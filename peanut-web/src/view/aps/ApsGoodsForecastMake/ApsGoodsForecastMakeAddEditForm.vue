<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsGoodsForecastMake} from "./ApsGoodsForecastMakeType.ts"
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
const dtoUrl = ref<string>("/apsGoodsForecastMake")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
    // 商品ID
    goodsId: [
      {required: true, message: "请输入商品ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    forecastMakeMonthNo: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    forecastMakeMonthName: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    forecastMakeMonthBeginDate: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    forecastMakeMonthEndDate: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    factoryId: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    month: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    weeks: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    forecastMainId: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    isDeploy: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    bomUseBeginDate: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    bomUseEndDate: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],

})


// 添加对象
const addForm = ref<ApsGoodsForecastMake>({
      goodsId: "",
      forecastMakeMonthNo: "",
      forecastMakeMonthName: "",
      forecastMakeMonthBeginDate: "",
      forecastMakeMonthEndDate: "",
      factoryId: "",
      month: "",
      weeks: "",
      forecastMainId: "",
      isDeploy: "",
      bomUseBeginDate: "",
      bomUseEndDate: "",
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
    <el-form-item label="${column.comment}" prop="forecastMakeMonthNo">
      <el-input v-model="addForm.forecastMakeMonthNo" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="forecastMakeMonthName">
      <el-input v-model="addForm.forecastMakeMonthName" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="forecastMakeMonthBeginDate">
      <el-input v-model="addForm.forecastMakeMonthBeginDate" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="forecastMakeMonthEndDate">
      <el-input v-model="addForm.forecastMakeMonthEndDate" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="factoryId">
      <el-input v-model="addForm.factoryId" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="month">
      <el-input v-model="addForm.month" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="weeks">
      <el-input v-model="addForm.weeks" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="forecastMainId">
      <el-input v-model="addForm.forecastMainId" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="isDeploy">
      <el-input v-model="addForm.isDeploy" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="bomUseBeginDate">
      <el-input v-model="addForm.bomUseBeginDate" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="bomUseEndDate">
      <el-input v-model="addForm.bomUseEndDate" clearable placeholder="请输入${column.comment}"/>
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

