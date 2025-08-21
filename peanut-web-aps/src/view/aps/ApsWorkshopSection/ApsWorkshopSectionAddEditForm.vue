<template>
  <el-form label-width="80px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="工厂" prop="factoryId">
      <el-select v-model="addForm.factoryId" style="width: 100%">
        <el-option v-for="f in factoryList" :key="f.id" :value="f.id" :label="f.factoryName"/>
      </el-select>
    </el-form-item>
    <el-form-item label="名称" prop="sectionName">
      <el-input v-model="addForm.sectionName" clearable placeholder="请输入名称" @change="loadSzm"/>
    </el-form-item>
    <el-form-item label="编码" prop="sectionCode">
      <el-input v-model="addForm.sectionCode" clearable placeholder="请输入"/>
    </el-form-item>
    <el-form-item label="编码" prop="rowIndex">
      <el-input-number :min="1" :max="100" v-model="addForm.rowIndex" clearable placeholder="请输入"/>
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
import {type ApsWorkshopSection} from "./ApsWorkshopSectionType.ts"
import {getById, pinyin4jSzm, postNoResult} from "@/common/utils/common-js.ts"
import {type FormInstance, FormRules} from "element-plus"
import {Factory, queryFactoryList} from "@v/base/Factory/FactoryType.ts";

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
const dtoUrl = ref<string>("/workshopSection")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 工厂ID
  factoryId: [
    {required: true, message: "请输入工厂ID", trigger: "blur" },
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  //
  sectionName: [
    {required: true, message: "请输入", trigger: "blur" },
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  //
  sectionCode: [
    {required: true, message: "请输入", trigger: "blur" },
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ]
})

const factoryList = ref<Factory[]>([])
// 页面加载事件
onMounted(() => {
  loadById()
  queryFactoryList().then(t => factoryList.value = t)
})
// 添加对象
const addForm = ref<ApsWorkshopSection>({
  factoryId: "",
  sectionName: "",
  sectionCode: "",
  sectionType: "",
  sectionStatus: "",
  rowIndex: 1,
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

function loadSzm() {
  pinyin4jSzm(addForm.value.sectionName).then(r => addForm.value.sectionCode = r)
}
</script>

<style scoped lang="scss">

</style>

