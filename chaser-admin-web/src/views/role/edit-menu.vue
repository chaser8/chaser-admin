<template>
  <el-dialog :visible.sync="show" title="配置菜单" :before-close="close" @open="open">
    <el-tree
      ref="menuTree"
      :data="treeData"
      show-checkbox
      node-key="key"
      :props="defaultProps"
      default-expand-all
      style="min-height: 200px"
      check-on-click-node
      :expand-on-click-node="false"
    >
      <span slot-scope="{ node, data }">
        <span>
          <i :class="data.type | classFilter" />
          {{ node.label }}
        </span>
      </span>
    </el-tree>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="updateRoleMenus()">
        保存
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import {getLevelMenus} from '@/api/menu'
import {getRoleMenus, updatePermission} from '@/api/role'

export default {
  name: 'EditMenu',
  filters: {
    classFilter(type) {
      const classMap = {
        menu: 'el-icon-s-management',
        func: 'el-icon-s-operation'
      }
      return classMap[type]
    }
  },
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
      let checkedNodes = this.$refs.menuTree.getCheckedNodes()
      const halfCheckedNodes = this.$refs.menuTree.getHalfCheckedNodes()
      checkedNodes = checkedNodes.concat(halfCheckedNodes)
      if (checkedNodes.length < 1) {
        this.$notify({
          title: '提示',
          message: '请选择需要添加的菜单',
          type: 'error',
          duration: 2000
        })
        return
      }
      const menus = []
      const funcIds = []
      for (const obj of checkedNodes) {
        if (obj.type === 'menu') {
          menus.push(obj.id)
        } else {
          funcIds.push(obj.id)
        }
      }
      updatePermission({ roleId: this.roleId, menuIds: menus, funcIds: funcIds }).then(_ => {
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
        if (res.data !== null) {
          const isLeaf = function(key, data) {
            for (const node of data) {
              if (node.key === key) {
                if (node.children === null || node.children.length === 0) {
                  return true
                } else {
                  return false
                }
              } else if (node.children !== null && node.children.length > 0) {
                return isLeaf(key, node.children)
              }
            }
          }
          const checkedKeys = []
          const selectCheckedKeys = res.data.map(value => {
            return value.type + '_' + value.id
          })
          for (const checkedKey of selectCheckedKeys) {
            if (isLeaf(checkedKey, this.treeData)) {
              checkedKeys.push(checkedKey)
            }
          }

          this.$refs.menuTree.setCheckedKeys(checkedKeys)
        }
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
