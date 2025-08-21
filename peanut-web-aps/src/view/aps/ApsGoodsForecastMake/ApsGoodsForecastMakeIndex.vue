<script setup lang="ts">
import { onMounted, ref } from "vue"
import AddEditFormVue from "./ApsGoodsForecastMakeAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { ElTable } from "element-plus";
import { HeaderInfo, postNoResult, postResultInfo } from "@@/utils/common-js.ts"
import { type ApsGoodsForecastMake } from "./ApsGoodsForecastMakeType.ts"
import { ApsGoods, queryGoodsList } from "@v/aps/ApsGoods/ApsGoodsType.ts";

const dtoUrl = ref<string>("/apsGoodsForecastMake")
const documentTitle = ref<string>("商品预测-制造")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsGoodsForecastMake>({
  goodsId: undefined,
  forecastMakeMonthNo: undefined,
  forecastMakeMonthName: undefined,
  forecastMakeMonthBeginDate: undefined,
  forecastMakeMonthEndDate: undefined,
  factoryId: undefined,
  month: undefined,
  weeks: undefined,
  forecastMainId: undefined,
  isDeploy: undefined,
  bomUseBeginDate: undefined,
  bomUseEndDate: undefined,
  id: undefined
})

// 表格选中的id
const multipleSelection = ref<(string | undefined)[]>([])

// 表格
// const dataTableRef = ref<InstanceType<typeof ElTable> | null>(null)
const dataTableRef = ref({})
// 表格操作头
const tableBarRef = ref<InstanceType<typeof TableBar> | null>(null)
// 表格相关
const dataList = ref<ApsGoodsForecastMake[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
  { fieldName: "id", showName: "序号" },
  { fieldName: "goodsId", showName: "商品" },
  { fieldName: "forecastMakeMonthNo", showName: "" },
  { fieldName: "forecastMakeMonthName", showName: "" },
  { fieldName: "forecastMakeMonthBeginDate", showName: "" },
  { fieldName: "forecastMakeMonthEndDate", showName: "" },
  { fieldName: "factoryId", showName: "" },
  { fieldName: "month", showName: "" },
  { fieldName: "weeks", showName: "" },
  { fieldName: "forecastMainId", showName: "" },
  { fieldName: "isDeploy", showName: "" },
  { fieldName: "bomUseBeginDate", showName: "" },
  { fieldName: "bomUseEndDate", showName: "" },
])

const goodsList = ref<ApsGoods []>([])
const router = useRouter()

// 获取表格内数据
const getDataList = () => {
  const req = {
    pageSize: currentPageSize.value,
    pageNum: currentPageNum.value,
    data: queryForm.value
  }
  console.info("getDataList {}", req)
  postResultInfo(`${dtoUrl.value}/queryPageList`, req)
    .then((t) => {
      dataList.value = t.data.dataList
      tableTotal.value = Number.parseInt(t.data.total)
      headerList.value = t.data.headerList
    })
}

// table点击事件
const showData = (data: any) => {
  // console.info("data ", data)
  // tableBarRef.value?.showEditDialog(data.id)
  router.push(`/aps/ApsGoodsForecastMake/result/${data.id}`)
}
// 页面条数变更事件
const handleSizeChange = (val: number) => {
  currentPageSize.value = val
  getDataList()
}
// 页面变更事件
const handleCurrentChange = (val: number) => {
  currentPageNum.value = val
  getDataList()
}
// 表格选中事件
const handleSelectionChange = (val: ApsGoodsForecastMake[]) => {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}

const deployData = (val: ApsGoodsForecastMake) => {
  console.info("deployData ", val)
  postNoResult("/apsGoodsForecastMake/deploy", val, "发布成功", undefined)
}

// 页面加载事件
onMounted(() => {
  getDataList()
  queryGoodsList().then(r => goodsList.value = r)
})

</script>

<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-form v-model="queryForm" inline>
        <el-form-item label="商品" prop="goodsId">
          <el-select v-model="queryForm.goodsId" clearable style="width: 200px">
            <el-option v-for="g in goodsList" :key="g.id" :value="g.id" :label="g.goodsName"/>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="search" @click="getDataList">
            查询
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card shadow="never">
      <TableBar
        :document-title="documentTitle"
        :add-component="AddEditFormVue"
        :refresh-list="getDataList"
        :data-table-ref="dataTableRef"
        :multiple-selection="multipleSelection"
        ref="tableBarRef"
        :data-batch-delete-url="dataBatchDeleteUrl"
      />
      <ElTable ref="dataTableRef" :data="dataList" stripe @selection-change="handleSelectionChange">
        <ElTableColumn type="selection"/>
        <ElTableColumn v-for="h in headerList" :key="h.fieldName" :label="h.showName" :prop="h.fieldName" :min-width="h.width"/>
        <ElTableColumn fixed="right" label="操作" width="250px">
          <template #default="scope">
            <el-button
              type="warning"
              icon="Histogram"
              @click="showData(scope.row)"
            >
              数据
            </el-button>
            <el-button
              type="primary"
              icon="DataAnalysis" v-if="scope.row.isDeploy===false"
              @click="deployData(scope.row)"
            >
              发布
            </el-button>
          </template>
        </ElTableColumn>
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

<style scoped lang="scss">

</style>

