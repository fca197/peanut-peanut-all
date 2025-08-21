<script setup lang="ts">
import {onMounted, ref} from "vue"
import AddEditFormVue from "./ApsOrderGoodsBomKittingVersionAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import {ElTable} from "element-plus";
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {type ApsOrderGoodsBomKittingVersion} from "./ApsOrderGoodsBomKittingVersionType.ts"
import {Factory, queryFactoryList} from "@v/base/Factory/FactoryType.ts";
import {router} from "@/router";
import KittingRate from "@v/aps/ApsOrderGoodsBomKittingVersion/KittingRate.vue";
import {useRoute} from "vue-router";
import KittingMissingBom from "@v/aps/ApsOrderGoodsBomKittingVersion/KittingMissingBom.vue";

const dtoUrl = ref<string>("/apsOrderGoodsBomKittingVersion")
const documentTitle = ref<string>("齐套检查版本")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)


const route = useRoute()
const bizKey = ref<String>(route.params.bizKey)
console.info("bizKey ", bizKey)

// 查询表格
const queryForm = ref<ApsOrderGoodsBomKittingVersion>({
  kittingVersionNo: undefined,
  kittingVersionName: undefined,
  kittingVersionSource: undefined,
  orderCount: undefined,
  kittingSuccessCount: undefined,
  kittingFailCount: undefined,
  kittingRate: undefined,
  kittingStatus: undefined,
  kittingMissingBom: undefined,
  bizId: bizKey,
  createDate: undefined,
  factoryId: undefined,
  goodsStatusId: undefined,
  bomUseDate: undefined,
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
const dataList = ref<ApsOrderGoodsBomKittingVersion[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([])
const factoryList = ref<Factory[]>([])
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
    dataList.value.forEach(t => {
      // t.kittingRate = new Decimal(t.kittingRate)
    })
    tableTotal.value = Number.parseInt(t.data.total)
    headerList.value = t.data.headerList
  })
}

// table点击事件
const showData = (data: any) => {
  // console.info("data ", data)
  // tableBarRef.value?.showEditDialog(data.id)
  router.push("/aps/ApsOrderGoodsBomKittingVersionOrder/" + data.id)
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
const handleSelectionChange = (val: ApsOrderGoodsBomKittingVersion[]) => {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
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
        <el-form-item label="齐套版本编码" prop="kittingVersionNo">
          <el-input
            v-model="queryForm.kittingVersionNo" clearable
            placeholder="请输入齐套版本编码"/>
        </el-form-item>
        <el-form-item label="齐套版本名称" prop="kittingVersionName">
          <el-input
            v-model="queryForm.kittingVersionName" clearable
            placeholder="请输入齐套版本名称"/>
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
        :show-add-btn="false"
        :add-component="AddEditFormVue"
        :refresh-list="getDataList"
        :data-table-ref="dataTableRef"
        :multiple-selection="multipleSelection"
        ref="tableBarRef"
        :data-batch-delete-url="dataBatchDeleteUrl"
      />
      <ElTable ref="dataTableRef" :data="dataList" stripe @selection-change="handleSelectionChange">
        <ElTableColumn
          type="selection" prop="id"
        />
        <ElTableColumn
          v-for="h in headerList" :key="h.fieldName" :label="h.showName"
          :min-width="h.width"
          :prop="h.fieldName"
        />
        <ElTableColumn prop="kittingRate" label="齐套率">
          <template #default="scope">
            <KittingRate :kitting-rate="scope.row.kittingRate"/>
          </template>
        </ElTableColumn>
        <ElTableColumn prop="kittingStatus" label="齐套状态"/>
        <ElTableColumn prop="kittingMissingBom" label="缺失物料" :width="130">
          <template #default="scope">
            <KittingMissingBom :kitting-missing-bom="scope.row.kittingMissingBom"/>
          </template>
        </ElTableColumn>
        <ElTableColumn fixed="right" label="操作" width="150px" style="float: right">
          <template #default="scope">
            <el-button
              type="primary"
              icon="Histogram"
              @click="showData(scope.row)"
            >
              详情
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
