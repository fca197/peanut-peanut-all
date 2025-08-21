<script setup lang="ts">
import { onMounted, ref } from "vue"
import AddEditFormVue from "./ApsGoodsBomBuyPlanAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { ElTable } from "element-plus"
import { HeaderInfo, postResultInfo } from "@@/utils/common-js.ts"
import { type ApsGoodsBomBuyPlan } from "./ApsGoodsBomBuyPlanType.ts"
import { isFollowList } from "@v/aps/ApsGoodsBom/ApsGoodsBomType.ts";

const dtoUrl = ref<string>("/apsGoodsBomBuyPlan")
const documentTitle = ref<string>("BOM 购买计划")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsGoodsBomBuyPlan>({
  planName: undefined,
  planTotalAmount: undefined,
  planSource: undefined,
  planRemark: undefined,
  buyPlanType: undefined,
  isFollow: undefined,
  bomUseDate: undefined,
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
const dataList = ref<ApsGoodsBomBuyPlan[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
  { fieldName: "id", showName: "序号" },
  { fieldName: "planName", showName: "计划名称" },
  { fieldName: "planTotalAmount", showName: "总价" },
  { fieldName: "planSource", showName: "计划来源" },
  { fieldName: "planRemark", showName: "计划备注" },
  { fieldName: "buyPlanType", showName: "购买类型" },
  { fieldName: "isFollow", showName: "是否关注" },
  { fieldName: "bomUseDate", showName: "日期" },
])


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
function handleSelectionChange(val: ApsGoodsBomBuyPlan[]) {
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
        <el-form-item label="计划名称" prop="planName">
          <el-input v-model="queryForm.planName" clearable placeholder="请输入计划名称"/>
        </el-form-item>
        <el-form-item label="总价" prop="planTotalAmount">
          <el-input v-model="queryForm.planTotalAmount" clearable placeholder="请输入总价"/>
        </el-form-item>
        <el-form-item label="计划来源" prop="planSource">
          <el-input v-model="queryForm.planSource" clearable placeholder="请输入计划来源"/>
        </el-form-item>
        <el-form-item label="是否关注" prop="isFollow">
          <el-select v-model="queryForm.isFollow" clearable placeholder="请选择" style="width: 100px">
            <el-option v-for="f in isFollowList" :key="f.value" :value="f.value" :label="f.label"/>
          </el-select>
        </el-form-item>
        <el-form-item label="使用日期" prop="bomUseDate">
          <el-date-picker
            v-model="queryForm.bomUseDate" clearable placeholder="请选择日期"
            type="date" value-format="YYYY-MM" format="YYYY-MM"
          />
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
        <ElTableColumn v-for="h in headerList" :key="h.fieldName" :label="h.showName" :prop="h.fieldName"
                       :min-width="h.width"/>
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

