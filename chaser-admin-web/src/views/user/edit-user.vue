<template>
  <el-dialog :title="textMap[dialogStatus]" :visible.sync="visible" :before-close="close" @open="open">
    <el-form ref="dataForm" :rules="rules" :model="formData" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item v-if="dialogStatus=='create'" label="帐号" prop="username">
        <el-input v-model="formData.username" maxlength="25" show-word-limit />
      </el-form-item>
      <el-form-item label="手机号码" prop="userPhone" maxlength="13" show-word-limit>
        <el-input v-model="formData.userPhone" />
      </el-form-item>
      <el-form-item v-if="dialogStatus=='create'" label="密码" prop="password">
        <el-input v-model="formData.password" show-password autocomplete="off" maxlength="50" show-word-limit />
      </el-form-item>
      <el-form-item v-if="dialogStatus=='create'" label="重复密码" prop="passwordRepeat">
        <el-input v-model="formData.passwordRepeat" show-password autocomplete="off" maxlength="50" show-word-limit />
      </el-form-item>
      <el-form-item label="用户名" prop="nickName">
        <el-input v-model="formData.nickName" maxlength="50" show-word-limit />
      </el-form-item>
      <el-form-item label="电子邮件" prop="email">
        <el-input v-model="formData.email" maxlength="50" show-word-limit />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
        保存
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import { create, usernameUniqueCheck, update } from '@/api/user'

export default {
  name: 'EditUser',
  props: {
    visible: { type: Boolean, required: true },
    formData: { type: Object },
    dialogStatus: { type: String },
    callback: { type: Function }

  },
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.formData.passwordRepeat !== '') {
          this.$refs.dataForm.validateField('passwordRepeat')
        }
        callback()
      }
    }
    var validatePass1 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.formData.password) {
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
  methods: {
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          create(this.formData).then(() => {
            this.$emit('callback')
            this.$emit('update:visible', false)
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
    open() {
      console.dir(this.formData)
      if (this.dialogStatus === 'update') {
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      } else if (this.dialogStatus === 'create') {
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
        })
      }
    },
    close() {
      this.$emit('update:visible', false)
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          update(this.formData).then(() => {
            this.$emit('callback')
            this.$emit('update:visible', false)
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

<style scoped>

</style>
