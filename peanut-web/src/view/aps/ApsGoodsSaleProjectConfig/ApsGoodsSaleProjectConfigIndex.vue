<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">

      <el-form ref="queryForm" :inline="true" label-width="100px">
        <el-form-item label="请选择商品" prop="goodsId">
          <el-select v-model="form.goodsId" style="width: 200px">
            <el-option v-for="item in goodsList" :key="item.id" :label="item.goodsName" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveConfig">
            保存配置
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="search-wrapper" shadow="never">
      <el-table id="dataTable" :key="dataTableKey" :data="apsSaleConfigList" :default-expand-all="true"
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}" row-key="id" stripe>
        <el-table-column label="组编码" prop="saleCode" width="170px">
          <template #default="scope">
            <span v-if="scope.row.isValue!== 1">{{ scope.row.saleCode }}</span>
          </template>
        </el-table-column>
        <el-table-column label="组名称" prop="saleName" width="170px">
          <template #default="scope">
            <span v-if="scope.row.isValue!== 1">{{ scope.row.saleName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="值编码" prop="saleCode" width="170px">
          <template #default="scope">
            <span v-if="scope.row.isValue === 1">{{ scope.row.saleCode }}</span>
          </template>
        </el-table-column>
        <el-table-column label="值名称" prop="saleName" width="170px">
          <template #default="scope">
            <span v-if="scope.row.isValue === 1">{{ scope.row.saleName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="工程特征组" width="170px">
          <template #default="scope">
            <span v-if="scope.row.isValue !== 1">
              <el-select v-model="scope.row.projectId" @change="(value) => selectProject(scope.row.id, value)">
                <el-option
                  v-for="item in apsProjectConfigList" :key="item.id" :label="item.saleName"
                  :value="item.id"></el-option>
              </el-select>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="工程特征值">
          <template #default="scope">
            <span v-if="scope.row.isValue === 1">
              <el-col
                :span="11" v-for="(plt, index) in goodsProjectMap[`${scope.row.parentId}-${scope.row.id}`]"
                :key="index">
                <el-input v-model="plt.saleConfigId" disabled style="display: none"/>
                <el-input v-model="plt.projectConfigId" disabled style="display: none"/>
                <el-input style="width: 200px" v-model="plt.projectConfigName" disabled/>
                <el-input-number :min="0" :step="1" :max="100" style="width: 200px" v-model="plt.quantity"/>
              </el-col>
            </span>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import {onMounted, ref, watch} from 'vue'
import type {ElForm, ElTable} from 'element-plus'
import {querySaleConfigList} from "@v/aps/ApsSaleConfig/ApsSaleConfigType.ts"
import {postNoResult, postResultInfo} from "@@/utils/common-js.ts"
import {queryGoodsList} from "@v/aps/ApsGoods/ApsGoodsType.ts"

// 定义表单引用
const queryForm = ref<InstanceType<typeof ElForm>>()
const dataTableKey = ref<number>(0)

// 定义响应式数据
const goodsList = ref<any[]>([])
const goodsSaleConfig = ref<any>({})
const goodsSaleForecastConfig = {}
const form = ref({
  goodsId: "",
  goods: {
    factoryId: undefined
  }
})
let apsSaleConfigList = []
const apsProjectConfigList = ref<any[]>([])
const goodsProjectMap = ref<Record<string, any[]>>({})


// 生命周期钩子，在组件挂载后执行
onMounted(() => {
  queryGoodsList().then((r) => {
    goodsList.value = r
    form.value.goodsId = r[0].id
  })
});
// 监听 form.goodsId 的变化
watch(() => form.value.goodsId, (val) => {
  getList()
})

// 定义方法
const getList = async () => {

  postResultInfo("/apsProjectConfig/queryPageList", {queryPage: false})
    .then(r => {
      const dataList = r.data.dataList
      apsProjectConfigList.value = r.data.dataList

      console.info("dataTableKey.value ", dataTableKey.value, dataList)
    }).then(() => {
    postResultInfo("/apsGoodsSaleItem/queryPageList", {queryPage: false, data: {goodsId: form.value.goodsId}})
      .then(rr => {
        const idSet = {}
        rr.data.dataList.forEach(t => {
          idSet[t.saleConfigId] = t.saleConfigId
        })
        console.info("idSet ", idSet)
        querySaleConfigList()
          .then(t => {
            apsSaleConfigList = t //.filter(tt => idSet[tt.id] !== undefined)
            apsSaleConfigList.forEach(t => {
              t.children = t.children.filter(tt => idSet[tt.id] !== undefined)
              t.children.forEach(t1 => {
                goodsSaleConfig.value[t1.id] = false
              })
              goodsSaleConfig.value[t.id] = false
            })
            apsSaleConfigList = apsSaleConfigList.filter(tt => tt.children.length > 0)
          }).then(() => {
          postResultInfo("/apsGoodsSaleProjectConfig/queryPageList", {
            queryPage: false,
            data: {goodsId: form.value.goodsId}
          })
            .then(res => {
              console.info("res ", res.data.dataList)
              goodsProjectMap.value = {}
              res.data.dataList.forEach(tt => {
                let key = tt.saleConfigParentId + "-" + tt.saleConfigId;
                let lt = goodsProjectMap.value[key] || []
                let parse = {
                  quantity: parseInt(tt.quantity),
                  factoryId: tt.factoryId,
                  saleConfigId: tt.saleConfigId,
                  saleConfigParentId: tt.saleConfigParentId,
                  saleConfigName: tt.saleConfigName,
                  goodsId: tt.goodsId,
                  projectConfigId: tt.projectConfigId,
                  projectConfigParentId: tt.projectConfigParentId,
                  projectConfigName: tt.projectConfigName
                };
                lt.push(parse)
                 goodsProjectMap.value[key]= lt
                for (let scc in apsSaleConfigList) {
                  if (apsSaleConfigList[scc].id === tt.saleConfigParentId) {
                    apsSaleConfigList[scc]["projectId"]= tt.projectConfigParentId
                    break;
                  }
                }
              })
              dataTableKey.value = Math.random()
              console.info("apsSaleConfigList", apsSaleConfigList)
            })
        })
      })
  })
}
const selectProject = (saleId: string, projectId: string) => {
  const lt = apsProjectConfigList.value.filter(t => t.id === projectId)[0].children || []
  if (lt.length === 0) {
    alert('请先添加工程特征值')
    return
  }
  const saleConfigList = apsSaleConfigList.filter(t => t.id === saleId)[0].children || []

  saleConfigList.forEach(sc => {
    const ltt: any[] = []
    lt.forEach(t => {
      const parse = {
        quantity: 0,
        factoryId: form.value.goods.factoryId,
        saleConfigId: sc.id,
        saleConfigParentId: sc.parentId,
        saleConfigName: sc.saleName,
        goodsId: form.value.goodsId,
        projectConfigId: t.id,
        projectConfigParentId: t.parentId,
        projectConfigName: t.saleName
      }
      ltt.push(parse)
    })
    goodsProjectMap.value[saleId + '-' + sc.id] = ltt
  })
  console.log("goodsSaleConfig", saleId, projectId, goodsProjectMap)
  dataTableKey.value = Math.random()
}

const saveConfig = () => {
  const f: any[] = []
  for (let key in goodsProjectMap.value) {
    const l = goodsProjectMap.value[key]
    l.forEach(t => {
      f.push(t)
    })
  }
  if (f.length === 0) {
    ElMessage.error("请先选择工程特征值")
    return
  }
  postNoResult("/apsGoodsSaleProjectConfig/insertBatch", f,"保存成功", getList)
}
</script>

<style lang="scss" scoped>

</style>
