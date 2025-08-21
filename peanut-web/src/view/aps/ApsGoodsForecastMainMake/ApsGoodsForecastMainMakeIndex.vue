<script setup lang="ts">
import { onMounted, ref } from "vue"
import AddEditFormVue from "./ApsGoodsForecastMainMakeAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { ElTable } from "element-plus";
import { HeaderInfo, postResultInfo } from "@@/utils/common-js.ts"
import { type ApsGoodsForecastMainMake } from "./ApsGoodsForecastMainMakeType.ts"
import { Factory, queryFactoryList } from "@v/base/Factory/FactoryType.ts";
import { ApsGoods, queryGoodsList } from "@v/aps/ApsGoods/ApsGoodsType.ts";

const dtoUrl = ref<string>("/apsGoodsForecastMainMake")
const documentTitle = ref<string>("预测生产主")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsGoodsForecastMainMake>({
  goodsId: undefined,
  forecastMakeMainNo: undefined,
  forecastMakeMainName: undefined,
  forecastMakeMainBeginDate: undefined,
  forecastMakeMainEndDate: undefined,
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
const dataList = ref<ApsGoodsForecastMainMake[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
  { fieldName: "id", showName: "序号" },
  { fieldName: "goodsId", showName: "商品" },
  { fieldName: "forecastMakeMainNo", showName: "编码" },
  { fieldName: "forecastMakeMainName", showName: "名称" },
  { fieldName: "forecastMakeMainBeginDate", showName: "开始时间" },
  { fieldName: "forecastMakeMainEndDate", showName: "结束时间" },
  { fieldName: "factoryId", showName: "工厂" }
])

const factoryList = ref<Factory[]>([])
const goodsList = ref<ApsGoods []>([])
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

const router = useRouter()
// table点击事件
const showData = (data: any) => {
  // console.info("data ", data)
  // tableBarRef.value?.showEditDialog(data.id)
  router.push(`/aps/ApsGoodsForecastMainMake/result/${data.id}`)
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
const handleSelectionChange = (val: ApsGoodsForecastMainMake[]) => {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}

// 页面加载事件
onMounted(() => {
  getDataList()
  queryFactoryList().then(r => factoryList.value = r)
  queryGoodsList().then(r => goodsList.value = r)
})

</script>

<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-form v-model="queryForm" inline>

        <el-form-item label="工厂" prop="factoryId">
          <el-select v-model="queryForm.factoryId" clearable style="width: 200px" @change="queryForm.goodsId=undefined">
            <el-option v-for="f in factoryList" :key="f.id" :value="f.id" :label="f.factoryName"/>
          </el-select>
        </el-form-item>
        <el-form-item label="商品" prop="goodsId">
          <el-select v-model="queryForm.goodsId" clearable style="width: 200px">
            <el-option
              v-for="g in goodsList.filter(g =>  queryForm.factoryId === undefined || g.factoryId === queryForm.factoryId)" :key="g.id"
              :label="g.goodsName" :value="g.id"
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
        :show-add-btn="false"
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
        <ElTableColumn v-for="h in headerList" :label="h.showName" :prop="h.fieldName"/>
        <ElTableColumn fixed="right" label="操作" width="150px">
          <template #default="scope">
            <el-button
              type="warning"
              icon="Histogram"
              @click="showData(scope.row)"
            >
              数据
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

