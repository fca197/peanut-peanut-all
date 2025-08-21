<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-form v-model="queryForm" inline>
        <el-form-item label="物流路径id" prop="logisticsPathId">
          <el-input v-model="queryForm.logisticsPathId" clearable placeholder="请输入物流路径id" />
        </el-form-item>
        <el-form-item label="省编码" prop="provinceCode">
          <el-input v-model="queryForm.provinceCode" clearable placeholder="请输入省编码" />
        </el-form-item>
        <el-form-item label="省名称" prop="provinceName">
          <el-input v-model="queryForm.provinceName" clearable placeholder="请输入省名称" />
        </el-form-item>
        <el-form-item label="市编码" prop="cityCode">
          <el-input v-model="queryForm.cityCode" clearable placeholder="请输入市编码" />
        </el-form-item>
        <el-form-item label="市名称" prop="cityName">
          <el-input v-model="queryForm.cityName" clearable placeholder="请输入市名称" />
        </el-form-item>
        <el-form-item label="运输天数" prop="transportDay">
          <el-input v-model="queryForm.transportDay" clearable placeholder="请输入运输天数" />
        </el-form-item>
        <el-form-item label="是否默认 0 否,1 是" prop="isDefault">
          <el-input v-model="queryForm.isDefault" clearable placeholder="请输入是否默认 0 否,1 是" />
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

<script setup lang="ts">
import {ref, onMounted} from "vue"
import AddEditFormVue from "./ApsLogisticsPathItemAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { ElTable } from "element-plus"
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {type ApsLogisticsPathItem} from "./ApsLogisticsPathItemType.ts"

const dtoUrl = ref<string>("/apsLogisticsPathItem")
const documentTitle = ref<string>("物流路详情径")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsLogisticsPathItem>({
  logisticsPathId:  undefined,
  provinceCode:  undefined,
  provinceName:  undefined,
  cityCode:  undefined,
  cityName:  undefined,
  transportDay:  undefined,
  isDefault:  undefined,
  factoryId:  undefined,
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
const dataList = ref<ApsLogisticsPathItem[] >([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([])


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
function handleSelectionChange(val: ApsLogisticsPathItem[]) {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}

// 页面加载事件
onMounted(() => {
  getDataList()
})

</script>

<style scoped lang="scss">

</style>

