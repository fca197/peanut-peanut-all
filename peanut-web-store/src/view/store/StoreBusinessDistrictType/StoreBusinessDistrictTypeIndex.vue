<script lang="ts" setup>
import {onMounted, ref} from "vue"
import AddEditFormVue from "./StoreBusinessDistrictTypeAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import {ElTable} from "element-plus";
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {type StoreBusinessDistrictType} from "./StoreBusinessDistrictTypeType.ts"

const dtoUrl = ref<string>("/storeBusinessDistrictType")
const documentTitle = ref<string>("商圈类型")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)
const loadEntity = ref<boolean>(true)
// 查询表格
const queryForm = ref<StoreBusinessDistrictType>({
  businessDistrictTypeName: undefined,
  businessDistrictTypeCode: undefined,
  businessDistrictTypeDesc: undefined,
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
const dataList = ref<StoreBusinessDistrictType[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
  {fieldName: "id", showName: "序号"},
  {fieldName: "businessDistrictTypeName", showName: "类型名称"},
  {fieldName: "businessDistrictTypeCode", showName: "类型编码"},
  {fieldName: "businessDistrictTypeDesc", showName: "类型描述"},
])

// 获取表格内数据
const getDataList = () => {
  const req = {
    pageSize: currentPageSize.value,
    pageNum: currentPageNum.value,
    data: queryForm.value
  }
  console.info("getDataList {}", req)
  loadEntity.value = true
  postResultInfo(`${dtoUrl.value}/queryPageList`, req)
  .then((t) => {
    dataList.value = t.data.dataList
    tableTotal.value = Number.parseInt(t.data.total)
    headerList.value = t.data.headerList
    loadEntity.value = false
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
const handleSelectionChange = (val: StoreBusinessDistrictType[]) => {
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
        <el-form-item label="类型名称" prop="businessDistrictTypeName">
          <el-input
              v-model="queryForm.businessDistrictTypeName"
              clearable
              placeholder="请输入类型名称"
          />
        </el-form-item>
        <el-form-item label="类型编码" prop="businessDistrictTypeCode">
          <el-input
              v-model="queryForm.businessDistrictTypeCode"
              clearable
              placeholder="请输入类型编码"
          />
        </el-form-item>
        <el-form-item label="类型描述" prop="businessDistrictTypeDesc">
          <el-input
              v-model="queryForm.businessDistrictTypeDesc"
              clearable
              placeholder="请输入类型描述"
          />
        </el-form-item>
        <el-form-item>
          <el-button icon="search" type="primary" @click="getDataList">
            查询
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card shadow="never">
      <TableBar
          ref="tableBarRef"
          :add-component="AddEditFormVue"
          :data-batch-delete-url="dataBatchDeleteUrl"
          :data-table-ref="dataTableRef"
          :document-title="documentTitle"
          :multiple-selection="multipleSelection"
          :refresh-list="getDataList"
      />
      <ElTable ref="dataTableRef" v-loading="loadEntity" :data="dataList" stripe @selection-change="handleSelectionChange">
        <ElTableColumn type="selection"/>
        <ElTableColumn
            v-for="h in headerList" :key="h.fieldName" :label="h.showName"
            :min-width="h.width" :prop="h.fieldName"
        />
        <ElTableColumn fixed="right" label="操作" width="150px">
          <template #default="scope">
            <el-button
                icon="edit"
                type="warning"
                @click="editData(scope.row)"
            >
              编辑
            </el-button>
          </template>
        </ElTableColumn>
      </ElTable>
      <el-row class="paginationDiv">
        <el-pagination
            v-model:current-page="currentPageNum"
            v-model:page-size="currentPageSize"
            :total="tableTotal"
            background
            layout="total, sizes, prev, pager, next"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
        />
      </el-row>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>

</style>

