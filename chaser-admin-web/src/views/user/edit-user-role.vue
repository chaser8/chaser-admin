<template>
  <el-dialog v-el-drag-dialog title="编辑角色" :visible.sync="visible" @open="open" :before-close="close">
    <el-transfer v-model="value" :data="roleList" :titles="['可选角色', '已选角色']" :props="transferProps" />
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="updateUserRoles()">
        保存
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import { getRoles } from '@/api/role'
import { getUserRoles, updateUserRoles } from '@/api/user'

export default {
  name: 'EditUserRole',
  props: {
    visible: { type: Boolean, required: true },
    userId: { required: true }
  },
  data() {
    return {
      roleList: [],
      value: [],
      transferProps: {
        key: 'id',
        label: 'name'
      }
    }
  },
  created() {
    this.roleList = []
    getRoles({}).then(response => {
      this.roleList = response.data
    })
  },
  methods: {
    close() {
      this.$emit('update:visible', false)
    },
    open() {
      this.value = []
      getUserRoles({ userId: this.userId }).then(response => {
        for (const role of response.data) {
          this.value.push(role.roleId)
        }
      })
    },
    updateUserRoles() {
      if (this.value.length < 1) {
        this.$notify({
          title: '提示',
          message: '请选择需要添加的角色',
          type: 'error',
          duration: 2000
        })
        return
      }
      updateUserRoles({ roleIds: this.value, userId: this.userId }).then(_ => {
        this.$emit('update:visible', false)
        this.$emit('callback')
        this.$notify({
          title: '提示',
          message: '更新成功',
          type: 'success',
          duration: 2000
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
