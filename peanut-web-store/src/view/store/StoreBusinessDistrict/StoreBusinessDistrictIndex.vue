<script setup lang="ts">
import {onMounted, ref} from "vue"
import AddEditFormVue from "./StoreBusinessDistrictAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import {ElTable} from "element-plus";
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {type StoreBusinessDistrict} from "./StoreBusinessDistrictType.ts"
import {queryBrandList, TBrand} from "@v/base/TBrand/TBrandType.ts";

const dtoUrl = ref<string>("/storeBusinessDistrict")
const documentTitle = ref<string>("商圈")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)
const loadEntity = ref<boolean>(true)
// 查询表格
const queryForm = ref<StoreBusinessDistrict>({
  brandId: undefined,
  businessDistrictCode: undefined,
  businessDistrictName: undefined,
  businessDistrictDesc: undefined,
  businessDistrictAddress: undefined,
  countryCode: undefined,
  provinceCode: undefined,
  cityCode: undefined,
  areaCode: undefined,
  countryName: undefined,
  provinceName: undefined,
  cityName: undefined,
  areaName: undefined,
  businessDistrictRadius: undefined,
  businessDistrictLevelId: undefined,
  businessDistrictTypeId: undefined,
  centerLat: undefined,
  centerLng: undefined,
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
const dataList = ref<StoreBusinessDistrict[]>([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
  {fieldName: "id", showName: "序号"},
  {fieldName: "brandId", showName: "品牌"},
  {fieldName: "businessDistrictCode", showName: "编码"},
  {fieldName: "businessDistrictName", showName: "名称"},
  {fieldName: "businessDistrictDesc", showName: "描述"},
  {fieldName: "businessDistrictAddress", showName: "地址"},
  {fieldName: "countryCode", showName: "国家编码"},
  {fieldName: "provinceCode", showName: "城市编码"},
  {fieldName: "cityCode", showName: "城市编码"},
  {fieldName: "areaCode", showName: "城市编码"},
  {fieldName: "countryName", showName: "国家编码"},
  {fieldName: "provinceName", showName: "城市编码"},
  {fieldName: "cityName", showName: "城市编码"},
  {fieldName: "areaName", showName: "城市编码"},
  {fieldName: "businessDistrictRadius", showName: "半径"},
  {fieldName: "businessDistrictLevelId", showName: "商圈级别ID"},
  {fieldName: "businessDistrictTypeId", showName: "商圈类别ID"},
  {fieldName: "centerLat", showName: "纬度"},
  {fieldName: "centerLng", showName: "经度"},
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
const handleSelectionChange = (val: StoreBusinessDistrict[]) => {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}
const brandList = ref<TBrand []>([])
// 页面加载事件
onMounted(() => {
  getDataList()
  queryBrandList().then(res => {
    brandList.value = res
  });
})

</script>

<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-form v-model="queryForm" inline>
        <el-form-item label="品牌" prop="brandId">
          <el-input
              v-model="queryForm.brandId"
              clearable
              placeholder="请输入品牌"
          />
        </el-form-item>
        <el-form-item label="编码" prop="businessDistrictCode">
          <el-input
              v-model="queryForm.businessDistrictCode"
              clearable
              placeholder="请输入编码"
          />
        </el-form-item>
        <el-form-item label="名称" prop="businessDistrictName">
          <el-input
              v-model="queryForm.businessDistrictName"
              clearable
              placeholder="请输入名称"
          />
        </el-form-item>
        <el-form-item label="国家编码" prop="countryCode">
          <el-input
              v-model="queryForm.countryCode"
              clearable
              placeholder="请输入国家编码"
          />
        </el-form-item>
        <el-form-item label="城市编码" prop="provinceCode">
          <el-input
              v-model="queryForm.provinceCode"
              clearable
              placeholder="请输入城市编码"
          />
        </el-form-item>
        <el-form-item label="城市编码" prop="cityCode">
          <el-input
              v-model="queryForm.cityCode"
              clearable
              placeholder="请输入城市编码"
          />
        </el-form-item>
        <el-form-item label="城市编码" prop="areaCode">
          <el-input
              v-model="queryForm.areaCode"
              clearable
              placeholder="请输入城市编码"
          />
        </el-form-item>
        <el-form-item label="半径" prop="businessDistrictRadius">
          <el-input
              v-model="queryForm.businessDistrictRadius"
              clearable
              placeholder="请输入半径"
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
          :dialog-with="1000"
      />
      <ElTable v-loading="loadEntity" ref="dataTableRef" :data="dataList" stripe @selection-change="handleSelectionChange">
        <ElTableColumn type="selection"/>
        <ElTableColumn label="品牌" props="brandName"/>
        <ElTableColumn label="编码" props="businessDistrictCode"/>
        <ElTableColumn label="名称" props="businessDistrictName"/>
        <ElTableColumn label="描述" props="businessDistrictDesc"/>
        <ElTableColumn label="地址" props="businessDistrictAddress"/>
        <ElTableColumn label="国家编码" props="countryCode"/>
        <ElTableColumn label="城市编码" props="provinceCode"/>
        <ElTableColumn label="城市编码" props="cityCode"/>
        <ElTableColumn label="城市编码" props="areaCode"/>
        <ElTableColumn label="国家编码" props="countryName"/>
        <ElTableColumn label="城市编码" props="provinceName"/>
        <ElTableColumn label="城市编码" props="cityName"/>
        <ElTableColumn label="城市编码" props="areaName"/>
        <ElTableColumn label="半径" props="businessDistrictRadius"/>
        <ElTableColumn label="商圈级别ID" props="businessDistrictLevelId"/>
        <ElTableColumn label="商圈类别ID" props="businessDistrictTypeId"/>
        <ElTableColumn label="纬度" props="centerLat"/>
        <ElTableColumn label="经度" props="centerLng"/>

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

