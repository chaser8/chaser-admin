<template>
  <el-dialog class="dialog-resource" :visible.sync="show" title="关联服务" :before-close="close" width="850px" @open="open">
    <el-transfer
      v-model="value"
      :data="funcList"
      filterable
      :titles="['可选服务', '已选服务']"
      :filter-method="filterMethod"
      :props="transferProps"
      filter-placeholder="请输入服务名关键字"
      style="width: 100%"
    />
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="updateFuncResourceRel()">
        保存
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import { getAll } from '@/api/resource'
import { getResources, updateResources } from '@/api/func'

export default {
  name: 'FuncResourceRel',
  props: {
    show: { type: Boolean, required: true },
    funcId: { required: true }
  },
  data() {
    return {
      funcList: [],
      value: [],
      transferProps: {
        key: 'id',
        label: 'name'
      },
      filterMethod(query, item) {
        return item.name.indexOf(query) > -1
      }
    }
  },
  created() {
    getAll().then(res => {
      this.funcList = res.data
    })
  },
  methods: {
    updateFuncResourceRel() {
      updateResources({ funcId: this.funcId, resourceIds: this.value }).then(_ => {
        this.$notify({
          title: '提示',
          message: '关联服务成功',
          type: 'success',
          duration: 2000
        })
        this.$emit('update:show', false)
      })
    },
    open() {
      this.value = []
      getResources(this.funcId).then(res => {
        res.data.map(value => {
          this.value.push(value.resourceId)
        })
      })
    },
    close() {
      this.$emit('update:show', false)
    }
  }
}
</script>

<style scoped>
.dialog-resource >>> .el-transfer-panel{
  width: 300px;
}
</style>
