<script setup lang="ts">
import { onMounted } from "vue"
import { queryApsBomGroupTree } from "@v/aps/ApsBomGroup/ApsBomGroupType.ts"
interface Tree {
  label: string
  children?: Tree[]
}

const defaultProps = {
  children: "children",
  label: "groupName"
}

const data = ref([])
const queryLoading = ref<boolean>(true)
onMounted(() => {
  queryData()
})
function queryData() {
  data.value = []
  queryApsBomGroupTree().then((t) => {
    data.value = t
    queryLoading.value = false
  })
}
</script>

<template>
  <el-button type="primary" icon="refresh" @click="queryData">
    刷新
  </el-button>
  <el-tree :highlight-current="true" :data="data" :props="defaultProps" />
</template>

<style scoped lang="scss"></style>
