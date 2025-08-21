<script setup lang="ts">
import { ref } from "vue"
import AddEditFormVue from "./ApsBomSupplierAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { HeaderInfo, postResultInfo } from "@@/utils/common-js.ts"
import { type ApsBomSupplier, supplierStatusList } from "./ApsBomSupplierType.ts"

const dtoUrl = ref<string>("/apsBomSupplier")
const documentTitle = ref<string>("供应商")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

const queryForm = ref({
  bomSupplierName: undefined,
  bomSupplierCode: undefined,
  bomSupplierPhone: undefined,
  bomSupplierTel: undefined,
  bomSupplierEmail: undefined,
  provinceCode: undefined,
  cityCode: undefined,
  areaCode: undefined,
  bomSupplierAddr: undefined,
  bomSupplierRemark: undefined,
  supplierStatus: undefined,
  id: undefined
})

const multipleSelection = ref<(string | undefined)[]>([])

function handleSelectionChange(val: ApsBomSupplier[]) {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}

const dataTableRef = ref({})
const dataList = ref([])
const tableBarRef = ref({})

const currentPageNum = ref(1)
const currentPageSize = ref(10)
const tableTotal = ref(0)
const headerList = ref<HeaderInfo[]>([])

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

onMounted(() => {
  getDataList()
})

function editData(data: any) {
  // console.info("data ", data)
  tableBarRef.value?.showEditDialog(data.id)
}

function handleSizeChange(val: number) {
  currentPageSize.value = val
  getDataList()
}

function handleCurrentChange(val: number) {
  currentPageNum.value = val
  getDataList()
}
</script>

<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-form v-model="queryForm" inline>
        <el-form-item label="名称" prop="bomSupplierName">
          <el-input v-model="queryForm.bomSupplierName" clearable placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="编号" prop="bomSupplierCode">
          <el-input v-model="queryForm.bomSupplierCode" clearable placeholder="请输入编号" />
        </el-form-item>
        <el-form-item label="手机" prop="bomSupplierPhone">
          <el-input v-model="queryForm.bomSupplierPhone" clearable placeholder="请输入手机" />
        </el-form-item>
        <el-form-item label="状态" prop="supplierStatus">
          <el-select v-model="queryForm.supplierStatus" style="width: 200px">
            <el-option v-for="kv in supplierStatusList" :label="kv.label" :value="kv.value" :key="kv.value" />
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
:document-title="documentTitle" :add-component="AddEditFormVue" :refresh-list="getDataList"
        :data-table-ref="dataTableRef" :multiple-selection="multipleSelection" ref="tableBarRef"
        :data-batch-delete-url="dataBatchDeleteUrl" />
      <ElTable ref="dataTableRef" :data="dataList" stripe @selection-change="handleSelectionChange">
        <ElTableColumn type="selection" />
        <ElTableColumn v-for="h in headerList" :key="h.fieldName" :label="h.showName" :prop="h.fieldName" :min-width="h.width"/>
        <ElTableColumn fixed="right" label="操作" width="150px">
          <template #default="scope">
            <el-button type="warning" icon="edit" @click="editData(scope.row)">
              编辑
            </el-button>
          </template>
        </ElTableColumn>
      </ElTable>
      <el-row class="paginationDiv">
        <el-pagination background v-model:current-page="currentPageNum" v-model:page-size="currentPageSize"
          layout="total, sizes, prev, pager, next" :total="tableTotal" @size-change="handleSizeChange"
          @current-change="handleCurrentChange" />
      </el-row>
    </el-card>
  </div>
</template>

<style
  scoped
  lang="scss"
></style>
