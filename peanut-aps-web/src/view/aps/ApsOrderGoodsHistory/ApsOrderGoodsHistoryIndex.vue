<script setup lang="ts">
import { onMounted, ref } from "vue"
import AddEditFormVue from "./ApsOrderGoodsHistoryAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { ElTable } from "element-plus";
import { HeaderInfo, postResultInfo } from "@@/utils/common-js.ts"
import { type ApsOrderGoodsHistory } from "./ApsOrderGoodsHistoryType.ts"
import { Factory, queryFactoryList } from "@v/base/Factory/FactoryType.ts";
import { ApsGoods, queryGoodsList } from "@v/aps/ApsGoods/ApsGoodsType.ts";

const dtoUrl = ref<string>("/apsOrderGoodsHistory")
const documentTitle = ref<string>("历史订单记录")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsOrderGoodsHistory>({
  factoryId: undefined,
  goodsId: undefined,
  goodsName: undefined,
  year: undefined,
  monthCount01: undefined,
  monthRatio01: undefined,
  monthCount02: undefined,
  monthRatio02: undefined,
  monthCount03: undefined,
  monthRatio03: undefined,
  monthCount04: undefined,
  monthRatio04: undefined,
  monthCount05: undefined,
  monthRatio05: undefined,
  monthCount06: undefined,
  monthRatio06: undefined,
  monthCount07: undefined,
  monthRatio07: undefined,
  monthCount08: undefined,
  monthRatio08: undefined,
  monthCount09: undefined,
  monthRatio09: undefined,
  monthCount10: undefined,
  monthRatio10: undefined,
  monthCount11: undefined,
  monthRatio11: undefined,
  monthCount12: undefined,
  monthRatio12: undefined,
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
const dataList = ref<ApsOrderGoodsHistory[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([])
const monthList = ref<string[]>(["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"])

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
const handleSelectionChange = (val: ApsOrderGoodsHistory[]) => {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}

const factoryList = ref<Factory[]>([])
const apsGoodsList = ref<ApsGoods[]>([])
// 页面加载事件
onMounted(() => {
  getDataList()
  queryFactoryList().then(r => factoryList.value = r)
  queryGoodsList().then(r => apsGoodsList.value = r)
})

</script>

<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-form v-model="queryForm" inline>
        <el-form-item label="工厂" prop="factoryId">
          <el-select v-model="queryForm.factoryId" clearable @change="queryForm.goodsId = undefined"
                     style="width: 200px">
            <el-option v-for="f in factoryList" :k="f.id" :value="f.id" :label="f.factoryName"/>
          </el-select>
        </el-form-item>
        <el-form-item label="商品" prop="goodsId">
          <el-select v-model="queryForm.goodsId" clearable style="width: 200px">
            <el-option
              v-for="f in apsGoodsList.filter(t=> queryForm.factoryId === undefined || t.factoryId === queryForm.factoryId)" :k="f.id" :value="f.id"
              :label="f.goodsName"/>
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
        <el-table-column v-for="m in monthList" width="200" :key="m+'month'" :label="m+'月'">
          <template #default="scope">
            {{
              scope.row['monthRatio' + m] !== null ? scope.row['monthRatio' + m] + '%' : ''
            }}/{{ scope.row['monthCount' + m] !== null ? parseInt(scope.row['monthCount' + m]) : '' }}
          </template>
        </el-table-column>
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

