<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type StorePoi} from "./StorePoiType.ts"
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
const dtoUrl = ref<string>("/storePoi")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 上级编码
  poiParentCode: [
    {required: true, message: "请输入上级编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // poi编码
  poiCode: [
    {required: true, message: "请输入poi编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // poi名称
  poiName: [
    {required: true, message: "请输入poi名称", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 层级
  poiLevel: [
    {required: true, message: "请输入层级", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // poi路径
  poiPath: [
    {required: true, message: "请输入poi路径", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],

})


// 添加对象
const addForm = ref<StorePoi>({
  poiParentCode: "",
  poiCode: "",
  poiName: "",
  poiLevel: "",
  poiPath: "",
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
  <el-form v-loading="loadEntity" label-width="80px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="上级编码" prop="poiParentCode">
      <el-input
          v-model="queryForm.poiParentCode"
          clearable
          placeholder="请输入上级编码"
      />
    </el-form-item>
    <el-form-item label="poi编码" prop="poiCode">
      <el-input
          v-model="queryForm.poiCode"
          clearable
          placeholder="请输入poi编码"
      />
    </el-form-item>
    <el-form-item label="poi名称" prop="poiName">
      <el-input
          v-model="queryForm.poiName"
          clearable
          placeholder="请输入poi名称"
      />
    </el-form-item>
    <el-form-item label="层级" prop="poiLevel">
      <el-input-number
          v-model="queryForm.poiLevel"
          clearable
          placeholder="请输入"
      />
    </el-form-item>
    <el-form-item label="poi路径" prop="poiPath">
      <el-input
          v-model="queryForm.poiPath"
          clearable
          placeholder="请输入poi路径"
      />
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
