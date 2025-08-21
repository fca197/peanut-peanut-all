<script setup lang="ts">
import {onMounted, ref} from "vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import {ElTable} from 'element-plus'
import {postNoResult, postResultInfoList} from "@@/utils/common-js.ts"
import {type ApsGoodsSaleItem} from "./ApsGoodsSaleItemType.ts"
import {ApsGoods, queryGoodsList} from "@v/aps/ApsGoods/ApsGoodsType.ts"
import {querySaleConfigList} from "@v/aps/ApsSaleConfig/ApsSaleConfigType.ts";

const dtoUrl = ref<string>("/apsGoodsSaleItem")
const documentTitle = ref<string>("商品销售配置")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsGoodsSaleItem>({
  goodsId: undefined,
  saleGoodsId: undefined,
  saleConfigId: undefined,
  useForecast: undefined,
  supplierStatus: undefined,
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
const dataList = ref<ApsGoodsSaleItem[]>([])

const goodsSaleForecastConfig = {}
const goodsList = ref<ApsGoods[]>([])
const goodsSaleConfig = {}
const dataTableKey = ref<any>("333")

// 获取表格内数据
function getDataList() {
  const req = {
    data: queryForm.value,
    queryPage: false
  }
  console.info("getDataList {}", req)
  postResultInfoList(`${dtoUrl.value}/queryPageList`, req)
    .then((t) => {
      for (let n in goodsSaleConfig) {
        goodsSaleConfig[n] = false
      }
      for (let n in goodsSaleForecastConfig) {
        goodsSaleForecastConfig[n] = false
      }
      t.forEach(g => {
        goodsSaleConfig["" + g.saleConfigId] = true
        goodsSaleForecastConfig ["" + g.saleConfigId] = g.useForecast === 1
      })
      console.log("goodsSaleConfig goodsSaleForecastConfig", goodsSaleConfig, goodsSaleForecastConfig)
      dataTableKey.value ="00." + Math.random()
    })
}

// table点击事件
function editData(data: any) {
  // console.info("data ", data)
  tableBarRef.value?.showEditDialog(data.id)
}


// 表格选中事件
function handleSelectionChange(val: ApsGoodsSaleItem[]) {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}

watch(() => queryForm.value.goodsId, (n) => {
  getDataList()
})

function changeSaleSelect(id: string, val: string) {
  postNoResult("/apsGoodsSaleItem/updateSaleConfig",
    {goodsId: queryForm.value.goodsId, saleConfigId: id, isAdd: val},
    "修改成功", () => {
      getDataList()
    })
}

function changeForecastSelect(id: string, val: string) {
  if (val === true && goodsSaleConfig[id] === false) {
    ElMessage.error("请先选择销售配置")
    return
  }
  postNoResult("/apsGoodsSaleItem/updateForecast",
    {goodsId: queryForm.value.goodsId, saleConfigId: id, useForecast: val ? 1 : 0},
    "修改成功",
    () => {
      getDataList()
    }
  )
}

// 页面加载事件
onMounted(() => {
  //
  querySaleConfigList().then((r) => {
    if (r.children) {
      r.children.forEach((rr) => {
        goodsSaleConfig[rr.id] = false
      })
    }
    goodsSaleConfig[r.id] = false
    dataList.value = r
  }).then(() => {
    queryGoodsList().then((r) => {
      goodsList.value = r
      queryForm.value.goodsId = r[0].id
    })
  })
})
</script>

<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-form v-model="queryForm" inline>
        <el-form-item label="商品" prop="goodsId">
          <el-select v-model="queryForm.goodsId" style="width: 200px">
            <el-option v-for="g in goodsList" :key="g.id" :value="g.id" :label="g.goodsName"/>
          </el-select>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card shadow="never">
      <el-table :key="dataTableKey" :data="dataList" :default-expand-all="true"
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}" row-key="id" stripe>
        <!--      <el-table-column label="全选" type="selection" prop="id"/>-->
        <el-table-column label="组编码" prop="saleCode">
          <template #default="scope">
            <span v-if="scope.row.isValue !== 1">{{ scope.row.saleCode }}</span>
          </template>
        </el-table-column>
        <el-table-column label="值编码" prop="saleCode">
          <template #default="scope">
            <span v-if="scope.row.isValue === 1">{{ scope.row.saleCode }}</span>
          </template>
        </el-table-column>
        <el-table-column label="组名称" prop="saleName">
          <template #default="scope">
            <span v-if="scope.row.isValue !== 1">{{ scope.row.saleName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="值名称" prop="saleName">
          <template #default="scope">
            <span v-if="scope.row.isValue === 1">{{ scope.row.saleName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="组/值">
          <template #default="scope">
            <span v-if="scope.row.isValue === 1">值</span>
            <span v-else>组</span>
          </template>
        </el-table-column>
        <el-table-column label="本商品配置">
          <template #default="scope">
          <span v-if="scope.row.isValue === 1">
            <el-checkbox
              v-model="goodsSaleConfig[scope.row.id]"
              @change="value=>{changeSaleSelect(scope.row.id,value)}"></el-checkbox>
            <span style="margin: 0 10px"></span>    <span v-if="goodsSaleConfig[scope.row.id]">是</span><span
            v-else>否</span>
          </span>
          </template>
        </el-table-column>
        <el-table-column label="参与预测">
          <template #default="scope">
          <span v-if="scope.row.isValue === 1">
           <el-checkbox
             v-model="goodsSaleForecastConfig[scope.row.id]" :value="scope.row.id"
             @change="value=>{changeForecastSelect(scope.row.id,value)}"></el-checkbox>
           <span style="margin: 0 10px"></span>    <span v-if="goodsSaleForecastConfig[scope.row.id]">是</span><span
            v-else>否</span>
          </span>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<style scoped lang="scss">

</style>

