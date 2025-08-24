<script lang="ts" setup>
import {onMounted, ref} from "vue"
import {type StoreBusinessDistrict} from "./StoreBusinessDistrictType.ts"
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
const dtoUrl = ref<string>("/storeBusinessDistrict")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 品牌ID
  brandId: [
    {required: true, message: "请输入品牌ID", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 编码
  businessDistrictCode: [
    {required: true, message: "请输入编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 名称
  businessDistrictName: [
    {required: true, message: "请输入名称", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 描述
  businessDistrictDesc: [
    {required: true, message: "请输入描述", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 地址
  businessDistrictAddress: [
    {required: true, message: "请输入地址", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 国家编码
  countryCode: [
    {required: true, message: "请输入国家编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 城市编码
  provinceCode: [
    {required: true, message: "请输入城市编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 城市编码
  cityCode: [
    {required: true, message: "请输入城市编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 城市编码
  areaCode: [
    {required: true, message: "请输入城市编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 国家编码
  countryName: [
    {required: true, message: "请输入国家编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 城市编码
  provinceName: [
    {required: true, message: "请输入城市编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 城市编码
  cityName: [
    {required: true, message: "请输入城市编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 城市编码
  areaName: [
    {required: true, message: "请输入城市编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 半径
  businessDistrictRadius: [
    {required: true, message: "请输入半径", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 商圈级别ID
  businessDistrictLevelId: [
    {required: true, message: "请输入商圈级别ID", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 商圈类别ID
  businessDistrictTypeId: [
    {required: true, message: "请输入商圈类别ID", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 纬度
  centerLat: [
    {required: true, message: "请输入纬度", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 经度
  centerLng: [
    {required: true, message: "请输入经度", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],

})


// 添加对象
const addForm = ref<StoreBusinessDistrict>({
  brandId: "",
  businessDistrictCode: "",
  businessDistrictName: "",
  businessDistrictDesc: "",
  businessDistrictAddress: "",
  countryCode: "",
  provinceCode: "",
  cityCode: "",
  areaCode: "",
  countryName: "",
  provinceName: "",
  cityName: "",
  areaName: "",
  businessDistrictRadius: "",
  businessDistrictLevelId: "",
  businessDistrictTypeId: "",
  centerLat: "",
  centerLng: "",
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
    <el-form-item label="品牌ID" prop="brandId">
      <el-input
          v-model="queryForm.brandId"
          clearable
          placeholder="请输入品牌ID"
      />
    </el-form-item>
    <el-form-item label="编码" prop="businessDistrictCode">
      <el-input
          v-model="queryForm.businessDistrictCode"
          clearable
          placeholder="请输入编码"
      />
    </el-form-item>
    <el-form-item label="名称" prop="businessDistrictName">
      <el-input
          v-model="queryForm.businessDistrictName"
          clearable
          placeholder="请输入名称"
      />
    </el-form-item>
    <el-form-item label="描述" prop="businessDistrictDesc">
      <el-input
          v-model="queryForm.businessDistrictDesc"
          clearable
          placeholder="请输入描述"
      />
    </el-form-item>
    <el-form-item label="地址" prop="businessDistrictAddress">
      <el-input
          v-model="queryForm.businessDistrictAddress"
          clearable
          placeholder="请输入地址"
      />
    </el-form-item>
    <el-form-item label="国家编码" prop="countryCode">
      <el-input
          v-model="queryForm.countryCode"
          clearable
          placeholder="请输入国家编码"
      />
    </el-form-item>
    <el-form-item label="城市编码" prop="provinceCode">
      <el-input
          v-model="queryForm.provinceCode"
          clearable
          placeholder="请输入城市编码"
      />
    </el-form-item>
    <el-form-item label="城市编码" prop="cityCode">
      <el-input
          v-model="queryForm.cityCode"
          clearable
          placeholder="请输入城市编码"
      />
    </el-form-item>
    <el-form-item label="城市编码" prop="areaCode">
      <el-input
          v-model="queryForm.areaCode"
          clearable
          placeholder="请输入城市编码"
      />
    </el-form-item>
    <el-form-item label="国家编码" prop="countryName">
      <el-input
          v-model="queryForm.countryName"
          clearable
          placeholder="请输入国家编码"
      />
    </el-form-item>
    <el-form-item label="城市编码" prop="provinceName">
      <el-input
          v-model="queryForm.provinceName"
          clearable
          placeholder="请输入城市编码"
      />
    </el-form-item>
    <el-form-item label="城市编码" prop="cityName">
      <el-input
          v-model="queryForm.cityName"
          clearable
          placeholder="请输入城市编码"
      />
    </el-form-item>
    <el-form-item label="城市编码" prop="areaName">
      <el-input
          v-model="queryForm.areaName"
          clearable
          placeholder="请输入城市编码"
      />
    </el-form-item>
    <el-form-item label="半径" prop="businessDistrictRadius">
      <el-input
          v-model="queryForm.businessDistrictRadius"
          clearable
          placeholder="请输入半径"
      />
    </el-form-item>
    <el-form-item label="商圈级别ID" prop="businessDistrictLevelId">
      <el-input
          v-model="queryForm.businessDistrictLevelId"
          clearable
          placeholder="请输入商圈级别ID"
      />
    </el-form-item>
    <el-form-item label="商圈类别ID" prop="businessDistrictTypeId">
      <el-input
          v-model="queryForm.businessDistrictTypeId"
          clearable
          placeholder="请输入商圈类别ID"
      />
    </el-form-item>
    <el-form-item label="纬度" prop="centerLat">
      <el-input
          v-model="queryForm.centerLat"
          clearable
          placeholder="请输入纬度"
      />
    </el-form-item>
    <el-form-item label="经度" prop="centerLng">
      <el-input
          v-model="queryForm.centerLng"
          clearable
          placeholder="请输入经度"
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
