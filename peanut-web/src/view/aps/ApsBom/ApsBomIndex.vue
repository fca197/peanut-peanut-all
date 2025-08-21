<script setup lang="ts">
import type { HeaderInfo } from "@@/utils/common-js.ts"
import type { ApsBom } from "./ApsBomType.ts"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { postResultInfo } from "@@/utils/common-js.ts"
import { ElTable } from "element-plus"
import { ref } from "vue"
import { supplyModeList } from "./ApsBomType.ts"
import { ApsBomGroup, apsGroupDefaultProps, queryApsBomGroupTree } from "@v/aps/ApsBomGroup/ApsBomGroupType.ts"
import AddEditFormVue from "./ApsBomAddEditForm.vue"


const dtoUrl = ref<string>("/apsBom")
const documentTitle = ref<string>("BOM 清单")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

const queryForm = ref({
  bomCode: undefined,
  bomName: undefined,
  bomCostPrice: undefined,
  bomCostPriceUnit: undefined,
  bomInventory: undefined,
  groupId: undefined,
  supplyMode: undefined,
  useUnit: undefined,
  bomUnit: undefined,
  produceProcessId: undefined,
  deliveryCycleDay: undefined,
  apsBomSupplierId: undefined,
  id: undefined
})

const multipleSelection = ref<(string | undefined)[]>([])
const dataTableRef = ref<any>({})
// const dataTableRef = ref<InstanceType<typeof ElTable> | null>(null)
const tableBarRef = ref<InstanceType<typeof TableBar> | null>(null)

const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([])

const dataList = ref<ApsBom[]>([])


function handleSelectionChange(val: ApsBom[]) {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}


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

const apsBomGroupList = ref<ApsBomGroup[]>([])

onMounted(() => {
  getDataList()
  queryApsBomGroupTree().then(t => apsBomGroupList.value = t)
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
        <el-form-item label="bom 编码" prop="bomCode">
          <el-input v-model="queryForm.bomCode" clearable placeholder="请输入bom 编码" />
        </el-form-item>
        <el-form-item label="bom 名称" prop="bomName">
          <el-input v-model="queryForm.bomName" clearable placeholder="请输入bom 名称" />
        </el-form-item>
        <el-form-item label="组ID" prop="groupId">
          <el-tree-select
            :props="apsGroupDefaultProps" style="width: 200px" node-key="id" :data="apsBomGroupList"
            v-model="queryForm.groupId" clearable placeholder="请输入组ID" />
        </el-form-item>
        <el-form-item label="供给方式" prop="supplyMode">
          <el-select v-model="queryForm.supplyMode" clearable style="width: 200px">
            <el-option v-for="s in supplyModeList" :label="s.label" :value="s.value" :key="s.value" />
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
        :data-batch-delete-url="dataBatchDeleteUrl" down-load-url="/apsBom/exportQueryPageList" upload-url="/upload" />
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
        <el-pagination
          background v-model:current-page="currentPageNum" v-model:page-size="currentPageSize"
          layout="total, sizes, prev, pager, next" :total="tableTotal" @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </el-row>
    </el-card>
  </div>
</template>
<style scoped lang="scss"></style>
