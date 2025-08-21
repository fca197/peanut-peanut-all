<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsOrderGoods} from "./ApsOrderGoodsType.ts"
import {getById, postNoResult} from "@/common/utils/common-js.ts"
import {type FormInstance, FormRules} from "element-plus"

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
const dtoUrl = ref<string>("/apsOrderGoods")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
    // 
    orderId: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 商品ID
    goodsId: [
      {required: true, message: "请输入商品ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    goodsName: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    goodsRemark: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 总价
    goodsAmount: [
      {required: true, message: "请输入总价", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 成本价
    goodsPrice: [
      {required: true, message: "请输入成本价", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 成本价
    goodsTotalPrice: [
      {required: true, message: "请输入成本价", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    goodsUnit: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 成本价
    goodsUnitPrice: [
      {required: true, message: "请输入成本价", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 成本价
    goodsUnitTotalPrice: [
      {required: true, message: "请输入成本价", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 订单状态
    goodsStatusId: [
      {required: true, message: "请输入订单状态", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 工厂ID
    factoryId: [
      {required: true, message: "请输入工厂ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 订单状态
    apsStatusId: [
      {required: true, message: "请输入订单状态", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],

})


// 添加对象
const addForm = ref<ApsOrderGoods>({
      orderId: "",
      goodsId: "",
      goodsName: "",
      goodsRemark: "",
      goodsAmount: "",
      goodsPrice: "",
      goodsTotalPrice: "",
      goodsUnit: "",
      goodsUnitPrice: "",
      goodsUnitTotalPrice: "",
      goodsStatusId: "",
      factoryId: "",
      apsStatusId: "",
      id: "" 
})

const  loadById = () => {
  if (!props.editId) {
    return
  }
  console.info("props.editId ", props.editId)
  getById(`${dtoUrl.value}/queryByIdList`, props.editId).then((t) => {
    addForm.value = t
    console.info(" addForm.value ", addForm.value)
  })
}

// 保存
const saveForm = () => {
  console.info("addForm ", addForm)
  addFormRef.value?.validate((valid) => {
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
const saveFormAfter = () => {
  cancelForm()
}

// 取消方法
const cancelForm = () => {
  if (props.saveFun) {
    props.saveFun()
  }
}

// 页面加载事件
onMounted(() => {
  loadById()
})
</script>

<template>
  <el-form label-width="80px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="${column.comment}" prop="orderId">
      <el-input v-model="addForm.orderId" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="商品" prop="goodsId">
      <el-input v-model="addForm.goodsId" clearable placeholder="请输入商品ID"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="goodsName">
      <el-input v-model="addForm.goodsName" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="goodsRemark">
      <el-input v-model="addForm.goodsRemark" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="总价" prop="goodsAmount">
      <el-input v-model="addForm.goodsAmount" clearable placeholder="请输入总价"/>
    </el-form-item>
    <el-form-item label="成本价" prop="goodsPrice">
      <el-input v-model="addForm.goodsPrice" clearable placeholder="请输入成本价"/>
    </el-form-item>
    <el-form-item label="成本价" prop="goodsTotalPrice">
      <el-input v-model="addForm.goodsTotalPrice" clearable placeholder="请输入成本价"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="goodsUnit">
      <el-input v-model="addForm.goodsUnit" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="成本价" prop="goodsUnitPrice">
      <el-input v-model="addForm.goodsUnitPrice" clearable placeholder="请输入成本价"/>
    </el-form-item>
    <el-form-item label="成本价" prop="goodsUnitTotalPrice">
      <el-input v-model="addForm.goodsUnitTotalPrice" clearable placeholder="请输入成本价"/>
    </el-form-item>
    <el-form-item label="订单状态" prop="goodsStatusId">
      <el-input v-model="addForm.goodsStatusId" clearable placeholder="请输入订单状态"/>
    </el-form-item>
    <el-form-item label="工厂" prop="factoryId">
      <el-input v-model="addForm.factoryId" clearable placeholder="请输入工厂ID"/>
    </el-form-item>
    <el-form-item label="订单状态" prop="apsStatusId">
      <el-input v-model="addForm.apsStatusId" clearable placeholder="请输入订单状态"/>
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

