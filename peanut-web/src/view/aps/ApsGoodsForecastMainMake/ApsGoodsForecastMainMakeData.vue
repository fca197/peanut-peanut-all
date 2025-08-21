<template>
  <div class="app-container">

    <el-card class="search-wrapper" shadow="never">

      <el-divider>制造主版本结果</el-divider>
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

<script setup lang="ts">


import { useRoute } from 'vue-router';
import { postResultInfo } from "@@/utils/common-js.ts";

const tableData = ref<any>({})

// 获取当前路由信息
const route = useRoute();

// 从路由参数中获取id
const id = route.params.id as string;

console.info("id  ", id)


const queryParams = ref<any>({})

const getData = () => {
  postResultInfo("/apsGoodsForecastMainMake/queryDataById", { id: id }).then(t => {
    tableData.value = t.data
    tableData.value.headerList [0].width = 600

    const headerList = t.data.headerList.slice(1)
    headerList.forEach(h=>{
      h.width = 100
    })

    // tableData.value.headerList.slice(1).forEach(h => h.width = 180)
    console.log("tableData.value = t ", t)
  })
}
onMounted(() => {
  getData()
})
</script>


<style scoped lang="scss">

</style>