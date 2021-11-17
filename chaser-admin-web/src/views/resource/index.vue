<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.value" placeholder="请输入服务名称模糊查询" style="width: 200px;" class="filter-item" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        查询
      </el-button>
      <el-button v-permission="['system:resource:add']" class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
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
      tooltip-effect="light"
    >
      <el-table-column align="center" label="序号" width="50">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="服务名" prop="name">
        <template slot-scope="{row}">
          <template v-if="row.edit">
            <el-input v-model="row.name" class="edit-input" size="small" />
          </template>
          <span v-else>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="服务地址" show-overflow-tooltip width="300" prop="url">
        <template slot-scope="{row}">
          <template v-if="row.edit">
            <el-input v-model="row.url" class="edit-input" size="small" />
          </template>
          <span v-else>{{ row.url }}</span>
        </template>
      </el-table-column>
      <el-table-column label="服务方法" width="110" prop="method">
        <template slot-scope="{row}">
          <template v-if="row.edit">
            <el-select v-model="row.method" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </template>
          <span v-else>{{ row.method }}</span>
        </template>
      </el-table-column>
      <el-table-column label="描述" show-overflow-tooltip prop="description">
        <template slot-scope="{row}">
          <template v-if="row.edit">
            <el-input v-model="row.description" type="textarea" :rows="2" class="edit-input" size="small" />
          </template>
          <span v-else>{{ row.description }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
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
          <template v-else>
            <el-button v-permission="['system:resource:update']" type="primary" size="mini" @click="edit(row)">
              编辑
            </el-button>
            <el-button v-permission="['system:resource:delete']" size="mini" type="danger" @click="del(row)">
              删除
            </el-button>
          </template>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="fetchData" />
  </div>
</template>

<script>
import {deleteResource, merge, page} from '@/api/resource'
import Pagination from '@/components/Pagination'

export default {

  name: 'Resource',
  components: { Pagination },
  data() {
    return {
      options: [{
        value: 'POST',
        label: 'POST'
      }, {
        value: 'GET',
        label: 'GET'
      }, {
        value: 'DELETE',
        label: 'DELETE'
      }, {
        value: 'PUT',
        label: 'PUT'
      }, {
        value: 'PATCH',
        label: 'PATCH'
      }],
      list: [],
      listLoading: true,
      total: 0,
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
        row.name = row.oname
        row.description = row.odescription
        row.url = row.ourl
        row.method = row.omethod
      }
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
    del(row) {
      this.$confirm('此操作将删除该服务, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteResource(row.id).then(_ => {
          this.$notify({
            title: '提示',
            message: '服务删除成功',
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
      page(this.listQuery).then(response => {
        this.total = response.data.total
        this.listLoading = false
        this.list = response.data.list.map(v => {
          this.$set(v, 'edit', false)
          v.oname = v.name
          v.odescription = v.description
          v.ourl = v.url
          v.omethod = v.method
          return v
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
