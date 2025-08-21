<template>
  <el-form label-width="80px" :model="addForm" ref="addFormRef" :rules="checkRules">
      <el-form-item label="物流路径id" prop="logisticsPathId">
        <el-input v-model="addForm.logisticsPathId" clearable placeholder="请输入物流路径id"/>
      </el-form-item>
      <el-form-item label="省编码" prop="provinceCode">
        <el-input v-model="addForm.provinceCode" clearable placeholder="请输入省编码"/>
      </el-form-item>
      <el-form-item label="省名称" prop="provinceName">
        <el-input v-model="addForm.provinceName" clearable placeholder="请输入省名称"/>
      </el-form-item>
      <el-form-item label="市编码" prop="cityCode">
        <el-input v-model="addForm.cityCode" clearable placeholder="请输入市编码"/>
      </el-form-item>
      <el-form-item label="市名称" prop="cityName">
        <el-input v-model="addForm.cityName" clearable placeholder="请输入市名称"/>
      </el-form-item>
      <el-form-item label="运输天数" prop="transportDay">
        <el-input v-model="addForm.transportDay" clearable placeholder="请输入运输天数"/>
      </el-form-item>
      <el-form-item label="是否默认 0 否,1 是" prop="isDefault">
        <el-input v-model="addForm.isDefault" clearable placeholder="请输入是否默认 0 否,1 是"/>
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

<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsLogisticsPathItem} from "./ApsLogisticsPathItemType.ts"
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
const dtoUrl = ref<string>("/apsLogisticsPathItem")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
    // 物流路径id
    logisticsPathId: [
      {required: true, message: "请输入物流路径id", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 省编码
    provinceCode: [
      {required: true, message: "请输入省编码", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 省名称
    provinceName: [
      {required: true, message: "请输入省名称", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 市编码
    cityCode: [
      {required: true, message: "请输入市编码", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 市名称
    cityName: [
      {required: true, message: "请输入市名称", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 运输天数
    transportDay: [
      {required: true, message: "请输入运输天数", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 是否默认 0 否,1 是
    isDefault: [
      {required: true, message: "请输入是否默认 0 否,1 是", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 工厂ID
    factoryId: [
      {required: true, message: "请输入工厂ID", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],

})


// 添加对象
const addForm = ref<ApsLogisticsPathItem>({
      logisticsPathId: "",
      provinceCode: "",
      provinceName: "",
      cityCode: "",
      cityName: "",
      transportDay: "",
      isDefault: "",
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

<style scoped lang="scss">

</style>

