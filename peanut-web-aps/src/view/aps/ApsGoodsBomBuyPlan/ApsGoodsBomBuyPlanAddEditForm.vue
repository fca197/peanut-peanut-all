<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsGoodsBomBuyPlan} from "./ApsGoodsBomBuyPlanType.ts"
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
const dtoUrl = ref<string>("/apsGoodsBomBuyPlan")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
    // 计划名称
    planName: [
      {required: true, message: "请输入计划名称", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 总价
    planTotalAmount: [
      {required: true, message: "请输入总价", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 计划来源
    planSource: [
      {required: true, message: "请输入计划来源", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 计划备注
    planRemark: [
      {required: true, message: "请输入计划备注", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 购买类型
    buyPlanType: [
      {required: true, message: "请输入购买类型", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 是否关注
    isFollow: [
      {required: true, message: "请输入是否关注", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 日期
    bomUseDate: [
      {required: true, message: "请输入日期", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],

})


// 添加对象
const addForm = ref<ApsGoodsBomBuyPlan>({
      planName: "",
      planTotalAmount: "",
      planSource: "",
      planRemark: "",
      buyPlanType: "",
      isFollow: "",
      bomUseDate: "",
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

<template>
  <el-form label-width="80px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="计划名称" prop="planName">
      <el-input v-model="addForm.planName" clearable placeholder="请输入计划名称"/>
    </el-form-item>
    <el-form-item label="总价" prop="planTotalAmount">
      <el-input v-model="addForm.planTotalAmount" clearable placeholder="请输入总价"/>
    </el-form-item>
    <el-form-item label="计划来源" prop="planSource">
      <el-input v-model="addForm.planSource" clearable placeholder="请输入计划来源"/>
    </el-form-item>
    <el-form-item label="计划备注" prop="planRemark">
      <el-input v-model="addForm.planRemark" clearable placeholder="请输入计划备注"/>
    </el-form-item>
    <el-form-item label="购买类型" prop="buyPlanType">
      <el-input v-model="addForm.buyPlanType" clearable placeholder="请输入购买类型"/>
    </el-form-item>
    <el-form-item label="是否关注" prop="isFollow">
      <el-input v-model="addForm.isFollow" clearable placeholder="请输入是否关注"/>
    </el-form-item>
    <el-form-item label="日期" prop="bomUseDate">
      <el-input v-model="addForm.bomUseDate" clearable placeholder="请输入日期"/>
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

