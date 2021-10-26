<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.value" placeholder="帐号/用户名/手机号模糊查询" style="width: 200px;" class="filter-item" />
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
      <el-table-column align="center" label="序号" width="95">
        <template slot-scope="scope">
          {{ scope.$index+1 }}
        </template>
      </el-table-column>
      <el-table-column label="帐号">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column label="用户名">
        <template slot-scope="scope">
          {{ scope.row.nickName }}
        </template>
      </el-table-column>
      <el-table-column label="角色" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.roleName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.userPhone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后登录时间" width="160" align="center">
        <template slot-scope="scope">
          {{ scope.row.loginTime }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="状态" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.statusName }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="错误次数" width="100">
        <template slot-scope="scope">
          {{ scope.row.pwdErrorCnt }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="240" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            编辑
          </el-button>
          <el-button size="mini" type="success" @click="editRole(row)">
            编辑角色
          </el-button>
          <el-button v-if="row.status=='1000'" size="mini" type="danger" @click="freeze(row)">
            冻结
          </el-button>
          <el-button v-else size="mini" type="danger" @click="unfreeze(row)">
            解冻
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="fetchData" />

    <edit-user ref="userEdit" :visible.sync="dialogFormVisible" :dialog-status.sync="dialogStatus" :form-data.sync="temp" @callback="fetchData" />
    <edit-user-role ref="editUserRole" :visible.sync="dialogEditRoleVisible" :user-id="editRoleUserId" @callback="fetchData" />
  </div>
</template>

<script>
import {freeze, page, unfreeze} from '@/api/user'
import Pagination from '@/components/Pagination'
import EditUser from '@/views/user/edit-user'
import EditUserRole from '@/views/user/edit-user-role' // secondary package based on el-pagination
export default {
  components: { EditUserRole, EditUser, Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        1000: 'success',
        2000: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true,
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        value: undefined
      },
      temp: {
      },
      dialogEditRoleVisible: false,
      dialogFormVisible: false,
      dialogStatus: '',
      editRoleUserId: undefined
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        username: '',
        password: '',
        userPhone: '',
        passwordRepeat: '',
        email: '',
        nickName: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
    },
    handleFilter() {
      this.listQuery.pageNum = 1
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      page(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
        this.listLoading = false
      })
    },
    editRole(row) {
      this.editRoleUserId = row.userId
      this.dialogEditRoleVisible = true
    },
    freeze(row) {
      freeze({ userId: row.userId }).then(_ => {
        this.fetchData()
      })
    },
    unfreeze(row) {
      unfreeze({ userId: row.userId }).then(_ => {
        this.fetchData()
      })
    }
  }
}
</script>

