<script setup lang="ts">
import {ref, onMounted} from "vue"
import AddEditFormVue from "./ApsMachineWorkstationItemAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import {ElTable} from "element-plus";
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {type ApsMachineWorkstationItem} from "./ApsMachineWorkstationItemType.ts"

const dtoUrl = ref<string>("/apsMachineWorkstationItem")
const documentTitle = ref<string>("aps 生产机器 工作站机器配置")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsMachineWorkstationItem>({
  machineWorkstationId: undefined,
  machineId: undefined,
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
const dataList = ref<ApsMachineWorkstationItem[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
  {fieldName: "id", showName: "序号"},
  {fieldName: "machineWorkstationId", showName: "工作站id"},
  {fieldName: "machineId", showName: "机器ID"},
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
const handleSelectionChange = (val: ApsMachineWorkstationItem[]) => {
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
        <el-form-item label="工作站id" prop="machineWorkstationId">
          <el-input v-model="queryForm.machineWorkstationId" clearable
                    placeholder="请输入工作站id"/>
        </el-form-item>
        <el-form-item label="机器ID" prop="machineId">
          <el-input v-model="queryForm.machineId" clearable placeholder="请输入机器ID"/>
        </el-form-item>

        <el-form-item label="最大功率" prop="maxPower">
          <el-input v-model="queryForm.maxPower" clearable placeholder="请输入最大功率"/>
        </el-form-item>
        <el-form-item label="工厂ID" prop="factoryId">
          <el-input v-model="queryForm.factoryId" clearable placeholder="请输入工厂ID"/>
        </el-form-item>
        <el-form-item label="排序索引" prop="sortIndex">
          <el-input v-model="queryForm.sortIndex" clearable placeholder="请输入排序索引"/>
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

