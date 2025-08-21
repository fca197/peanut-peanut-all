<script setup lang="ts">
import {ref, onMounted} from "vue"
import AddEditFormVue from "./ApsOrderGoodsAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { ElTable } from "element-plus";
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {type ApsOrderGoods} from "./ApsOrderGoodsType.ts"

const dtoUrl = ref<string>("/apsOrderGoods")
const documentTitle = ref<string>("订单商品")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsOrderGoods>({
  orderId: undefined,
  goodsId: undefined,
  goodsName: undefined,
  goodsRemark: undefined,
  goodsAmount: undefined,
  goodsPrice: undefined,
  goodsTotalPrice: undefined,
  goodsUnit: undefined,
  goodsUnitPrice: undefined,
  goodsUnitTotalPrice: undefined,
  goodsStatusId: undefined,
  factoryId: undefined,
  apsStatusId: undefined,
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
const dataList = ref<ApsOrderGoods[] >([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
 {fieldName: "id", showName: "序号"},
  { fieldName: "orderId", showName: "" },
  { fieldName: "goodsId", showName: "商品" },
  { fieldName: "goodsName", showName: "" },
  { fieldName: "goodsRemark", showName: "" },
  { fieldName: "goodsAmount", showName: "总价" },
  { fieldName: "goodsPrice", showName: "成本价" },
  { fieldName: "goodsTotalPrice", showName: "成本价" },
  { fieldName: "goodsUnit", showName: "" },
  { fieldName: "goodsUnitPrice", showName: "成本价" },
  { fieldName: "goodsUnitTotalPrice", showName: "成本价" },
  { fieldName: "goodsStatusId", showName: "订单状态" },
  { fieldName: "factoryId", showName: "工厂" },
  { fieldName: "apsStatusId", showName: "订单状态" },
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
const handleSelectionChange = (val: ApsOrderGoods[]) => {
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
        <el-form-item label="${column.comment}" prop="goodsName">
          <el-input v-model="queryForm.goodsName" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="goodsRemark">
          <el-input v-model="queryForm.goodsRemark" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="总价" prop="goodsAmount">
          <el-input v-model="queryForm.goodsAmount" clearable placeholder="请输入总价" />
        </el-form-item>
        <el-form-item label="成本价" prop="goodsPrice">
          <el-input v-model="queryForm.goodsPrice" clearable placeholder="请输入成本价" />
        </el-form-item>
        <el-form-item label="成本价" prop="goodsTotalPrice">
          <el-input v-model="queryForm.goodsTotalPrice" clearable placeholder="请输入成本价" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="goodsUnit">
          <el-input v-model="queryForm.goodsUnit" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="成本价" prop="goodsUnitPrice">
          <el-input v-model="queryForm.goodsUnitPrice" clearable placeholder="请输入成本价" />
        </el-form-item>
        <el-form-item label="成本价" prop="goodsUnitTotalPrice">
          <el-input v-model="queryForm.goodsUnitTotalPrice" clearable placeholder="请输入成本价" />
        </el-form-item>
        <el-form-item label="订单状态" prop="goodsStatusId">
          <el-input v-model="queryForm.goodsStatusId" clearable placeholder="请输入订单状态" />
        </el-form-item>
        <el-form-item label="工厂" prop="factoryId">
          <el-input v-model="queryForm.factoryId" clearable placeholder="请输入工厂ID" />
        </el-form-item>
        <el-form-item label="订单状态" prop="apsStatusId">
          <el-input v-model="queryForm.apsStatusId" clearable placeholder="请输入订单状态" />
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

