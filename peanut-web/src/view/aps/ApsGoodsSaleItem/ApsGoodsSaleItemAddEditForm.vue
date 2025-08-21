<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsGoodsSaleItem} from "./ApsGoodsSaleItemType.ts"
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
const dtoUrl = ref<string>("/apsGoodsSaleItem")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
    // 商品ID
    goodsId: [
      {required: true, message: "请输入商品ID", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    //
    saleGoodsId: [
      {required: true, message: "请输入${column.comment}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    //
    saleConfigId: [
      {required: true, message: "请输入${column.comment}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    //
    useForecast: [
      {required: true, message: "请输入${column.comment}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    //
    supplierStatus: [
      {required: true, message: "请输入${column.comment}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],

})


// 添加对象
const addForm = ref<ApsGoodsSaleItem>({
      goodsId: "",
      saleGoodsId: "",
      saleConfigId: "",
      useForecast: "",
      supplierStatus: "",
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
  })
}

// 保存
function saveForm() {
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
function saveFormAfter() {
  cancelForm()
}

// 取消方法
function cancelForm() {
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
    <el-form-item label="商品" prop="goodsId">
      <el-input v-model="addForm.goodsId" clearable placeholder="请输入商品ID"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="saleGoodsId">
      <el-input v-model="addForm.saleGoodsId" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="saleConfigId">
      <el-input v-model="addForm.saleConfigId" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="useForecast">
      <el-input v-model="addForm.useForecast" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="supplierStatus">
      <el-input v-model="addForm.supplierStatus" clearable placeholder="请输入${column.comment}"/>
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

