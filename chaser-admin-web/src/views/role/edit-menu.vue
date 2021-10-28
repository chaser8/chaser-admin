<template>
  <el-dialog :visible.sync="show" title="配置菜单" :before-close="close" @open="open">
    <el-tree
      ref="menuTree"
      :data="treeData"
      show-checkbox
      node-key="id"
      :props="defaultProps"
      default-expand-all
      style="min-height: 200px"
      check-on-click-node
      :expand-on-click-node="false"
      check-strictly="true"
    />
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="updateRoleMenus()">
        保存
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import {getLevelMenus} from '@/api/menu'
import {getRoleMenus, updateRoleMenus} from '@/api/role'

export default {
  name: 'EditMenu',
  props: {
    show: { type: Boolean, required: true },
    roleId: { required: true }
  },
  data() {
    return {
      treeData: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  created() {
    this.treeData = []
    getLevelMenus().then(response => {
      this.treeData = response.data
    })
  },
  methods: {
    updateRoleMenus() {
      const checkedNodes = this.$refs.menuTree.getCheckedNodes()
      if (checkedNodes.length < 1) {
        this.$notify({
          title: '提示',
          message: '请选择需要添加的菜单',
          type: 'error',
          duration: 2000
        })
        return
      }
      const menus = checkedNodes.map(menu => {
        return menu.id
      })
      updateRoleMenus({ roleId: this.roleId, menus: menus }).then(_ => {
        this.$notify({
          title: '提示',
          message: '配置成功',
          type: 'success',
          duration: 2000
        })
        this.close()
      })
    },
    open() {
      getRoleMenus({ roleId: this.roleId }).then(res => {
        this.$refs.menuTree.setCheckedKeys(res.data)
      })
    },
    close() {
      this.$refs.menuTree.setCheckedKeys([])
      this.$emit('update:show', false)
    }
  }
}
</script>

<style scoped>

</style>
