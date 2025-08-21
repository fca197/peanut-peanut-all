<script setup lang="ts">

const props = defineProps({
  kittingMissingBom: {
    type: Array
  }
})
const showKittingMissingBom = ref<boolean>(false);
</script>

<template>

  <el-button
    type="danger"
    v-if="props.kittingMissingBom?.length>0" @click="showKittingMissingBom=true" icon="View">
    详情
  </el-button>

  <el-dialog v-model="showKittingMissingBom" :width="500" append-to-body>
    <el-table :data="props.kittingMissingBom">
      <el-table-column label="序号">
        <template #default="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="label" label="零件名称"/>
      <el-table-column prop="value" label="缺失量"/>
    </el-table>

  </el-dialog>

</template>

<style scoped lang="scss">

</style>
