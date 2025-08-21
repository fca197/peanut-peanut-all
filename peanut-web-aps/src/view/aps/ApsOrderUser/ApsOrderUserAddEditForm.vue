<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsOrderUser} from "./ApsOrderUserType.ts"
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
const dtoUrl = ref<string>("/apsOrderUser")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
    // 
    orderId: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    userName: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    userPhone: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    userSex: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    countryCode: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    provinceCode: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    cityCode: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    areaCode: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    userAddress: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    userRemark: [
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
const addForm = ref<ApsOrderUser>({
      orderId: "",
      userName: "",
      userPhone: "",
      userSex: "",
      countryCode: "",
      provinceCode: "",
      cityCode: "",
      areaCode: "",
      userAddress: "",
      userRemark: "",
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
    <el-form-item label="${column.comment}" prop="orderId">
      <el-input v-model="addForm.orderId" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="userName">
      <el-input v-model="addForm.userName" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="userPhone">
      <el-input v-model="addForm.userPhone" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="userSex">
      <el-input v-model="addForm.userSex" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="countryCode">
      <el-input v-model="addForm.countryCode" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="provinceCode">
      <el-input v-model="addForm.provinceCode" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="cityCode">
      <el-input v-model="addForm.cityCode" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="areaCode">
      <el-input v-model="addForm.areaCode" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="userAddress">
      <el-input v-model="addForm.userAddress" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="userRemark">
      <el-input v-model="addForm.userRemark" clearable placeholder="请输入${column.comment}"/>
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

