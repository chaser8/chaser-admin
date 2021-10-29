<template>
  <div class="app-container">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="菜单功能项管理" name="menu">
        <menu-manage />
      </el-tab-pane>
      <el-tab-pane label="后台服务管理" name="resource">
        <resource />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import MenuManage from '@/views/permission/menu-manage'
import Resource from '@/views/permission/resource'

export default {
  name: 'Index',
  components: { Resource, MenuManage },
  data() {
    return {
      activeName: 'menu'
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    }
  }
}
</script>

<style scoped>

</style>
