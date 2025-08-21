<script setup lang="ts">

import { postResultInfo } from "@@/utils/common-js.ts";
import { useRoute } from 'vue-router';

const tableData = ref<any>({})

// 获取当前路由信息
const route = useRoute();

// 从路由参数中获取id
const id = route.params.id as string;

onMounted(() => {
  postResultInfo("/apsGoodsForecast/computeResult", { id: id }).then(r => {
    tableData.value = r.data
    tableData.value.headerList [0].width = 600
    tableData.value.headerList.slice(1).forEach(h => h.width = 180)
    console.log("tableData.value = r ", r)
  })
})
</script>
<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-divider>计算结果</el-divider>
      <el-table id="dataTable" :data="tableData.dataList" cellpadding="0" cellspacing="0" show-summary>
        <el-table-column v-for="(item,index) in  tableData.headerList" :key="index" :label="item.showName"
                         :prop="item.fieldName" :width="item.width" align="center">
          <template #default="scope">
            <span v-if="scope.row[item.fieldName]">{{ scope.row[item.fieldName] }}</span>
            <span v-else>-</span>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>


<style scoped lang="scss">

</style>