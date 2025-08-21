<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsOrderGoodsBomKittingVersion} from "./ApsOrderGoodsBomKittingVersionType.ts"
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
const dtoUrl = ref<string>("/apsOrderGoodsBomKittingVersion")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 齐套版本编码
  kittingVersionNo: [
    {required: true, message: "请输入齐套版本编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 齐套版本名称
  kittingVersionName: [
    {required: true, message: "请输入齐套版本名称", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 齐套来源
  kittingVersionSource: [
    {required: true, message: "请输入齐套来源", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 订单数量
  orderCount: [
    {required: true, message: "请输入订单数量", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 齐套数
  kittingSuccessCount: [
    {required: true, message: "请输入齐套数", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 非齐套数
  kittingFailCount: [
    {required: true, message: "请输入非齐套数", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 齐套率
  kittingRate: [
    {required: true, message: "请输入齐套率", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 齐套状态 已齐套， 部分齐套，未齐套
  kittingStatus: [
    {required: true, message: "请输入齐套状态 已齐套， 部分齐套，未齐套", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 缺失物料前10 [{id: label}]
  kittingMissingBom: [
    {required: true, message: "请输入缺失物料前10 [{id: label}]", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 计算日期
  createDate: [
    {required: true, message: "请输入计算日期", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 工厂ID
  factoryId: [
    {required: true, message: "请输入工厂ID", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 状态
  goodsStatusId: [
    {required: true, message: "请输入状态", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 使用时间
  bomUseDate: [
    {required: true, message: "请输入使用时间", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],

})


// 添加对象
const addForm = ref<ApsOrderGoodsBomKittingVersion>({
  kittingVersionNo: "",
  kittingVersionName: "",
  kittingVersionSource: "",
  orderCount: "",
  kittingSuccessCount: "",
  kittingFailCount: "",
  kittingRate: "",
  kittingStatus: "",
  kittingMissingBom: "",
  createDate: "",
  factoryId: "",
  goodsStatusId: "",
  bomUseDate: "",
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
    <el-form-item label="齐套版本编码" prop="kittingVersionNo">
      <el-input v-model="addForm.kittingVersionNo" clearable placeholder="请输入齐套版本编码"/>
    </el-form-item>
    <el-form-item label="齐套版本名称" prop="kittingVersionName">
      <el-input v-model="addForm.kittingVersionName" clearable placeholder="请输入齐套版本名称"/>
    </el-form-item>
    <el-form-item label="齐套来源" prop="kittingVersionSource">
      <el-input v-model="addForm.kittingVersionSource" clearable placeholder="请输入齐套来源"/>
    </el-form-item>
    <el-form-item label="订单数量" prop="orderCount">
      <el-input v-model="addForm.orderCount" clearable placeholder="请输入订单数量"/>
    </el-form-item>
    <el-form-item label="齐套数" prop="kittingSuccessCount">
      <el-input v-model="addForm.kittingSuccessCount" clearable placeholder="请输入齐套数"/>
    </el-form-item>
    <el-form-item label="非齐套数" prop="kittingFailCount">
      <el-input v-model="addForm.kittingFailCount" clearable placeholder="请输入非齐套数"/>
    </el-form-item>
    <el-form-item label="齐套率" prop="kittingRate">
      <el-input v-model="addForm.kittingRate" clearable placeholder="请输入齐套率"/>
    </el-form-item>
    <el-form-item label="齐套状态 已齐套， 部分齐套，未齐套" prop="kittingStatus">
      <el-input
        v-model="addForm.kittingStatus" clearable
        placeholder="请输入齐套状态 已齐套， 部分齐套，未齐套"/>
    </el-form-item>
    <el-form-item label="缺失物料前10 [{id: label}]" prop="kittingMissingBom">
      <el-input
        v-model="addForm.kittingMissingBom" clearable
        placeholder="请输入缺失物料前10 [{id: label}]"/>
    </el-form-item>
    <el-form-item label="计算日期" prop="createDate">
      <el-input v-model="addForm.createDate" clearable placeholder="请输入计算日期"/>
    </el-form-item>
    <el-form-item label="工厂ID" prop="factoryId">
      <el-input v-model="addForm.factoryId" clearable placeholder="请输入工厂ID"/>
    </el-form-item>
    <el-form-item label="状态" prop="goodsStatusId">
      <el-input v-model="addForm.goodsStatusId" clearable placeholder="请输入状态"/>
    </el-form-item>
    <el-form-item label="使用时间" prop="bomUseDate">
      <el-input v-model="addForm.bomUseDate" clearable placeholder="请输入使用时间"/>
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
