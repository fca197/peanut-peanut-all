<script setup lang="ts">
import {onMounted, ref} from "vue"
import AddEditFormVue from "./ApsGoodsForecastAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import {ElTable} from "element-plus"
import {downloadFilePost, HeaderInfo, postNoResult, postResultInfo} from "@@/utils/common-js.ts"
import {type ApsGoodsForecast} from "./ApsGoodsForecastType.ts"
import {UploadFilled} from "@element-plus/icons-vue"
import {getToken} from "@@/utils/cache/cookies.ts"
import {ExcelErrorMsg} from "@v/common/excel/ExcelErrorMsg.ts";
import ShowExcelErrorMsg from "@v/common/excel/ShowExcelErrorMsg.vue";

const dtoUrl = ref<string>("/apsGoodsForecast")
const documentTitle = ref<string>("预测")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsGoodsForecast>({
  goodsId: undefined,
  forecastNo: undefined,
  forecastName: undefined,
  forecastBeginDate: undefined,
  forecastEndDate: undefined,
  month: undefined,
  months: undefined,
  forecastStatus: undefined,
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
const dataList = ref<ApsGoodsForecast[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
  {fieldName: "id", showName: "序号"},
  {fieldName: "goodsId", showName: "商品"},
  {fieldName: "forecastNo", showName: "预测编码"},
  {fieldName: "forecastName", showName: "预测名称"},
  {fieldName: "forecastBeginDate", showName: "开始时间"},
  {fieldName: "forecastEndDate", showName: "结束时间"},
  {fieldName: "month", showName: ""},
  {fieldName: "months", showName: ""},
  {fieldName: "forecastStatus", showName: ""}
])

const uploadShow = ref<boolean>(false)
const uploadUrl = ref<string>("")

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
const handleSelectionChange = (val: ApsGoodsForecast[]) => {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}

const uploadShowFun = (val: ApsGoodsForecast) => {
  uploadUrl.value = `${import.meta.env.VITE_BASE_URL}/apsGoodsForecast/uploadTemplate/${val.id}`
  uploadShow.value = true
}

const excelErrorMsgList = ref<ExcelErrorMsg []>([])

const uploadShowCloseFun = (res: any) => {
  console.info("uploadShowCloseFun ", res)
  if (res.code !== 200) {
    ElMessage.error("文件上传失败，请检查文件")
    return
  }
  if (res.data.subCode === 300) {
    excelErrorMsgList.value = res.data.excelErrorMsgList;

    console.info("excelErrorMsgList ", excelErrorMsgList.value)
  }
  uploadShow.value = false
}
const downloadTemplate = (row: ApsGoodsForecast) => {
  downloadFilePost(`/apsGoodsForecast/downloadTemplate/${row.id}`, {}, "模板.xlsx")
}
const compute = (row: ApsGoodsForecast) => {
  postNoResult("/apsGoodsForecast/compute", row, "开始计算", undefined)
}
const deployData = (row: ApsGoodsForecast) => {
  postNoResult("/apsGoodsForecast/deploy", row, "发布成功", undefined)
}
const router = useRouter()

const showResultData = (row: ApsGoodsForecast) => {
  router.push(`/aps/ApsGoodsForecast/result/${row.id}`)
}
const showForecastData = (row: ApsGoodsForecast) => {
  router.push(`/aps/ApsGoodsForecast/forecast/${row.id}`)
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
        <el-form-item label="预测名称" prop="forecastName">
          <el-input v-model="queryForm.forecastName" clearable placeholder="请输入预测名称"/>
        </el-form-item>
        <el-form-item label="预测编码" prop="forecastNo">
          <el-input v-model="queryForm.forecastNo" clearable placeholder="请输入预测编码"/>
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
        <ElTableColumn v-for="h in headerList" :key="h.fieldName" :label="h.showName"
                       :prop="h.fieldName"/>
        <ElTableColumn label="状态">
          <template #default="scope">

            <span v-if="scope.row.forecastStatus === 10">
              待上传
            </span>
            <span v-if="scope.row.forecastStatus === 30">
              待计算
            </span>
            <span v-if="scope.row.forecastStatus === 50">
              计算结束
            </span>
          </template>
        </ElTableColumn>
        <ElTableColumn fixed="right" label="操作" width="150px">
          <template #default="scope">
            <el-dropdown type="primary" split-button>
              操作
              <template #dropdown>
                <el-dropdown-menu>
                  <!--            TO_UPLOAD(10, "待上传"), //-->
                  <!--            TO_COMPUTED(30, "待计算"), //-->
                  <!--            COMPUTED_RESULT(50, "计算结束"),-->
                  <!--                  <el-dropdown-item  icon="edit" @click="editData(scope.row)">-->
                  <!--                    编辑-->
                  <!--                  </el-dropdown-item>-->

                  <el-dropdown-item
                    v-if="scope.row.forecastStatus === 10 || scope.row.forecastStatus === 30"
                    icon="download"
                    @click="downloadTemplate(scope.row)">
                    下载
                  </el-dropdown-item>
                  <el-dropdown-item
                    v-if="scope.row.forecastStatus === 10 || scope.row.forecastStatus === 30"
                    icon="upload"
                    @click="uploadShowFun(scope.row)">
                    上传
                  </el-dropdown-item>
                  <el-dropdown-item v-if="scope.row.forecastStatus >= 30" icon="Grid"
                                    @click="showForecastData(scope.row)">
                    查看上传数据
                  </el-dropdown-item>
                  <el-dropdown-item v-if="scope.row.forecastStatus === 30" icon="Notification"
                                    @click="compute(scope.row)">
                    计算
                  </el-dropdown-item>
                  <el-dropdown-item v-if="scope.row.forecastStatus === 50" icon="Histogram"
                                    @click="showResultData(scope.row)">
                    查看计算结果
                  </el-dropdown-item>
                  <el-dropdown-item v-if="scope.row.forecastStatus === 50" icon="DataAnalysis"
                                    @click="deployData(scope.row)">
                    发布
                  </el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
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
    <el-dialog v-model="uploadShow" destroy-on-close>
      <el-upload
        class="upload-demo"
        drag
        :action="uploadUrl"
        multiple
        :headers="{
         'j-token': getToken()
        }"
        :on-success=uploadShowCloseFun
      >
        <el-icon class="el-icon--upload">
          <upload-filled/>
        </el-icon>
        <div class="el-upload__text">
          拖入文件 或 <em>点此上传</em>
        </div>
        <template #tip>
          <div class="el-upload__tip">
            文件格式: xlsx
          </div>
        </template>
      </el-upload>
    </el-dialog>
    <ShowExcelErrorMsg :excel-error-msg="excelErrorMsgList"/>
  </div>
</template>

<style scoped lang="scss">

</style>
