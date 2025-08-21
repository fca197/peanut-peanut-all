<script setup lang="ts">
import {onMounted, ref} from "vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import {ElTable} from "element-plus";
import {HeaderInfo, KVEntity, postResultInfo, postResultInfoList} from "@@/utils/common-js.ts"
import {
  type ApsOrderGoodsBomKittingVersionOrder
} from "./ApsOrderGoodsBomKittingVersionOrderType.ts"
import KittingRate from "@v/aps/ApsOrderGoodsBomKittingVersion/KittingRate.vue";
import {router} from "@/router";
import KittingMissingBom from "@v/aps/ApsOrderGoodsBomKittingVersion/KittingMissingBom.vue";
import {
  ApsOrderGoodsBomKittingVersion
} from "@v/aps/ApsOrderGoodsBomKittingVersion/ApsOrderGoodsBomKittingVersionType.ts";
import Decimal from "decimal.js";

const dtoUrl = ref<string>("/apsOrderGoodsBomKittingVersionOrder")
const documentTitle = ref<string>("齐套检查订单详情")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

const route = useRoute()

const kittingVersionId = ref<string | undefined>(route.params.id)
console.info("kittingVersionId ", kittingVersionId)

const apsOrderGoodsBomKittingVersion = ref<ApsOrderGoodsBomKittingVersion>({})
// 查询表格
const queryForm = ref<ApsOrderGoodsBomKittingVersionOrder>({
  kittingVersionId: kittingVersionId,
  orderId: undefined,
  orderNo: undefined,
  kittingRate: undefined,
  kittingStatus: undefined,
  kittingMissingBom: undefined,
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
const dataList = ref<ApsOrderGoodsBomKittingVersionOrder[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
  {fieldName: "id", showName: "序号"},
  {fieldName: "orderId", showName: "订单ID"},
  {fieldName: "orderNo", showName: "订单编号"},
  // {fieldName: "kittingRate", showName: "齐套率"},
  // {fieldName: "kittingStatus", showName: "齐套状态"},
  // {fieldName: "kittingMissingBom", showName: "缺失物料前10 [{id: label}]"}
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
const showData = (data: any) => {
  // console.info("data ", data)
  // tableBarRef.value?.showEditDialog(data.id)
  router.push("/aps/ApsOrderGoodsBomKittingVersionOrderBom/" + kittingVersionId.value + "/" + data.orderId)
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
const handleSelectionChange = (val: ApsOrderGoodsBomKittingVersionOrder[]) => {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}

const templateHeaderList = ref<KVEntity[]>([])

// 页面加载事件
onMounted(() => {
  getDataList()
  postResultInfoList("/apsOrderGoodsBomKittingVersion/queryByIdList",
    {idList: [kittingVersionId.value]}
  ).then(r => {
    apsOrderGoodsBomKittingVersion.value = r[0]
    templateHeaderList.value = r[0].templateHeaderList
  }).then(r => {

  })
})
</script>

<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-form v-model="queryForm" inline>
        <el-form-item label="订单号" prop="orderNo">
          <el-input v-model="queryForm.orderNo" clearable placeholder="请输入订单号"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="search" @click="getDataList">
            查询
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="search-wrapper" shadow="never">
      <el-row>
        <el-col :span="6">
          <el-card>
            <div class="total_title">
              <div class="total_title_all"></div>
              <div> 总齐套项数</div>
            </div>
            <div class="total_value">
              {{ apsOrderGoodsBomKittingVersion.orderCount }}
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card>
            <div class="total_title">
              <div class="total_title_success"></div>
              <div>
                已齐套项数
              </div>
            </div>
            <div class="total_value">
            <span>
                {{ apsOrderGoodsBomKittingVersion.kittingSuccessCount }}
            </span>
              <span class="total_value_desc total_value_success">
               / {{
                  (apsOrderGoodsBomKittingVersion.kittingSuccessCount / apsOrderGoodsBomKittingVersion.orderCount * 100).toFixed(2)
                }}%
            </span>

            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card>
            <div class="total_title ">
              <div class="total_title_fail"></div>
              <div>
                未齐套项数
              </div>
            </div>
            <div class="total_value">
              <span>
                {{ apsOrderGoodsBomKittingVersion.kittingFailCount }}
            </span>
              <span class="total_value_desc total_value_error">
               / {{
                  (apsOrderGoodsBomKittingVersion.kittingFailCount / apsOrderGoodsBomKittingVersion.orderCount * 100).toFixed(2)
                }}%
            </span>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card>
            <div class="total_title">
              <div class="total_title_fail"></div>
              <div>
                缺失零件总数
              </div>
            </div>
            <div class="total_value total_value_error">

              {{
                apsOrderGoodsBomKittingVersion.kittingMissingBom?.reduce((sum, item) => {
                  return sum.plus(new Decimal(item.value));
                }, new Decimal(0)).toFixed(6)
              }}
              <div class="total_value_info">详情</div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-card>
    <el-card shadow="never">
      <TableBar
        :document-title="documentTitle"
        :refresh-list="getDataList"
        :show-del-btn="false"
        :show-add-btn="false"
        :data-table-ref="dataTableRef"
        :multiple-selection="multipleSelection"
        ref="tableBarRef"
        :data-batch-delete-url="dataBatchDeleteUrl"
      />
      <ElTable ref="dataTableRef" :data="dataList" @selection-change="handleSelectionChange">
        <ElTableColumn type="selection"/>

        <ElTableColumn prop="numberIndex" label="制造序号" :width="80"/>
        <ElTableColumn
          v-for="h in headerList" :key="h.fieldName" :label="h.showName"
          :prop="h.fieldName" :width="200"
        />
        <ElTableColumn prop="kittingRate" label="齐套率">
          <template #default="scope">
            <KittingRate :kitting-rate="scope.row.kittingRate"/>
          </template>
        </ElTableColumn>
        <ElTableColumn prop="kittingMissingBom" label="缺失物料" :width="130">
          <template #default="scope">
            <KittingMissingBom :kitting-missing-bom="scope.row.kittingMissingBom"/>
          </template>
        </ElTableColumn>
        <ElTableColumn
          v-for="(h,i) in templateHeaderList" :key="h.label" :label="h.label"
          :width="200"
        >
          <template #default="scope">
            {{ scope.row["orderField" + ((i + 1) < 10 ? "0" + (i + 1) : i + 1)] }}
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

.total_title {
  text-align: center;
  font-size: 20px;
  font-weight: 300;
  margin: 5px 0 15px 0;
  line-height: 30px;
  display: inline-flex;
}

.total_title > div:first-child {
  margin-right: 10px;
}

.total_value {
  text-align: center;
  font-weight: 500;
  margin-top: 5px;
}

.total_title_all {
  content: " ";
  background-color: #00bb99;
  border: 1px solid #00bb99;
  line-height: 30px;
  width: 30px;
  border-radius: 15px;
}

.total_title_success {
  content: " ";
  background-color: #00bb99;
  border: 1px solid #00bb99;
  line-height: 30px;
  width: 30px;
  border-radius: 15px;
}

.total_title_fail {
  content: " ";
  background-color: lightcoral;
  border: 1px solid lightcoral;
  line-height: 30px;
  width: 30px;
  border-radius: 15px;
}

.total_title_bom {
  content: " ";
  background-color: lightcoral;
  border: 1px solid lightcoral;
  line-height: 30px;
  width: 30px;
  border-radius: 15px;
}

.total_value_info {
  margin-top: 15px;
  font-weight: 200;
  text-align: right;
  margin-right: 20px;
  display: none;
}

.el-card {
  margin: 10px 5px 10px 5px;
}

.total_value_desc {
  font-weight: 350;
  color: #64748b;
}

.total_value_error {
  text-align: center;
  font-weight: 500;
  margin-top: 5px;
  color: #d23131;
}

.total_value_success {
  text-align: center;
  font-weight: 500;
  margin-top: 5px;
  color: #00bb99;
}
</style>
