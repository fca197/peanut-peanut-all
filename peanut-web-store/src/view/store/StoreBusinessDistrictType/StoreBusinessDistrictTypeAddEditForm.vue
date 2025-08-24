<script lang="ts" setup>
import {onMounted, ref} from "vue"
import {type StoreBusinessDistrictType} from "./StoreBusinessDistrictTypeType.ts"
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
const loadEntity = ref<boolean>(true)
// 对象URL
const dtoUrl = ref<string>("/storeBusinessDistrictType")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 类型名称
  businessDistrictTypeName: [
    {required: true, message: "请输入类型名称", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 类型编码
  businessDistrictTypeCode: [
    {required: true, message: "请输入类型编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 类型描述
  businessDistrictTypeDesc: [
    {required: true, message: "请输入类型描述", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],

})


// 添加对象
const addForm = ref<StoreBusinessDistrictType>({
  businessDistrictTypeName: "",
  businessDistrictTypeCode: "",
  businessDistrictTypeDesc: "",
  id: ""
})

const loadById = () => {
  if (!props.editId) {
    return
  }
  console.info("props.editId ", props.editId)
  getById(`${dtoUrl.value}/queryByIdList`, props.editId).then((t) => {
    addForm.value = t
    console.info(" addForm.value ", addForm.value)
    loadEntity.value = false
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
  <el-form ref="addFormRef" v-loading="loadEntity" :model="addForm" :rules="checkRules" label-width="80px">
    <el-form-item label="类型名称" prop="businessDistrictTypeName">
      <el-input
          v-model="queryForm.businessDistrictTypeName"
          clearable
          placeholder="请输入类型名称"
      />
    </el-form-item>
    <el-form-item label="类型编码" prop="businessDistrictTypeCode">
      <el-input
          v-model="queryForm.businessDistrictTypeCode"
          clearable
          placeholder="请输入类型编码"
      />
    </el-form-item>
    <el-form-item label="类型描述" prop="businessDistrictTypeDesc">
      <el-input
          v-model="queryForm.businessDistrictTypeDesc"
          clearable
          placeholder="请输入类型描述"
      />
    </el-form-item>
  </el-form>
  <el-row class="addFormBtnRow">
    <el-button icon="close" type="info" @click="cancelForm">
      取消
    </el-button>
    <el-button icon="check" type="primary" @click="saveForm">
      确定
    </el-button>
  </el-row>
</template>

<style lang="scss" scoped>
</style>
