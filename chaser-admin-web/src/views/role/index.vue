<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.value" placeholder="请输入角色名称迷糊查询" style="width: 200px;" class="filter-item" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        查询
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        新增
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
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="角色名">
        <template slot-scope="{row}">
          <template v-if="row.edit">
            <el-input v-model="row.name" class="edit-input" size="small" />
          </template>
          <span v-else>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="描述">
        <template slot-scope="{row}">
          <template v-if="row.edit">
            <el-input v-model="row.description" class="edit-input" size="small" />
          </template>
          <span v-else>{{ row.description }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="160" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="400" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
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
          <el-button v-else type="primary" size="mini" @click="edit(row)">
            编辑
          </el-button>
          <el-button type="primary" size="mini" @click="editMenu(row)">
            配置菜单
          </el-button>
          <el-button type="primary" size="mini" @click="editMenu(row)">
            配置权限
          </el-button>
          <el-button size="mini" type="danger" @click="del(row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="fetchData" />
    <edit-menu :role-id.sync="currentRoleId" :show.sync="editMenuShow" />
  </div>
</template>

<script>
import {del, getRolePage, merge} from '@/api/role'
import Pagination from '@/components/Pagination'
import EditMenu from '@/views/role/edit-menu' // secondary package based on el-pagination
export default {
  components: { EditMenu, Pagination },
  data() {
    return {
      list: [],
      listLoading: true,
      total: 0,
      currentRoleId: undefined,
      editMenuShow: false,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        value: undefined
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    cancelEdit(row) {
      row.edit = false
      if (row.id === undefined) {
        this.list.shift()
      } else {
        row.name = row.originalName
        row.description = row.originalDescription
      }
    },
    handleCreate() {
      this.list.unshift({
        name: '',
        description: '',
        edit: true
      })
    },
    confirmEdit(row) {
      row.edit = false
      merge(row).then(_ => {
        this.$notify({
          title: '提示',
          message: '更新成功',
          type: 'success',
          duration: 2000
        })
        this.fetchData()
      })
    },
    edit(row) {
      row.edit = true
    },
    editMenu(row) {
      this.currentRoleId = row.id
      this.editMenuShow = true
    },
    del(row) {
      this.$confirm('此操作将删除该角色, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        del({ id: row.id }).then(_ => {
          this.$notify({
            title: '提示',
            message: '角色删除成功',
            type: 'success',
            duration: 2000
          })
          this.fetchData()
        })
      })
    },
    handleFilter() {
      this.listQuery.pageNum = 1
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      getRolePage(this.listQuery).then(response => {
        this.list = response.data.list.map(v => {
          this.$set(v, 'edit', false)
          v.originalName = v.name
          v.originalDescription = v.description
          return v
        })

        this.total = response.data.total
        this.listLoading = false
      })
    }
  }
}
</script>

