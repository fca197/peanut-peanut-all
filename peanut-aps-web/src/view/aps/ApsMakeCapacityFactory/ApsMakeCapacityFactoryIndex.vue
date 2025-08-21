<script setup lang="ts">
import { onMounted, ref } from "vue"
import AddEditFormVue from "./ApsMakeCapacityFactoryAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { ElTable } from "element-plus"
import { HeaderInfo, postResultInfo } from "@@/utils/common-js.ts"
import { type ApsMakeCapacityFactory } from "./ApsMakeCapacityFactoryType.ts"
import { Factory, queryFactoryList } from "@v/base/Factory/FactoryType.ts";

const dtoUrl = ref<string>("/apsMakeCapacityFactory")
const documentTitle = ref<string>("工厂产能")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsMakeCapacityFactory>({
  factoryId: undefined,
  makeCapacityQuantity: undefined,
  year: `${new Date().getFullYear()}`,
  month: undefined,
  dayMin1: undefined,
  dayMax1: undefined,
  dayMin2: undefined,
  dayMax2: undefined,
  dayMin3: undefined,
  dayMax3: undefined,
  dayMin4: undefined,
  dayMax4: undefined,
  dayMin5: undefined,
  dayMax5: undefined,
  dayMin6: undefined,
  dayMax6: undefined,
  dayMin7: undefined,
  dayMax7: undefined,
  dayMin8: undefined,
  dayMax8: undefined,
  dayMin9: undefined,
  dayMax9: undefined,
  dayMin10: undefined,
  dayMax10: undefined,
  dayMin11: undefined,
  dayMax11: undefined,
  dayMin12: undefined,
  dayMax12: undefined,
  dayMin13: undefined,
  dayMax13: undefined,
  dayMin14: undefined,
  dayMax14: undefined,
  dayMin15: undefined,
  dayMax15: undefined,
  dayMin16: undefined,
  dayMax16: undefined,
  dayMin17: undefined,
  dayMax17: undefined,
  dayMin18: undefined,
  dayMax18: undefined,
  dayMin19: undefined,
  dayMax19: undefined,
  dayMin20: undefined,
  dayMax20: undefined,
  dayMin21: undefined,
  dayMax21: undefined,
  dayMin22: undefined,
  dayMax22: undefined,
  dayMin23: undefined,
  dayMax23: undefined,
  dayMin24: undefined,
  dayMax24: undefined,
  dayMin25: undefined,
  dayMax25: undefined,
  dayMin26: undefined,
  dayMax26: undefined,
  dayMin27: undefined,
  dayMax27: undefined,
  dayMin28: undefined,
  dayMax28: undefined,
  dayMin29: undefined,
  dayMax29: undefined,
  dayMin30: undefined,
  dayMax30: undefined,
  dayMin31: undefined,
  dayMax31: undefined,
  id: undefined
})

// 表格选中的id
const multipleSelection = ref<(string | undefined) []>([])

// 表格
// const dataTableRef = ref<InstanceType<typeof ElTable> | null>(null)
const dataTableRef = ref({})
// 表格操作头
const tableBarRef = ref<InstanceType<typeof TableBar> | null>(null)
// 表格相关
const dataList = ref<ApsMakeCapacityFactory[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(12)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([])

const factoryList = ref<Factory[]>([])
// 获取表格内数据
function getDataList() {
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
function handleSelectionChange(val: ApsMakeCapacityFactory[]) {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}
const handleDelete = (val: ApsMakeCapacityFactory) => {

}
// 页面加载事件
onMounted(() => {
  getDataList()
  queryFactoryList().then(r => factoryList.value = r)
})

</script>

<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-form v-model="queryForm" inline>
        <el-form-item label="工厂" prop="factoryId">
          <el-select v-model="queryForm.factoryId" clearable style="width: 200px">
            <el-option v-for="f in factoryList" :label="f.factoryName" :value="f.id" :key="f.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="年份" prop="factoryId">
          <el-select v-model="queryForm.year" clearable style="width: 200px">
            <el-option
              v-for="f in [-1, 0, 1, 2].map(t=> (new Date().getFullYear()+t) + '')" :label="f" :value="f"
              :key="f"
            />
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
        :dialog-with="800"
      />
      <ElTable ref="dataTableRef" :data="dataList" stripe @selection-change="handleSelectionChange">
        <ElTableColumn type="selection"/>
        <ElTableColumn v-for="h in headerList" :key="h.fieldName" :label="h.showName" :prop="h.fieldName" :min-width="h.width"/>
        <ElTableColumn v-for="(item,index) in  31" :key="index" :label="item+'日'" :prop="''+item" width="80"
                       align="center">
          <template #default="scope">
            <span>{{ scope.row["dayMin" + item] }} / {{ scope.row["dayMax" + item] }}</span>
          </template>
        </ElTableColumn>
      </ElTable>

      <el-row class="paginationDiv">
        <el-pagination
          background
          v-model:current-page="currentPageNum"
          v-model:page-size="currentPageSize"
          layout="total, sizes, prev, pager, next"
          :page-sizes="[12,24,36]"
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

