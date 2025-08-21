<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-form ref="queryForm" :inline="true" :model="queryParams" label-width="100px" >

        <el-form-item label="预测时间" prop="dataRange">
          <el-date-picker v-model="queryParams.dateRange"
                          :value="new Date()"
                          clearable
                          end-placeholder="结束日期"
                          range-separator="至"
                          start-placeholder="开始日期"
                          type="monthrange"
                          value-format="yyyy-MM"
          ></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button icon="el-icon-search" type="primary" @click="getData">
            搜索
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="search-wrapper" shadow="never">

      <el-divider>主版本预测结果</el-divider>
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
  postResultInfo("/apsGoodsForecastMainSaleData/getDataByGoodsId", { id: id }).then(t => {
    tableData.value = t.data
    tableData.value.headerList [0].width = 600

    const headerList = t.data.headerList.slice(3)
    for (let i = 1; i < t.data.dataList.length; i ++) {
      const item = t.data.dataList[i]
      headerList.forEach(header => {
        item[header.fieldName] = item[header.fieldName] * 100 + '%'
      })
    }
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