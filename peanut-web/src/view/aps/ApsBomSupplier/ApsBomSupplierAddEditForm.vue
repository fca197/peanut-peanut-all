<script setup lang="ts">
import { onMounted, ref } from "vue"
import { getById, pinyin4jSzm, postNoResult } from "@/common/utils/common-js.ts"
import { type FormInstance, FormRules } from "element-plus"
import { ApsBomSupplier, supplierStatusList } from "@v/aps/ApsBomSupplier/ApsBomSupplierType.ts"
import { DistrictCode, queryDistrictByParentCode } from "@v/base/DistrictCode/DistrictCodeType.ts"

const props = defineProps({
  saveFun: {
    type: Function
  },
  editId: {
    type: String,
    required: false
  }
})

const dtoUrl = ref<string>("/apsBomSupplier")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  bomSupplierName: [
    { required: true, message: "请输入名称", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  bomSupplierCode: [
    { required: true, message: "请输入编号", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  bomSupplierPhone: [
    { required: true, message: "请输入手机", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  provinceCode: [
    { required: true, message: "请输入省", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  cityCode: [
    { required: true, message: "请输入市", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  areaCode: [
    { required: true, message: "请输入县", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  bomSupplierAddr: [
    { required: true, message: "请输入地址", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ]

})

onMounted(() => {
  loadById()
})

const addForm = ref<ApsBomSupplier | null>({
  bomSupplierName: "",
  bomSupplierCode: "",
  bomSupplierPhone: "",
  bomSupplierTel: "",
  bomSupplierEmail: "",
  provinceCode: "1010000",
  cityCode: "",
  areaCode: "",
  bomSupplierAddr: "",
  bomSupplierRemark: "",
  supplierStatus: 1,
  id: ""
})

const provinceList = ref<DistrictCode[]>([])
const cityList = ref<DistrictCode[]>([])
const areaList = ref<DistrictCode[]>([])

function loadById() {
  queryDistrictByParentCode("0").then((t) => {
    provinceList.value = t
  })
  if (!props.editId) {
    addForm.value.provinceCode = ""
    return
  }
  console.info("props.editId ", props.editId)
  getById(`${dtoUrl.value}/queryByIdList`, props.editId).then((t) => {
    addForm.value = t
    console.info(" addForm.value ", addForm.value)
  })
}

function saveForm() {
  console.info("addForm ", addForm)
  addFormRef.value?.validate((valid) => {
    if (valid) {
      if (props.editId) {
        postNoResult(`${dtoUrl.value}/updateById`, addForm.value, "修改成功", saveFormAfter)
      } else {
        postNoResult(`${dtoUrl.value}/insert`, addForm.value, "保存成功", saveFormAfter)
      }
    } else {
      ElMessage.error("表单校验失败，请检查必填项")
    }
  })
}

function saveFormAfter() {
  cancelForm()
}

function cancelForm() {
  if (props.saveFun) {
    props.saveFun()
  }
}

function loadSzm() {
  pinyin4jSzm(addForm.value.bomSupplierName).then(r => addForm.value.bomSupplierCode = r)
}

watch(() => addForm.value.provinceCode, (n) => {
  console.info("provinceCode: ", n)
  addForm.value.cityCode = undefined
  addForm.value.areaCode = undefined

  queryDistrictByParentCode(n).then((t) => {
    cityList.value = t
  })
})

watch(() => addForm.value.cityCode, (n) => {
  console.info("cityCode: ", n)
  addForm.value.areaCode = null
  queryDistrictByParentCode(n).then((t) => {
    areaList.value = t
  })
})
</script>

<template>
  <el-form label-width="80px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="名称" prop="bomSupplierName">
      <el-input v-model="addForm.bomSupplierName" clearable placeholder="请输入名称" @change="loadSzm" />
    </el-form-item>
    <el-form-item label="编号" prop="bomSupplierCode">
      <el-input v-model="addForm.bomSupplierCode" clearable placeholder="请输入编号" />
    </el-form-item>
    <el-form-item label="手机" prop="bomSupplierPhone">
      <el-input v-model="addForm.bomSupplierPhone" clearable placeholder="请输入手机" />
    </el-form-item>
    <el-form-item label="座机" prop="bomSupplierTel">
      <el-input v-model="addForm.bomSupplierTel" clearable placeholder="请输入座机" />
    </el-form-item>
    <el-form-item label="邮件" prop="bomSupplierEmail">
      <el-input v-model="addForm.bomSupplierEmail" clearable placeholder="请输入邮件" />
    </el-form-item>
    <el-form-item label="省" prop="provinceCode">
      <el-select v-model="addForm.provinceCode" style="width: 100%">
        <el-option v-for="d in provinceList" :key="d.code" :label="d.name" :value="d.code" />
      </el-select>
    </el-form-item>
    <el-form-item label="市" prop="cityCode">
      <el-select v-model="addForm.cityCode" style="width: 100%">
        <el-option v-for="d in cityList" :key="d.code" :label="d.name" :value="d.code" />
      </el-select>
    </el-form-item>
    <el-form-item label="县" prop="areaCode">
      <el-select v-model="addForm.areaCode" style="width: 100%">
        <el-option v-for="d in areaList" :key="d.code" :label="d.name" :value="d.code" />
      </el-select>
    </el-form-item>
    <el-form-item label="地址" prop="bomSupplierAddr">
      <el-input v-model="addForm.bomSupplierAddr" clearable placeholder="请输入地址" />
    </el-form-item>
    <el-form-item label="备注" prop="bomSupplierRemark">
      <el-input v-model="addForm.bomSupplierRemark" clearable placeholder="请输入备注" />
    </el-form-item>
    <el-form-item label="状态" prop="supplierStatus">
      <el-select v-model="addForm.supplierStatus" style="width: 200px">
        <el-option v-for="kv in supplierStatusList" :label="kv.label" :value="kv.value" :key="kv.value" />
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

<style
  scoped
  lang="scss"
></style>
