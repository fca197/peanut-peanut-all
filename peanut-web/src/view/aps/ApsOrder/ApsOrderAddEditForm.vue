<template>
  <el-form ref="form" :model="addForm" label-width="100px" :rules="checkRules">
    <el-tabs tab-position="left" style="height: 650px;" type="border-card">
      <el-tab-pane label="基本信息">
        <el-form-item label="订单编号" prop="orderNo">
          <el-input disabled v-model="addForm.orderNo" placeholder="请输入订单编号"/>
        </el-form-item>
        <el-form-item label="订单金额" prop="orderAmount">
          <el-input v-model="addForm.orderTotalPrice" placeholder="请输入订单金额"/>
        </el-form-item>
        <el-form-item label="定金金额" prop="orderAmount">
          <el-input v-model="addForm.reserveAmount" placeholder="请输入定金金额"/>
        </el-form-item>
        <el-form-item label="定金支付时间">
          <el-date-picker v-model="addForm.reserveDatetime" type="datetime" placeholder="选择日期时间"
                          align="right" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="尾款金额" prop="finishPayedAmount">
          <el-input v-model="addForm.finishPayedAmount" placeholder="请输入尾款金额"/>
        </el-form-item>
        <el-form-item label="尾款支付时间" prop="finishPayedDatetime">
          <el-date-picker v-model="addForm.finishPayedDatetime" type="datetime" placeholder="选择日期时间"
                          align="right" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="制造完成时间" prop="makeFinishDate">
          <el-date-picker v-model="addForm.makeFinishDate" type="date" placeholder="选择日期时间"
                          align="right" value-format="YYYY-MM-DD" style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="交付时间" prop="deliveryDate">
          <el-date-picker v-model="addForm.deliveryDate" type="date" placeholder="选择日期时间"
                          align="right" value-format="YYYY-MM-DD" style="width: 100%"
          />
        </el-form-item>


        <el-form-item label="订单备注" prop="orderRemark">
          <el-input v-model="addForm.orderRemark" placeholder="请输入订单备注"/>
        </el-form-item>
      </el-tab-pane>
      <el-tab-pane label="用户管理" style="overflow-y: scroll">
        <el-form-item label="客户名称" prop="userName">
          <el-input v-model="addForm.orderUser.userName" placeholder="请输入客户名称"/>
        </el-form-item>
        <el-form-item label="客户电话" prop="userPhone">
          <el-input v-model="addForm.orderUser.userPhone" placeholder="请输入客户电话"/>
        </el-form-item>
        <el-form-item label="客户性别" prop="userMobile">
          <el-radio-group v-model="addForm.orderUser.userSex">
            <el-radio label="男" value="1"/>
            <el-radio label="女" value="0"/>
          </el-radio-group>
        </el-form-item>
        <!--        <el-form-item label="国家" prop="userEmail">-->
        <!--          <el-select v-model="addForm.orderUser.countryCode" placeholder="请选择省份">-->
        <!--            <el-option v-for="item in countryCodeList" :key="item.code" :label="item.name" :value="item.code"/>-->
        <!--          </el-select>-->
        <!--        </el-form-item>-->
        <el-form-item label="省份" prop="provinceCode">
          <el-select v-model="addForm.orderUser.provinceCode" placeholder="请选择省份">
            <el-option v-for="item in provinceCodeList" :key="item.code" :label="item.name" :value="item.code"/>
          </el-select>
        </el-form-item>
        <el-form-item label="城市" prop="cityCode">
          <el-select v-model="addForm.orderUser.cityCode" placeholder="请选择城市">
            <el-option v-for="item in cityCodeList" :key="item.code" :label="item.name" :value="item.code"/>
          </el-select>
        </el-form-item>
        <el-form-item label="区县" prop="areaCode">
          <el-select v-model="addForm.orderUser.areaCode" placeholder="请选择区县">
            <el-option v-for="item in areaCodeList" :key="item.code" :label="item.name" :value="item.code"/>
          </el-select>
        </el-form-item>
        <el-form-item label="客户地址" prop="userAddress">
          <el-input v-model="addForm.orderUser.userAddress" placeholder="请输入客户地址"/>
        </el-form-item>
        <el-form-item label="备注" prop="userAddress">
          <el-input v-model="addForm.orderUser.userRemark" placeholder="请输入备注"/>
        </el-form-item>
      </el-tab-pane>
      <el-tab-pane label="商品管理">
        <el-form-item label="工厂" prop="factoryId">
          <el-select v-model="addForm.factoryId" style="width: 100%">
            <el-option v-for="f in factoryList" :value="f.id" :key="f.id" :label="f.factoryName"/>
          </el-select>
        </el-form-item>
        <el-form-item label="商品">
          <el-select v-model="addForm.goodsList[0].goodsId" placeholder="请选择商品" style="width: 100%">
            <el-option v-for="item in goodsList.filter(t=>t.factoryId === addForm.factoryId)" :key="item.id"
                       :label="item.goodsName" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="数量">
          <el-input disabled v-model="addForm.goodsList[0].goodsNum" placeholder="1"/>
        </el-form-item>
      </el-tab-pane>
      <el-tab-pane label="销售配置" :key="loadGoodsSaleKey">
        <el-form-item v-for="(sa, index) in goodsSaleList" :label="sa.saleName+'/'+sa.saleCode">
          <el-radio-group v-model="goodsSaleConfigMap[sa.id]" :key="index">
            <el-radio :value="st.id" v-for="st in sa.children">
              {{ st.saleName }}/{{ st.saleCode }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
      </el-tab-pane>
      <!--      <el-tab-pane label="工程配置">-->
      <!--        工程配置-->
      <!--      </el-tab-pane>-->
      <el-tab-pane label="零件">
        <el-form-item label="零件">
          <el-table :data="goodsBomList">
            <el-table-column prop="bomName" label="名称"/>
            <el-table-column prop="bomCode" label="编号"/>
            <el-table-column prop="bomCostPriceUnit" label="单价规格"/>
            <el-table-column prop="bomCostPrice" label="单价"/>
            <el-table-column label="关注">
              <template #default="scope">
                {{ scope.row.isFollow === true ? "是" : "否" }}
              </template>
            </el-table-column>
            <el-table-column label="数量">
              <template #default="scope">
                <el-input v-model="addForm.goodsBom[scope.row.id]" placeholder="数量"></el-input>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
      </el-tab-pane>
    </el-tabs>
  </el-form>
  <el-row class="addFormBtnRow">
    <el-button @click="cancelForm" type="info" icon="close">
      取消
    </el-button>
    <el-button @click="saveForm" type="primary" icon="check">
      确定
    </el-button>
  </el-row>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue"
import { type ApsOrder } from "./ApsOrderType.ts"
import { postNoResult, postResultInfoList } from "@/common/utils/common-js.ts"
import { type FormInstance, FormRules } from "element-plus"
import { DistrictCode, queryDistrictByParentCode } from "@v/base/DistrictCode/DistrictCodeType.ts";
import { ApsGoods, queryGoodsList } from "@v/aps/ApsGoods/ApsGoodsType.ts";
import { ApsGoodsBom } from "@v/aps/ApsGoodsBom/ApsGoodsBomType.ts";
import { Factory, queryFactoryList } from "@v/base/Factory/FactoryType.ts";
import { ApsSaleConfig, querySaleConfigList } from "@v/aps/ApsSaleConfig/ApsSaleConfigType.ts";

const props = defineProps({
  saveFun: {
    type: Function
  },
  editId: {
    type: String,
    required: false
  }
})

// 对象URL
const dtoUrl = ref<string>("/apsOrder")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({})

// const countryCodeList = ref<DistrictCode []>([])
const provinceCodeList = ref<DistrictCode []>([])
const cityCodeList = ref<DistrictCode []>([])
const areaCodeList = ref<DistrictCode []>([])
const goodsList = ref<ApsGoods []>([])
const goodsBomList = ref<ApsGoodsBom []>([])
const factoryList = ref<Factory[]>([])
const loadGoodsSaleKey = ref<string>("")
const goodsSaleList = ref<ApsSaleConfig []>([])
const goodsSaleConfigMap = ref({})
// 页面加载事件
onMounted(() => {
  // loadById()
  queryDistrictByParentCode("0").then((r) => {
    provinceCodeList.value = r
  })
  queryFactoryList().then(r => factoryList.value = r)

  querySaleConfigList().then(r => {
    goodsSaleList.value = r
  })
  queryGoodsList().then(r => goodsList.value = r)
})

// 添加对象
const addForm = ref<ApsOrder>({
  orderNo: "",
  orderRemark: "",
  orderStatus: "",
  orderTotalPrice: "",
  goodsId: "",
  reserveAmount: "",
  reserveDatetime: "",
  finishPayedAmount: "",
  finishPayedDatetime: "",
  makeFinishDate: "",
  actMakeFinishDate: "",
  deliveryDate: "",
  factoryId: "",
  urgencyLevel: 0,
  schedulingDate: "",
  orderNoParent: "",
  id: "",
  orderUser: {
    userSex: "1"
  },
  goodsList: [ {} ],
  goodsBom: []
})

// 保存
function saveForm() {


  var goodsId = addForm.value.goodsList[0].goodsId
  let apsOrderSaleConfigList = []
  for (let gs in goodsSaleConfigMap.value) {
    apsOrderSaleConfigList.push({
      goodsId: goodsId,
      configId: goodsSaleConfigMap.value[gs]
    })
  }
  let apsGoodsBomList = []

  for (let k in addForm.value.goodsBom) {
    let v = addForm.value.goodsBom[k]
    apsGoodsBomList.push({ goodsId: goodsId, bomCount: v, goodsBomId: k })
  }
  addForm.value.apsGoodsBomList = apsGoodsBomList
  addForm.value.apsOrderSaleConfigList = apsOrderSaleConfigList

  console.info("addForm ", addForm)
  postNoResult(`${dtoUrl.value}/insert`, addForm.value, "保存成功", saveFormAfter)
}

// 保存成功后，方法， 目前关闭弹窗
function saveFormAfter() {
  cancelForm()
}

// 取消方法
function cancelForm() {
  if(props.saveFun) {
    props.saveFun()
  }
}

watch(() => addForm.value.goodsList[0].goodsId, (value) => {
  console.info("selectGoods ", value)
  const data = { data: { goodsId: value }, queryPage: false }
  postResultInfoList("/apsGoodsBom/queryPageList", data).then(t => {
    goodsBomList.value = t.sort((a, b) => a.isFollow === true ? - 1 : 1)
  })
  postResultInfoList("/apsGoodsSaleItem/queryPageList", data).then(r => {
    let saleConfigArr = r.map(t => t.saleConfigId);
    goodsSaleList.value.forEach(t => {
      t.children = t.children.filter(tt => saleConfigArr.includes(tt.id))
    })
    loadGoodsSaleKey.value = Math.random() + ""
  })
})

watch(() => addForm.value.orderUser.provinceCode, (n) => {
  console.info("addForm.value.orderUser.provinceCode", n)
  addForm.value.orderUser.cityCode = undefined
  addForm.value.orderUser.areaCode = undefined
  queryDistrictByParentCode(n).then(r => {
    cityCodeList.value = r
  })
})
watch(() => addForm.value.orderUser.cityCode, (n) => {
  console.info("addForm.value.orderUser.cityCode", n)
  addForm.value.orderUser.areaCode = undefined
  queryDistrictByParentCode(n).then(r => {
    areaCodeList.value = r
  })
})
watch(() => addForm.value.factoryId, (n) => {
  console.info("addForm.value.factoryId", n)
  let ll = goodsList.value.filter(t => t.factoryId === n);
  if(ll.length > 0) {
    addForm.value.goodsList[0].goodsId = ll[0].id
  } else {
    addForm.value.goodsList[0].goodsId = undefined

  }
})
</script>

<style scoped lang="scss">

</style>

