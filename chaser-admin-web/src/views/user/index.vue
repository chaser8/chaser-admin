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
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            编辑
          </el-button>
          <el-button size="mini" type="success" @click="handleModifyStatus(row,'published')">
            添加角色
          </el-button>
          <el-button v-if="row.status=='1000'" size="mini" type="danger" @click="handleDelete(row,$index)">
            冻结
          </el-button>
          <el-button v-else size="mini" type="danger" @click="handleDelete(row,$index)">
            解冻
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="fetchData" />
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item v-if="dialogStatus=='create'" label="帐号" prop="username">
          <el-input v-model="temp.username" maxlength="25" show-word-limit />
        </el-form-item>
        <el-form-item label="手机号码" prop="userPhone" maxlength="13" show-word-limit>
          <el-input v-model="temp.userPhone" />
        </el-form-item>
        <el-form-item v-if="dialogStatus=='create'" label="密码" prop="password">
          <el-input v-model="temp.password" show-password autocomplete="off" maxlength="50" show-word-limit />
        </el-form-item>
        <el-form-item v-if="dialogStatus=='create'" label="重复密码" prop="passwordRepeat">
          <el-input v-model="temp.passwordRepeat" show-password autocomplete="off" maxlength="50" show-word-limit />
        </el-form-item>
        <el-form-item label="用户名" prop="nickName">
          <el-input v-model="temp.nickName" maxlength="50" show-word-limit />
        </el-form-item>
        <el-form-item label="电子邮件" prop="email">
          <el-input v-model="temp.email" maxlength="50" show-word-limit />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          保存
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { page, create, usernameUniqueCheck, update } from '@/api/user'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
export default {
  components: { Pagination },
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
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.temp.passwordRepeat !== '') {
          this.$refs.dataForm.validateField('passwordRepeat')
        }
        callback()
      }
    }
    var validatePass1 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.temp.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    var usernameUnique = (rule, value, callback) => {
      if (value !== '') {
        usernameUniqueCheck({ username: value }).then(response => {
          return response.data
        }).then(data => {
          if (data.flag !== true) {
            callback(new Error('帐号重复'))
          } else {
            callback()
          }
        })
      } else {
        callback()
      }
    }
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
        id: undefined,
        username: '',
        password: '',
        passwordRepeat: '',
        email: '',
        userPhone: '',
        nickName: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '新增'
      },
      rules: {
        username: [
          { required: true, message: '请输入登录账号', trigger: 'blur' },
          { min: 6, max: 25, message: '长度在 6 到 25 个字符', trigger: 'blur' },
          { validator: usernameUnique, trigger: 'blur' }
        ],
        nickName: [
          { required: true, message: '请输入用户姓名', trigger: 'blur' },
          { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
        ],
        userPhone: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          { pattern: /^1(3|4|5|7|8|9)\d{9}$/, message: '请输入合法手机号', trigger: 'blur' }
        ],
        password: [
          { required: true, validator: validatePass, trigger: 'blur' },
          { min: 6, max: 50, message: '长度在 6 到 50 个字符', trigger: 'blur' }],
        passwordRepeat: [
          { required: true, validator: validatePass1, trigger: 'blur' },
          { min: 6, max: 50, message: '长度在 6 到 50 个字符', trigger: 'blur' }]
      }
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
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
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
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
      })
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
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          create(this.temp).then(() => {
            this.dialogFormVisible = false
            this.fetchData()
            this.$notify({
              title: '提示',
              message: '新增成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          update(this.temp).then(() => {
            this.dialogFormVisible = false
            this.fetchData()
            this.$notify({
              title: '提示',
              message: '更新成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    }
  }
}
</script>

