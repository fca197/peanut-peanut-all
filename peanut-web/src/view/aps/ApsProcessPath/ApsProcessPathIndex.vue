<script setup lang="ts">
import {onMounted, ref} from "vue"
import AddEditFormVue from "./ApsProcessPathAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import {ElTable} from "element-plus"
import {HeaderInfo, postNoResult, postResultInfo} from "@@/utils/common-js.ts"
import {type ApsProcessPath} from "./ApsProcessPathType.ts"
import {Factory, queryFactoryList} from "@v/base/Factory/FactoryType.ts";

const dtoUrl = ref<string>("/apsProcessPath")
const documentTitle = ref<string>("流程路径")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsProcessPath>({
  processPathCode: undefined,
  processPathName: undefined,
  processPathRemark: undefined,
  isDefault: undefined,
  factoryId: undefined,
  id: undefined,
  pathRoomList: []
})

// 表格选中的id
const multipleSelection = ref<(string | undefined) []>([])

// 表格
// const dataTableRef = ref<InstanceType<typeof ElTable> | null>(null)
const dataTableRef = ref({})
// 表格操作头
const tableBarRef = ref<InstanceType<typeof TableBar> | null>(null)
// 表格相关
const dataList = ref<ApsProcessPath[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
  {fieldName: "id", showName: "序号"},
  {fieldName: "processPathCode", showName: ""},
  {fieldName: "processPathName", showName: ""},
  {fieldName: "processPathRemark", showName: ""},
  {fieldName: "isDefault", showName: ""},
  {fieldName: "factoryId", showName: "工厂"},
])

const factoryList = ref<Factory[]>([])

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

const copyData = (data: ApsProcessPath) => {
  postNoResult("/apsProcessPath/copy", {id: data.id}, "复制成功", getDataList)
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
function handleSelectionChange(val: ApsProcessPath[]) {
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
        <el-form-item label="工厂" prop="factoryId">
          <el-select v-model="queryForm.factoryId" clearable placeholder="请选择工厂" style="width: 200px">
            <el-option v-for="f in factoryList" :key="f.id" :label="f.factoryName" :value="f.id"></el-option>
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
        :document-title="documentTitle"
        :add-component="AddEditFormVue"
        :refresh-list="getDataList"
        :data-table-ref="dataTableRef"
        :multiple-selection="multipleSelection"
        ref="tableBarRef"
        :data-batch-delete-url="dataBatchDeleteUrl"
        :dialog-with="1000"
      />
      <ElTable ref="dataTableRef" :data="dataList" stripe @selection-change="handleSelectionChange">
        <ElTableColumn type="selection"/>
        <ElTableColumn v-for="h in headerList" :key="h.fieldName" :label="h.showName" :prop="h.fieldName" :min-width="h.width"/>
        <ElTableColumn fixed="right" label="操作" width="250px">
          <template #default="scope">
            <el-button
              type="warning"
              icon="edit"
              @click="editData(scope.row)"
            >
              编辑
            </el-button>
            <el-button
              type="primary"
              icon="CopyDocument"
              @click="copyData(scope.row)"
            >
              复制
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

