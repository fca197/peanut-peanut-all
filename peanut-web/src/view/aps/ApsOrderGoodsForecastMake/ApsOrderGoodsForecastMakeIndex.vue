<script setup lang="ts">
import {ref, onMounted} from "vue"
import AddEditFormVue from "./ApsOrderGoodsForecastMakeAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { ElTable } from "element-plus";
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {type ApsOrderGoodsForecastMake} from "./ApsOrderGoodsForecastMakeType.ts"

const dtoUrl = ref<string>("/apsOrderGoodsForecastMake")
const documentTitle = ref<string>("订单商品节点预测")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsOrderGoodsForecastMake>({
  orderId: undefined,
  goodsId: undefined,
  goodsStatusId: undefined,
  goodsStatusName: undefined,
  forecastMakeDate: undefined,
  factoryId: undefined,
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
const dataList = ref<ApsOrderGoodsForecastMake[] >([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
 {fieldName: "id", showName: "序号"},
  { fieldName: "orderId", showName: "" },
  { fieldName: "goodsId", showName: "商品" },
  { fieldName: "goodsStatusId", showName: "订单状态" },
  { fieldName: "goodsStatusName", showName: "节点名称" },
  { fieldName: "forecastMakeDate", showName: "预计制造日期" },
  { fieldName: "factoryId", showName: "工厂" },
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
      headerList.value = t.data.headerList
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
const handleSelectionChange = (val: ApsOrderGoodsForecastMake[]) => {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
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
        <el-form-item label="${column.comment}" prop="orderId">
          <el-input v-model="queryForm.orderId" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="商品" prop="goodsId">
          <el-input v-model="queryForm.goodsId" clearable placeholder="请输入商品ID" />
        </el-form-item>
        <el-form-item label="订单状态" prop="goodsStatusId">
          <el-input v-model="queryForm.goodsStatusId" clearable placeholder="请输入订单状态" />
        </el-form-item>
        <el-form-item label="节点名称" prop="goodsStatusName">
          <el-input v-model="queryForm.goodsStatusName" clearable placeholder="请输入节点名称" />
        </el-form-item>
        <el-form-item label="预计制造日期" prop="forecastMakeDate">
          <el-input v-model="queryForm.forecastMakeDate" clearable placeholder="请输入预计制造日期" />
        </el-form-item>
        <el-form-item label="工厂" prop="factoryId">
          <el-input v-model="queryForm.factoryId" clearable placeholder="请输入工厂ID" />
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
        <ElTableColumn fixed="right" label="操作" width="150px">
          <template #default="scope">
            <el-button
              type="warning"
              icon="edit"
              @click="editData(scope.row)"
            >
              编辑
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

