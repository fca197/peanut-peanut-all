<script setup lang="ts">
import {ref, onMounted} from "vue"
import AddEditFormVue from "./ApsOrderGoodsBomAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { ElTable } from "element-plus";
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {type ApsOrderGoodsBom} from "./ApsOrderGoodsBomType.ts"

const dtoUrl = ref<string>("/apsOrderGoodsBom")
const documentTitle = ref<string>("BOM 清单")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsOrderGoodsBom>({
  orderId: undefined,
  goodsBomId: undefined,
  goodsId: undefined,
  groupId: undefined,
  bomId: undefined,
  bomCode: undefined,
  bomName: undefined,
  bomUsage: undefined,
  bomUnit: undefined,
  bomCostPrice: undefined,
  bomCostPriceUnit: undefined,
  bomUseWorkStation: undefined,
  bomUseExpression: undefined,
  bomInventory: undefined,
  isFollow: undefined,
  factoryId: undefined,
  goodsStatusId: undefined,
  bomUseDate: undefined,
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
const dataList = ref<ApsOrderGoodsBom[] >([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
 {fieldName: "id", showName: "序号"},
  { fieldName: "orderId", showName: "订单ID" },
  { fieldName: "goodsBomId", showName: "aps_goods_bom ID " },
  { fieldName: "goodsId", showName: "商品" },
  { fieldName: "groupId", showName: "零件组ID" },
  { fieldName: "bomId", showName: "商品" },
  { fieldName: "bomCode", showName: "bom 编码" },
  { fieldName: "bomName", showName: "bom 名称" },
  { fieldName: "bomUsage", showName: "使用量" },
  { fieldName: "bomUnit", showName: "单位" },
  { fieldName: "bomCostPrice", showName: "成本价" },
  { fieldName: "bomCostPriceUnit", showName: "单位" },
  { fieldName: "bomUseWorkStation", showName: "使用工位" },
  { fieldName: "bomUseExpression", showName: "使用表达式" },
  { fieldName: "bomInventory", showName: "库存" },
  { fieldName: "isFollow", showName: "是否关注" },
  { fieldName: "factoryId", showName: "工厂" },
  { fieldName: "goodsStatusId", showName: "状态" },
  { fieldName: "bomUseDate", showName: "使用时间" },
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
const handleSelectionChange = (val: ApsOrderGoodsBom[]) => {
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
        <el-form-item label="订单ID" prop="orderId">
          <el-input v-model="queryForm.orderId" clearable placeholder="请输入订单ID" />
        </el-form-item>
        <el-form-item label="aps_goods_bom ID " prop="goodsBomId">
          <el-input v-model="queryForm.goodsBomId" clearable placeholder="请输入aps_goods_bom ID " />
        </el-form-item>
        <el-form-item label="商品" prop="goodsId">
          <el-input v-model="queryForm.goodsId" clearable placeholder="请输入商品ID" />
        </el-form-item>
        <el-form-item label="零件组ID" prop="groupId">
          <el-input v-model="queryForm.groupId" clearable placeholder="请输入零件组ID" />
        </el-form-item>
        <el-form-item label="商品" prop="bomId">
          <el-input v-model="queryForm.bomId" clearable placeholder="请输入商品ID" />
        </el-form-item>
        <el-form-item label="bom 编码" prop="bomCode">
          <el-input v-model="queryForm.bomCode" clearable placeholder="请输入bom 编码" />
        </el-form-item>
        <el-form-item label="bom 名称" prop="bomName">
          <el-input v-model="queryForm.bomName" clearable placeholder="请输入bom 名称" />
        </el-form-item>
        <el-form-item label="使用量" prop="bomUsage">
          <el-input v-model="queryForm.bomUsage" clearable placeholder="请输入使用量" />
        </el-form-item>
        <el-form-item label="单位" prop="bomUnit">
          <el-input v-model="queryForm.bomUnit" clearable placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="成本价" prop="bomCostPrice">
          <el-input v-model="queryForm.bomCostPrice" clearable placeholder="请输入成本价" />
        </el-form-item>
        <el-form-item label="单位" prop="bomCostPriceUnit">
          <el-input v-model="queryForm.bomCostPriceUnit" clearable placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="使用工位" prop="bomUseWorkStation">
          <el-input v-model="queryForm.bomUseWorkStation" clearable placeholder="请输入使用工位" />
        </el-form-item>
        <el-form-item label="使用表达式" prop="bomUseExpression">
          <el-input v-model="queryForm.bomUseExpression" clearable placeholder="请输入使用表达式" />
        </el-form-item>
        <el-form-item label="库存" prop="bomInventory">
          <el-input v-model="queryForm.bomInventory" clearable placeholder="请输入库存" />
        </el-form-item>
        <el-form-item label="是否关注" prop="isFollow">
          <el-input v-model="queryForm.isFollow" clearable placeholder="请输入是否关注" />
        </el-form-item>
        <el-form-item label="工厂" prop="factoryId">
          <el-input v-model="queryForm.factoryId" clearable placeholder="请输入工厂ID" />
        </el-form-item>
        <el-form-item label="状态" prop="goodsStatusId">
          <el-input v-model="queryForm.goodsStatusId" clearable placeholder="请输入状态" />
        </el-form-item>
        <el-form-item label="使用时间" prop="bomUseDate">
          <el-input v-model="queryForm.bomUseDate" clearable placeholder="请输入使用时间" />
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

