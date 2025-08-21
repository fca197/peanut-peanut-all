<script setup lang="ts">
import {onMounted, ref} from "vue"
import AddEditFormVue from "./ApsMachineWorkstationAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import {ElTable} from "element-plus";
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {type ApsMachineWorkstation} from "./ApsMachineWorkstationType.ts"
import {Factory, queryFactoryList} from "@v/base/Factory/FactoryType.ts";

const dtoUrl = ref<string>("/apsMachineWorkstation")
const documentTitle = ref<string>("工作站")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsMachineWorkstation>({
  machineWorkstationNo: undefined,
  machineWorkstationName: undefined,
  // minPower: undefined,
  maxPower: undefined,
  factoryId: undefined,
  sortIndex: undefined,
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
const dataList = ref<ApsMachineWorkstation[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
  {fieldName: "id", showName: "序号"},
  {fieldName: "machineWorkstationNo", showName: "工作站编号"},
  {fieldName: "machineWorkstationName", showName: "工作站名称"},
  // {fieldName: "minPower", showName: "最小功率"},
  {fieldName: "maxPower", showName: "最大功率"},
  {fieldName: "factoryId", showName: "工厂ID"},
  {fieldName: "sortIndex", showName: "排序索引"},
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
const handleSelectionChange = (val: ApsMachineWorkstation[]) => {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}

const factoryList = ref<Factory[]>([])
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
        <el-form-item label="工作站名称" prop="machineWorkstationName">
          <el-input
            v-model="queryForm.machineWorkstationName" clearable
            placeholder="请输入工作站名称"/>
        </el-form-item>
        <el-form-item label="工作站编号" prop="machineWorkstationNo">
          <el-input
            v-model="queryForm.machineWorkstationNo" clearable
            placeholder="请输入工作站编号"/>
        </el-form-item>
        <el-form-item label="工厂" prop="factoryId">
          <el-select
            v-model="queryForm.factoryId" clearable placeholder="请选择工厂"
            style="width: 200px">
            <el-option
              v-for="f in factoryList" :value="f.id" :label="f.factoryName" :key="f.id"
            />
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
        <ElTableColumn
          v-for="h in headerList" :key="h.fieldName" :label="h.showName"
          :prop="h.fieldName" :min-width="h.width"
        />
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

