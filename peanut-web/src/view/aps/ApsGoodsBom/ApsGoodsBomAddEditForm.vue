<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsGoodsBom, checkBomUseExpressionFun, isFollowList} from "./ApsGoodsBomType.ts"
import {getById, postNoResult} from "@/common/utils/common-js.ts"
import {type FormInstance, FormRules} from "element-plus"
import {Factory, queryFactoryList} from "@v/base/Factory/FactoryType.ts";
import {ApsGoods, queryGoodsList} from "@v/aps/ApsGoods/ApsGoodsType.ts";
import {ApsBom, queryApsBomList} from "@v/aps/ApsBom/ApsBomType.ts";
import {queryStationList} from "@v/aps/ApsWorkshopStation/ApsWorkshopStationType.ts";

const props = defineProps({
  saveFun: {
    type: Function
  },
  editId: {
    type: String,
    required: false
  }
})
const factoryList = ref<Factory[]>([])
const goodsList = ref<ApsGoods[]>([])
const workStationList = ref<any[]>([])
const selectBomList = ref<ApsBom[]>([])
const bomUseExpressionRef = ref<any>({})
// 对象URL
const dtoUrl = ref<string>("/apsGoodsBom")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 商品ID
  goodsId: [
    {required: true, message: "请输入商品ID", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 零件组ID
  groupId: [
    {required: true, message: "请输入零件组ID", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 商品ID
  bomId: [
    {required: true, message: "请输入商品ID", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 单位
  bomUnit: [
    {required: true, message: "请输入单位", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 成本价
  bomCostPrice: [
    {required: true, message: "请输入成本价", trigger: "blur"}
  ],
  // 用量
  bomUsage: [
    {required: true, message: "请输入用量", trigger: "blur"},
    {min: 1, max: 20, message: "长度在 1 到 20 个字符", trigger: "blur"}
  ],
  // 使用工位
  bomUseWorkStation: [
    {required: true, message: "请输入使用工位", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 使用表达式
  bomUseExpression: [
    {required: true, validator: checkBomUseExpression, trigger: "blur"}
  ],
  // 库存
  bomInventory: [
    {required: true, message: "请输入库存", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 工厂ID
  factoryId: [
    {required: true, message: "请输入工厂", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ]
})


// 页面加载事件
onMounted(() => {
  loadById()
  queryFactoryList().then((r) => {
    factoryList.value = r;
  })
  queryGoodsList().then((r) => {
    goodsList.value = r
  })

  queryStationList().then(r => workStationList.value = r)


})
// 添加对象
const addForm = ref<ApsGoodsBom>({
  goodsId: "",
  groupId: "",
  bomId: "",
  bomCode: "",
  bomName: "",
  bomUsage: "",
  bomUnit: "",
  bomCostPrice: "",
  bomCostPriceUnit: "",
  bomUseWorkStation: "",
  bomUseExpression: "",
  bomInventory: "",
  isFollow: false,
  factoryId: "",
  id: ""
})

function loadById() {
  if (!props.editId) {
    return
  }
  console.info("props.editId ", props.editId)
  getById(`${dtoUrl.value}/queryByIdList`, props.editId).then((t) => {
    addForm.value = t
    console.info(" addForm.value ", addForm.value)
  }).then(() => {
    selectBomListFun(null, addForm.value.bomId)
  })
}

// 保存
function saveForm() {
  console.info("addForm ", addForm)
  addFormRef.value?.validate((valid) => {
    console.info("valid ", valid)
    if (valid) {
      // 存在ID ，调用更新
      if (props.editId) {
        postNoResult(`${dtoUrl.value}/updateById`, addForm.value, "修改成功", saveFormAfter)
      } else {
        // 调用保存
        postNoResult(`${dtoUrl.value}/insert`, addForm.value, "保存成功", saveFormAfter)
      }
    } else {
      ElMessage.error("表单校验失败，请检查必填项")
    }
  })
}

// 保存成功后，方法， 目前关闭弹窗
function saveFormAfter() {
  cancelForm()
}

// 取消方法
function cancelForm() {
  if (props.saveFun) {
    props.saveFun()
  }
}

function selectBomListFun(v: string, id) {
  queryApsBomList(1, {
    bomName: v,
    id
  }).then(r => {
    selectBomList.value = r
    bomChange()
  })
}

function checkBomUseExpression(rule: any, value: string, callback: any) {
  // value = value + ""
  console.info("value  ", value)
  if (value === undefined || value.trim().length === 0) {
    callback(new Error("表达式不能为空"))
    return
  }
  checkBomUseExpressionFun(addForm.value.bomUseExpression).then(r => {
    if (!r) {
      callback(new Error("表达式错误"))
    }
  })
}

function bomChange() {
  selectBomList.value.filter(t => t.id === addForm.value.bomId).forEach(t => {
    addForm.value.bomName = t.bomName
    addForm.value.bomCode = t.bomCode
    addForm.value.bomUnit = t.bomUnit
    addForm.value.bomCostPriceUnit = t.bomCostPriceUnit
    addForm.value.bomCostPrice = t.bomCostPrice
  })
}
</script>

<template>
  <el-form label-width="100px" :model="addForm" ref="addFormRef" :rules="checkRules">

    <el-form-item label="工厂" prop="factoryId">
      <el-select v-model="addForm.factoryId" placeholder="请选择工厂" clearable>
        <el-option v-for="item in factoryList" :key="item.id" :label="item.factoryName"
                   :value="item.id"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="商品" prop="goodsId">
      <el-select v-model="addForm.goodsId" placeholder="请选择零件" clearable>
        <el-option v-for="item in goodsList.filter(t => t.factoryId === addForm.factoryId)"
                   :key="item.id"
                   :label="item.goodsName" :value="item.id"/>
      </el-select>
    </el-form-item>
    <el-form-item label="工位" prop="bomUseWorkStation">
      <el-select v-model="addForm.bomUseWorkStation" placeholder="请选择工位" clearable>
        <el-option v-for="item in workStationList" :key="item.id" :label="item.stationName"
                   :value="item.id"/>
      </el-select>
    </el-form-item>
    <el-form-item label="零件" prop="bomId">
      <el-select v-model="addForm.bomId" placeholder="请选择零件" filterable remote
                 :remote-method="selectBomListFun"
                 @change="bomChange">
        <el-option v-for="item in selectBomList" :key="item.id" :label="item.bomName"
                   :value="item.id"/>
      </el-select>
    </el-form-item>
    <el-form-item label="零件编码" prop="bomCode">
      <el-input v-model="addForm.bomCode" placeholder="请输入零件编码" readonly disabled/>
    </el-form-item>
    <el-form-item label="零件名称" prop="bomName">
      <el-input v-model="addForm.bomName" placeholder="请输入零件名称" readonly disabled/>
    </el-form-item>
    <el-form-item label="成本单价" prop="bomCostPrice">
      <el-input v-model="addForm.bomCostPrice" placeholder="请输入成本单价" readonly disabled/>
    </el-form-item>
    <el-form-item label="用量规格" prop="bomCostPriceUnit">
      <el-input v-model="addForm.bomCostPriceUnit" placeholder="请输入单价规格" readonly disabled/>
    </el-form-item>
    <el-form-item label="零件用量" prop="bomUsage">
      <el-input v-model="addForm.bomUsage" placeholder="请输入用量"/>
    </el-form-item>
    <el-form-item label="用量规格" prop="bomUnit">
      <el-input v-model="addForm.bomUnit" placeholder="请输入用量规格"/>
    </el-form-item>
    <el-form-item label="使用表达式" prop="bomUseExpression" ref="bomUseExpressionRef">
      <el-input v-model="addForm.bomUseExpression" placeholder="请输入使用表达式"/>
    </el-form-item>
    <el-form-item label="是否关注" prop="isFollow">
      <el-select v-model="addForm.isFollow">
        <el-option v-for="f in isFollowList" :key="f.label" :value="f.value" :label="f.label"/>
      </el-select>
    </el-form-item>

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

<style scoped lang="scss">

</style>
