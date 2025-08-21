<script setup lang="ts">
import {onMounted, ref} from "vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import {ElTable} from "element-plus";
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {
  type ApsOrderGoodsBomKittingVersionOrderBom
} from "./ApsOrderGoodsBomKittingVersionOrderBomType.ts"

const dtoUrl = ref<string>("/apsOrderGoodsBomKittingVersionOrderBom")
const documentTitle = ref<string>("齐套检查版本详情")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

const route = useRoute()

const kittingVersionId = ref<string>(route.params.versionId)
const orderId = ref<string>(route.params.orderId)
console.info("kittingVersionId orderId ", kittingVersionId, orderId)
// 查询表格
const queryForm = ref<ApsOrderGoodsBomKittingVersionOrderBom>({
  kittingVersionId: kittingVersionId,
  orderId: orderId,
  orderNo: undefined,
  orderMakeBeginDateTime: undefined,
  goodsId: undefined,
  goodsName: undefined,
  workshopSectionId: undefined,
  workshopSectionName: undefined,
  workshopStationId: undefined,
  workshopStationName: undefined,
  apsRoomId: undefined,
  apsRoomName: undefined,
  bomId: undefined,
  bomName: undefined,
  bomUsage: undefined,
  inventoryBeforeCount: undefined,
  inventoryAfterCount: undefined,
  goodsStatusId: undefined,
  goodsStatusName: undefined,
  bomUseDateTime: undefined,
  createDate: undefined,
  factoryId: undefined,
  id: undefined
})

// 表格
// 表格选中的id
const multipleSelection = ref<(string | undefined)[]>([])
// const dataTableRef = ref<InstanceType<typeof ElTable> | null>(null)
const dataTableRef = ref({})
// 表格操作头
const tableBarRef = ref<InstanceType<typeof TableBar> | null>(null)
// 表格相关
const dataList = ref<ApsOrderGoodsBomKittingVersionOrderBom[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
  {fieldName: "id", showName: "序号", width: 200},
  {fieldName: "orderNo", showName: "订单编号", width: 200},
  // {fieldName: "orderMakeBeginDateTime", showName: "开始制造时间", width: 200},
  {fieldName: "goodsName", showName: "商品名称", width: 200},
  // {fieldName: "workshopSectionName", showName: "工段名称", width: 100},
  // {fieldName: "workshopStationName", showName: "工位名称", width: 100},
  // {fieldName: "apsRoomName", showName: "车间名称", width: 100},
  {fieldName: "bomName", showName: "零件名称", width: 100},
  {fieldName: "bomUsage", showName: "商品用量", width: 100},
  {fieldName: "inventoryBeforeCount", showName: "库存(前)", width: 100},
  {fieldName: "inventoryAfterCount", showName: "库存(后)", width: 100},
  // {fieldName: "goodsStatusName", showName: "状态名称", width: 100},
  // {fieldName: "bomUseDateTime", showName: "零件使用时间", width: 150},
  // {fieldName: "createDate", showName: "计算日期", width: 150}
])

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
    // headerList.value = t.data.headerList
  })
}

// table点击事件
const editData = (data: any) => {
  // console.info("data ", data)
  tableBarRef.value?.showEditDialog(data.id)
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
const handleSelectionChange = (val: ApsOrderGoodsBomKittingVersionOrderBom[]) => {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}

const tableRowClassName = ({row, rowIndex,}:
                           {
                             row: ApsOrderGoodsBomKittingVersionOrderBom
                             rowIndex: number
                           }) => {
  console.info("row.isEnough ", rowIndex, row)

  return row.isEnough ? "" : "warning-row"
}
// 页面加载事件
onMounted(() => {
  getDataList()
})
</script>

<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-form v-model="queryForm" inline>
        <!--        </el-form-item>  -->
        <!--        <el-form-item label="车间ID" prop="apsRoomId">  -->
        <!--          <el-input v-model="queryForm.apsRoomId" clearable placeholder="请输入车间ID"/>  -->
        <!--        </el-form-item>   -->
        <!--        <el-form-item label="工段Id" prop="workshopSectionId">  -->
        <!--          <el-input v-model="queryForm.workshopSectionId" clearable placeholder="请输入工段Id"/>  -->
        <!--        </el-form-item>  -->
        <!--        <el-form-item label="工段名称" prop="workshopSectionName">  -->
        <!--          <el-input v-model="queryForm.workshopSectionName" clearable placeholder="请输入工段名称"/>  -->
        <!--        </el-form-item>  -->
        <!--        <el-form-item label="工位ID" prop="workshopStationId">  -->
        <!--          <el-input v-model="queryForm.workshopStationId" clearable placeholder="请输入工位ID"/>  -->
        <!--        </el-form-item>  -->
        <!--        <el-form-item label="工位名称" prop="workshopStationName">  -->
        <!--          <el-input v-model="queryForm.workshopStationName" clearable placeholder="请输入工位名称"/>  -->

        <el-form-item label="零件名称" prop="bomName">
          <el-input v-model="queryForm.bomName" clearable placeholder="请输入零件名称"/>
        </el-form-item>
        <!--        <el-form-item label="状态ID" prop="goodsStatusId"> -->
        <!--          <el-input v-model="queryForm.goodsStatusId" clearable placeholder="请输入状态ID"/> -->
        <!--        </el-form-item> -->
        <!--        <el-form-item label="状态名称" prop="goodsStatusName"> -->
        <!--          <el-input v-model="queryForm.goodsStatusName" clearable placeholder="请输入状态名称"/> -->
        <!--        </el-form-item>  -->

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
        :show-add-btn="false"
        :show-del-btn="false"
        :refresh-list="getDataList"
        :data-table-ref="dataTableRef"
        :multiple-selection="multipleSelection"
        ref="tableBarRef"
        :data-batch-delete-url="dataBatchDeleteUrl"
      />
      <ElTable
        ref="dataTableRef" :data="dataList"
        @selection-change="handleSelectionChange"
        :row-class-name="tableRowClassName"
      >
        <ElTableColumn
          type="selection"
        />
        <ElTableColumn
          v-for="h in headerList" :key="h.fieldName" :label="h.showName"
          :prop="h.fieldName"
        />
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

<style>
.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-danger-light-9);
}
</style>
