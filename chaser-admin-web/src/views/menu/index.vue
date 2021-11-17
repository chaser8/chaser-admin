<template>
  <el-container class="app-container">
    <el-aside width="250px" style="background: none;">
      <el-tree
        ref="menuTree"
        :data="menuData"
        node-key="id"
        :props="defaultProps"
        :default-expanded-keys="['0']"
        :expand-on-click-node="false"
        highlight-current
        style="min-height: 600px;max-height: 100%;border-right: #E4E7ED solid 1px;"
        @node-click="handleNodeClick"
      />
    </el-aside>
    <el-main>
      <div class="filter-container">
        <el-button v-if="tableMenuFlag" v-permission="['system:menu:add']" class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
          新增菜单
        </el-button>
        <el-button v-else v-permission="['system:func:add']" class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
          新增功能项
        </el-button>
      </div>
      <el-table
        v-loading="listLoading"
        :data="list"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column align="center" label="序号" width="50">
          <template slot-scope="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>
        <template v-if="tableMenuFlag">
          <el-table-column align="center" label="菜单名称">
            <template slot-scope="{row}">
              <template v-if="row.edit">
                <el-input v-model="row.name" class="edit-input" size="small" />
              </template>
              <span v-else>{{ row.name }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="菜单编码" show-overflow-tooltip prop="code">
            <template slot-scope="{row}">
              <template v-if="row.edit">
                <el-input v-model="row.code" class="edit-input" size="small" />
              </template>
              <span v-else>{{ row.code }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="排序" width="80" prop="sort">
            <template slot-scope="{row}">
              <template v-if="row.edit">
                <el-input v-model="row.sort" class="edit-input" size="small" />
              </template>
              <span v-else>{{ row.sort }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="图标" width="80" prop="icon">
            <template slot-scope="{row}">
              <template v-if="row.edit">
                <el-input v-model="row.icon" class="edit-input" size="small" />
              </template>
              <span v-else>{{ row.icon }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="菜单地址" show-overflow-tooltip prop="url">
            <template slot-scope="{row}">
              <template v-if="row.edit">
                <el-input v-model="row.url" class="edit-input" size="small" />
              </template>
              <span v-else>{{ row.url }}</span>
            </template>
          </el-table-column>
        </template>
        <template v-else>
          <el-table-column align="center" label="功能项名称" prop="name" />
          <el-table-column align="center" label="功能项编码" prop="code" />
        </template>
        <el-table-column label="操作" align="center" width="250" class-name="small-padding fixed-width">
          <template slot-scope="{row}">
            <template v-if="tableMenuFlag">
              <template v-if="row.edit">
                <el-button
                  v-if="row.edit"
                  class="cancel-btn"
                  size="mini"
                  type="warning"
                  @click="cancelEdit(row)"
                >
                  取消
                </el-button>
                <el-button
                  type="success"
                  size="mini"
                  @click="confirmEdit(row)"
                >
                  确认
                </el-button>
              </template>
              <template v-else>
                <el-button v-permission="['system:menu:update']" type="primary" size="mini" @click="edit(row)">
                  编辑
                </el-button>
                <el-button v-permission="['system:menu:delete']" type="danger" size="mini" @click="del(row)">
                  删除
                </el-button>
              </template>
            </template>
            <template v-else>
              <template v-if="row.edit">
                <el-button
                  v-if="row.edit"
                  class="cancel-btn"
                  size="mini"
                  type="warning"
                  @click="cancelEdit(row)"
                >
                  取消
                </el-button>
                <el-button
                  type="success"
                  size="mini"
                  @click="confirmEdit(row)"
                >
                  确认
                </el-button>
              </template>
              <template v-else>
                <el-button v-permission="['system:func:update']" type="primary" size="mini" @click="edit(row)">
                  编辑
                </el-button>
                <el-button v-permission="['system:func:resource']" type="danger" size="mini" @click="relResource(row)">
                  关联服务
                </el-button>
                <el-button v-permission="['system:func:delete']" type="danger" size="mini" @click="del(row)">
                  删除
                </el-button>
              </template>
            </template>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
    <func-resource-rel :func-id.sync="funcResourceRelFuncId" :show.sync="funcResourceRelShow" />
  </el-container>
</template>

<script>
import { addMenu, delMenu, getChildren, getLevelMenus, getMenuFunc } from '@/api/menu'
import { addFunc, delFunc } from '@/api/func'
import FuncResourceRel from '@/views/menu/func-resource-rel'

export default {
  name: 'MenuManage',
  components: { FuncResourceRel },
  data() {
    const rootMenu = {
      id: '0',
      name: '系统菜单',
      level: 0,
      children: []
    }
    return {
      list: [],
      listLoading: true,
      total: 0,
      tableMenuFlag: true,
      funcResourceRelFuncId: undefined,
      funcResourceRelShow: false,
      menuData: [rootMenu],
      currentMenu: rootMenu,
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      header: []
    }
  },
  created() {
    this.loadMenus()
    this.fetchData()
  },
  methods: {
    relResource(row) {
      this.funcResourceRelFuncId = row.id
      this.funcResourceRelShow = true
    },
    loadMenus() {
      getLevelMenus().then(response => {
        this.menuData[0].children = response.data
      })
    },
    handleCreate() {
      if (this.list.length !== 0 && this.list[0].id === undefined) {
        this.$notify({
          title: '提示',
          message: '请先提交后再新增',
          type: 'error',
          duration: 2000
        })
        return
      }
      if (this.tableMenuFlag) {
        const level = this.currentMenu.level + 1
        this.list.unshift({
          parentId: this.currentMenu.id,
          name: '',
          level: level,
          sort: undefined,
          code: '',
          icon: '',
          url: '',
          edit: true
        })
      } else {
        this.list.unshift({
          parentId: this.currentMenu.id,
          name: '',
          code: '',
          edit: true
        })
      }
    },
    cancelEdit(row) {
      row.edit = false
      if (row.id === undefined) {
        this.list.shift()
      } else {
        row.name = row.oname
        row.level = row.olevel
        row.sort = row.osort
        row.code = row.ocode
        row.icon = row.oicon
        row.url = row.ourl
      }
    },
    handleNodeClick(data) {
      this.tableMenuFlag = data.children !== null && data.children.length > 0
      this.currentMenu = data
      this.fetchData()
    },
    edit(row) {
      row.edit = true
    },
    del(row) {
      this.$confirm('此操作将删除信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.tableMenuFlag) {
          delMenu(row.id).then(_ => {
            this.$notify({
              title: '提示',
              message: '菜单删除成功',
              type: 'success',
              duration: 2000
            })
            this.loadMenus()
            this.fetchData()
          })
        } else {
          delFunc(row.id).then(_ => {
            this.$notify({
              title: '提示',
              message: '功能项删除成功',
              type: 'success',
              duration: 2000
            })
            this.fetchData()
          })
        }
      })
    },
    confirmEdit(row) {
      if (this.tableMenuFlag) {
        addMenu(row).then(_ => {
          this.$notify({
            title: '提示',
            message: '新增成功',
            type: 'success',
            duration: 2000
          })
          this.loadMenus()
          this.fetchData()
        })
      } else {
        addFunc(row).then(_ => {
          this.$notify({
            title: '提示',
            message: '新增成功',
            type: 'success',
            duration: 2000
          })
          this.fetchData()
        })
      }
    },
    fetchData() {
      this.list = []
      this.listLoading = true
      const that = this
      const initData = function(response) {
        that.list = response.data.map(v => {
          that.$set(v, 'edit', false)
          v.oname = v.name
          v.olevel = v.level
          v.osort = v.sort
          v.ocode = v.code
          v.oicon = v.icon
          v.ourl = v.url
          return v
        })
        that.listLoading = false
      }
      if (this.tableMenuFlag) {
        getChildren(this.currentMenu.id).then(initData)
      } else {
        getMenuFunc(this.currentMenu.id).then(initData)
      }
    }
  }
}
</script>

<style scoped>

</style>
