<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsGoodsForecastMain} from "./ApsGoodsForecastMainType.ts"
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
const dtoUrl = ref<string>("/apsGoodsForecastMain")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
    // 商品ID
    goodsId: [
      {required: true, message: "请输入商品ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 预测编码
    forecastNo: [
      {required: true, message: "请输入预测编码", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 预测名称
    forecastName: [
      {required: true, message: "请输入预测名称", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 开始时间
    forecastBeginDate: [
      {required: true, message: "请输入开始时间", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 结束时间
    forecastEndDate: [
      {required: true, message: "请输入结束时间", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    month: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    months: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 工厂ID
    factoryId: [
      {required: true, message: "请输入工厂ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],

})


// 添加对象
const addForm = ref<ApsGoodsForecastMain>({
      goodsId: "",
      forecastNo: "",
      forecastName: "",
      forecastBeginDate: "",
      forecastEndDate: "",
      month: "",
      months: "",
      factoryId: "",
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
    <el-form-item label="商品" prop="goodsId">
      <el-input v-model="addForm.goodsId" clearable placeholder="请输入商品ID"/>
    </el-form-item>
    <el-form-item label="预测编码" prop="forecastNo">
      <el-input v-model="addForm.forecastNo" clearable placeholder="请输入预测编码"/>
    </el-form-item>
    <el-form-item label="预测名称" prop="forecastName">
      <el-input v-model="addForm.forecastName" clearable placeholder="请输入预测名称"/>
    </el-form-item>
    <el-form-item label="开始时间" prop="forecastBeginDate">
      <el-input v-model="addForm.forecastBeginDate" clearable placeholder="请输入开始时间"/>
    </el-form-item>
    <el-form-item label="结束时间" prop="forecastEndDate">
      <el-input v-model="addForm.forecastEndDate" clearable placeholder="请输入结束时间"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="month">
      <el-input v-model="addForm.month" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="months">
      <el-input v-model="addForm.months" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="工厂" prop="factoryId">
      <el-input v-model="addForm.factoryId" clearable placeholder="请输入工厂ID"/>
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

