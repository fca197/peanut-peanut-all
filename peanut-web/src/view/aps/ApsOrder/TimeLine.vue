<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-form :inline="true" :model="queryParams" label-width="88px">
        <el-form-item label="时间类型" prop="">
          <el-select v-model="queryParams.isActualMakeTime" style="width: 200px">
            <el-option label="实际时间" :value="1"/>
            <el-option label="预计时间" :value="0"/>
          </el-select>
        </el-form-item>
        <el-form-item label="订单编号" prop="orderNo">
          <el-input v-model="queryParams.orderNo" placeholder="订单编号" clearable style="width: 200px"/>
        </el-form-item>
        <el-form-item label="时间范围" prop="">

          <el-date-picker
            v-model="queryParams.dateArr"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            value-format="YYYY-MM-DD"
            format="YYYY-MM-DD"
            style="width: 300px"
          />
        </el-form-item>
        <el-form-item>
          <el-button icon="search" type="primary" @click="getDataList">
            搜索
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="search-wrapper" shadow="never">

      <ElTable ref="dataTableRef" :data="dataList" stripe @selection-change="handleSelectionChange">
        <ElTableColumn type="selection"/>
        <ElTableColumn v-for="h in headerList" :key="h.fieldName" :label="h.showName" :prop="h.fieldName" :min-width="h.width"/>
      </ElTable>
      <el-row class="paginationDiv">
        <el-pagination
          background
          v-model:current-page="currentPageNum"
          v-model:page-size="currentPageSize"
          layout="total, sizes, prev, pager, next"
          :total="tableTotal"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </el-row>
    </el-card>
  </div>
</template>

<script setup lang="ts">

import { ref } from "vue";
import { ElTable } from "element-plus";
import TableBar from "@/layouts/components/TableBar/index.vue";
import type { ApsOrder } from "@v/aps/ApsOrder/ApsOrderType.ts";
import { HeaderInfo, postResultInfo } from "@@/utils/common-js.ts";
import { ApsStatus, queryApsStatusList } from "@v/aps/ApsStatus/ApsStatusType.ts";
import type { ApsMachine } from "@v/aps/ApsMachine/ApsMachineType.ts";
import dayjs from "dayjs";

const queryParams = ref({
  isActualMakeTime: 1,
  orderNo: undefined,
  dateArr: []
})

// 表格选中的id
const multipleSelection = ref<(string | undefined) []>([])

// 表格
// const dataTableRef = ref<InstanceType<typeof ElTable> | null>(null)
const dataTableRef = ref({})
// 表格操作头
const tableBarRef = ref<InstanceType<typeof TableBar> | null>(null)
// 表格相关
const dataList = ref<ApsOrder[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([])
const apsStatusList = ref<ApsStatus[]>([])
const loadDataIng = ref<boolean>(true)

onMounted(() => {
  try {

    const beginDate = new Date()
    beginDate.setDate(beginDate.getDate() - 10)
    queryParams.value.dateArr[0] = new dayjs(beginDate).format("YYYY-MM-DD");
    const today = new Date()
    today.setMonth(today.getMonth() + 1) // 设置为下个月
    queryParams.value.dateArr[1] = new dayjs(today).format("YYYY-MM-DD");

    queryApsStatusList().then(r => {
      apsStatusList.value = r
    }).then(r => {
      getDataList()
    })

  } catch (e) {
    console.error("e ", e)
  }
})

const getDataList = () => {

  if(queryParams.value.dateArr === null || queryParams.value.dateArr === undefined || queryParams.value.dateArr.length !== 2) {
    ElMessage.error("请选择时间范围")
    return
  }
  queryParams.value.beginDate = queryParams.value.dateArr[0]
  queryParams.value.endDate = queryParams.value.dateArr[1]
  postResultInfo("/apsOrder/timeLine", queryParams.value).then(t => {
    headerList.value = t.data.headerList
    dataList.value = t.data.records
    dataList.value.forEach(r => {
      r.statusInfoList.forEach(rt => {
        //
        r[rt.beginDate] = r[rt.beginDate] === undefined ? rt.statusName : r[rt.beginDate] + '/' + rt.statusName
        r[rt.beginDate + '_isDelay'] = rt.isDelay
        r[rt.beginDate + '_statusId'] = rt.statusId
        r[rt.beginDate + '_actualMakeBeginTime'] = rt.actualMakeBeginTime
        r[rt.beginDate + '_expectMakeBeginTime'] = rt.expectMakeBeginTime
      })
    })
    tableTotal.value = parseInt(t.data.total)

  })
}

// table点击事件
function editData(data: any) {
  // console.info("data ", data)
  tableBarRef.value?.showEditDialog(data.id)
}

// 页面条数变更事件
function handleSizeChange(val: number) {
  currentPageSize.value = val
  getDataList()
}

// 页面变更事件
function handleCurrentChange(val: number) {
  currentPageNum.value = val
  getDataList()
}

// 表格选中事件
function handleSelectionChange(val: ApsMachine[]) {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}
</script>

<style scoped lang="scss">

</style>
