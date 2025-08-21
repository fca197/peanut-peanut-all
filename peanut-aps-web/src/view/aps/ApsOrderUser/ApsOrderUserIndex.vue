<script setup lang="ts">
import {ref, onMounted} from "vue"
import AddEditFormVue from "./ApsOrderUserAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { ElTable } from "element-plus";
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {type ApsOrderUser} from "./ApsOrderUserType.ts"

const dtoUrl = ref<string>("/apsOrderUser")
const documentTitle = ref<string>("订单用户")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsOrderUser>({
  orderId: undefined,
  userName: undefined,
  userPhone: undefined,
  userSex: undefined,
  countryCode: undefined,
  provinceCode: undefined,
  cityCode: undefined,
  areaCode: undefined,
  userAddress: undefined,
  userRemark: undefined,
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
const dataList = ref<ApsOrderUser[] >([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
 {fieldName: "id", showName: "序号"},
  { fieldName: "orderId", showName: "" },
  { fieldName: "userName", showName: "" },
  { fieldName: "userPhone", showName: "" },
  { fieldName: "userSex", showName: "" },
  { fieldName: "countryCode", showName: "" },
  { fieldName: "provinceCode", showName: "" },
  { fieldName: "cityCode", showName: "" },
  { fieldName: "areaCode", showName: "" },
  { fieldName: "userAddress", showName: "" },
  { fieldName: "userRemark", showName: "" },
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
const handleSelectionChange = (val: ApsOrderUser[]) => {
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
        <el-form-item label="${column.comment}" prop="userName">
          <el-input v-model="queryForm.userName" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="userPhone">
          <el-input v-model="queryForm.userPhone" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="userSex">
          <el-input v-model="queryForm.userSex" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="countryCode">
          <el-input v-model="queryForm.countryCode" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="provinceCode">
          <el-input v-model="queryForm.provinceCode" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="cityCode">
          <el-input v-model="queryForm.cityCode" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="areaCode">
          <el-input v-model="queryForm.areaCode" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="userAddress">
          <el-input v-model="queryForm.userAddress" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="userRemark">
          <el-input v-model="queryForm.userRemark" clearable placeholder="请输入${column.comment}" />
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

