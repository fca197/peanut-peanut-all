<script setup lang="ts">
import {onMounted, ref} from "vue"
import AddEditFormVue from "./StoreBusinessDistrictLevelAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import {ElTable} from "element-plus";
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {type StoreBusinessDistrictLevel} from "./StoreBusinessDistrictLevelType.ts"

const dtoUrl = ref<string>("/storeBusinessDistrictLevel")
const documentTitle = ref<string>("商圈级别")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)
const loadEntity = ref<boolean>(true)
// 查询表格
const queryForm = ref<StoreBusinessDistrictLevel>({
  businessDistrictLevelName: undefined,
  businessDistrictLevelDesc: undefined,
  businessDistrictLevelColor: undefined,
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
const dataList = ref<StoreBusinessDistrictLevel[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
  {fieldName: "id", showName: "序号"},
  {fieldName: "businessDistrictLevelName", showName: "商圈名称"},
  {fieldName: "businessDistrictLevelDesc", showName: "商圈描述"},
  {fieldName: "businessDistrictLevelColor", showName: "商圈颜色"},
  {fieldName: "updateTime", showName: "修改时间"},
  {fieldName: "updateUserName", showName: "修改人"},
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
    // headerList.value = t.data.headerList
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
const handleSelectionChange = (val: StoreBusinessDistrictLevel[]) => {
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
        <el-form-item label="商圈名称" prop="businessDistrictLevelName">
          <el-input
              v-model="queryForm.businessDistrictLevelName"
              clearable
              placeholder="请输入商圈名称"
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
      <ElTable v-loading="loadEntity" ref="dataTableRef" :data="dataList" stripe @selection-change="handleSelectionChange">
        <ElTableColumn type="selection"/>
        <!--        <ElTableColumn v-for="h in headerList" :key="h.fieldName" :label="h.showName"-->
        <!--            :prop="h.fieldName" :min-width="h.width"-->
        <!--        />-->
        <ElTableColumn label="序号" prop="id"/>
        <ElTableColumn label="名称" prop="businessDistrictLevelName"/>
        <ElTableColumn label="颜色" prop="businessDistrictLevelColor" >
          <template #default="scope">
            <div :style="'width: 23px; height:23px; background-color: '+scope.row.businessDistrictLevelColor"></div>
          </template>
        </ElTableColumn>
        <ElTableColumn label="描述" prop="businessDistrictLevelDesc"
                       show-overflow-tooltip/>
        <ElTableColumn label="修改时间" prop="updateTime"/>
        <ElTableColumn label="修改人" prop="updateUserName"/>
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

