<script setup lang="ts">
import { onMounted, ref } from "vue"
import AddEditFormVue from "./ApsOrderGoodsSaleConfigHistoryAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { ElTable } from "element-plus";
import { HeaderInfo, postNoResult, postResultInfo } from "@@/utils/common-js.ts"
import { type ApsOrderGoodsSaleConfigHistory } from "./ApsOrderGoodsSaleConfigHistoryType.ts"
import { Factory, queryFactoryList } from "@v/base/Factory/FactoryType.ts";
import { ApsGoods, queryGoodsList } from "@v/aps/ApsGoods/ApsGoodsType.ts";

const dtoUrl = ref<string>("/apsOrderGoodsSaleHistory")
const documentTitle = ref<string>("销售规划订单历史销售占比")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsOrderGoodsSaleConfigHistory>({
  factoryId: undefined,
  goodsId: undefined,
  goodsName: undefined,
  saleParentId: undefined,
  saleParentConfigName: undefined,
  saleConfigId: undefined,
  saleConfigName: undefined,
  year: undefined,
  id: undefined
})

// 表格选中的id
const multipleSelection = ref<(string | undefined)[]>([])

const monthList = ref<string[]>([ "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" ])
// 表格
// const dataTableRef = ref<InstanceType<typeof ElTable> | null>(null)
const dataTableRef = ref({})
// 表格操作头
const tableBarRef = ref<InstanceType<typeof TableBar> | null>(null)
// 表格相关
const dataList = ref<ApsOrderGoodsSaleConfigHistory[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
  { fieldName: "id", showName: "序号" },
  { fieldName: "goodsName", showName: "商品名称" },
  { fieldName: "saleParentConfigName", showName: "销售组名称" },
  { fieldName: "saleConfigName", showName: "销售组名称" },
  { fieldName: "year", showName: "年份" },
  { fieldName: "monthCount01", showName: "1月销售数量" },
  { fieldName: "monthRatio01", showName: "1月销售占比" },
  { fieldName: "monthCount02", showName: "2月销售数量" },
  { fieldName: "monthRatio02", showName: "2月销售占比" },
  { fieldName: "monthCount03", showName: "3月销售数量" },
  { fieldName: "monthRatio03", showName: "3月销售占比" },
  { fieldName: "monthCount04", showName: "4月销售数量" },
  { fieldName: "monthRatio04", showName: "4月销售占比" },
  { fieldName: "monthCount05", showName: "5月销售数量" },
  { fieldName: "monthRatio05", showName: "5月销售占比" },
  { fieldName: "monthCount06", showName: "6月销售数量" },
  { fieldName: "monthRatio06", showName: "6月销售占比" },
  { fieldName: "monthCount07", showName: "7月销售数量" },
  { fieldName: "monthRatio07", showName: "7月销售占比" },
  { fieldName: "monthCount08", showName: "8月销售数量" },
  { fieldName: "monthRatio08", showName: "8月销售占比" },
  { fieldName: "monthCount09", showName: "9月销售数量" },
  { fieldName: "monthRatio09", showName: "9月销售占比" },
  { fieldName: "monthCount10", showName: "10月销售数量" },
  { fieldName: "monthRatio10", showName: "10月销售占比" },
  { fieldName: "monthCount11", showName: "11月销售数量" },
  { fieldName: "monthRatio11", showName: "11月销售占比" },
  { fieldName: "monthCount12", showName: "12月销售数量" },
  { fieldName: "monthRatio12", showName: "12月销售占比" }
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
// const editData = (data: any) => {
//   // console.info("data ", data)
//   tableBarRef.value?.showEditDialog(data.id)
// }
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
const handleSelectionChange = (val: ApsOrderGoodsSaleConfigHistory[]) => {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}
const factoryList = ref<Factory[]>([])
const apsGoodsList = ref<ApsGoods[]>([])

const refreshHistory = (type) => {

  postNoResult("/apsOrderGoodsSaleHistory/selectOrder2History",
    { selectType: type === 1 ? "LAST_MONTH" : "CURRENT_MONTH" }, "刷新成功", () => {
      getDataList();
    })
}
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
          <el-select v-model="queryForm.factoryId" clearable style="width: 200px"
                     @change="queryForm.goodsId = undefined">
            <el-option v-for="f in factoryList" :label="f.factoryName" :value="f.id" :key="f.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="商品" prop="goodsId">
          <el-select v-model="queryForm.goodsId" clearable style="width: 200px">
            <el-option
              v-for="f in apsGoodsList.filter(t=> queryForm.factoryId === undefined || t.factoryId === queryForm.factoryId)"
              :label="f.goodsName"
              :value="f.id" :key="f.id"/>
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
        :show-del-btn="false"
        :show-add-btn="false"
        :document-title="documentTitle"
        :add-component="AddEditFormVue"
        :refresh-list="getDataList"
        :data-table-ref="dataTableRef"
        :multiple-selection="multipleSelection"
        ref="tableBarRef"
        :data-batch-delete-url="dataBatchDeleteUrl"
      >
        <template #otherBtn>
          <el-button type="danger" icon="refresh" @click="refreshHistory(1)">
            上月
          </el-button>
          <el-button type="primary" icon="refresh" @click="refreshHistory(0)">
            当月
          </el-button>
        </template>
      </TableBar>
      <ElTable ref="dataTableRef" :data="dataList" stripe @selection-change="handleSelectionChange">
        <ElTableColumn type="selection"/>
        <ElTableColumn v-for="h in headerList" :key="h.fieldName" :label="h.showName"
                       :prop="h.fieldName" :width="h.width+'px'" align="center"/>
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

