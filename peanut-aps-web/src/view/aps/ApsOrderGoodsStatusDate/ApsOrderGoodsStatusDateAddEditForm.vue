<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsOrderGoodsStatusDate} from "./ApsOrderGoodsStatusDateType.ts"
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
const dtoUrl = ref<string>("/apsOrderGoodsStatusDate")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
    // 
    orderId: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 商品ID
    goodsId: [
      {required: true, message: "请输入商品ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 状态名称
    goodsStatusName: [
      {required: true, message: "请输入状态名称", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 订单状态
    goodsStatusId: [
      {required: true, message: "请输入订单状态", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 工厂ID
    factoryId: [
      {required: true, message: "请输入工厂ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 状态索引
    statusIndex: [
      {required: true, message: "请输入状态索引", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 预计开始时间
    expectMakeBeginTime: [
      {required: true, message: "请输入预计开始时间", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 预计结束时间
    expectMakeEndTime: [
      {required: true, message: "请输入预计结束时间", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 实际开始时间
    actualMakeBeginTime: [
      {required: true, message: "请输入实际开始时间", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 实际结束时间
    actualMakeEndTime: [
      {required: true, message: "请输入实际结束时间", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],

})


// 添加对象
const addForm = ref<ApsOrderGoodsStatusDate>({
      orderId: "",
      goodsId: "",
      goodsStatusName: "",
      goodsStatusId: "",
      factoryId: "",
      statusIndex: "",
      expectMakeBeginTime: "",
      expectMakeEndTime: "",
      actualMakeBeginTime: "",
      actualMakeEndTime: "",
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
    <el-form-item label="商品" prop="goodsId">
      <el-input v-model="addForm.goodsId" clearable placeholder="请输入商品ID"/>
    </el-form-item>
    <el-form-item label="状态名称" prop="goodsStatusName">
      <el-input v-model="addForm.goodsStatusName" clearable placeholder="请输入状态名称"/>
    </el-form-item>
    <el-form-item label="订单状态" prop="goodsStatusId">
      <el-input v-model="addForm.goodsStatusId" clearable placeholder="请输入订单状态"/>
    </el-form-item>
    <el-form-item label="工厂" prop="factoryId">
      <el-input v-model="addForm.factoryId" clearable placeholder="请输入工厂ID"/>
    </el-form-item>
    <el-form-item label="状态索引" prop="statusIndex">
      <el-input v-model="addForm.statusIndex" clearable placeholder="请输入状态索引"/>
    </el-form-item>
    <el-form-item label="预计开始时间" prop="expectMakeBeginTime">
      <el-input v-model="addForm.expectMakeBeginTime" clearable placeholder="请输入预计开始时间"/>
    </el-form-item>
    <el-form-item label="预计结束时间" prop="expectMakeEndTime">
      <el-input v-model="addForm.expectMakeEndTime" clearable placeholder="请输入预计结束时间"/>
    </el-form-item>
    <el-form-item label="实际开始时间" prop="actualMakeBeginTime">
      <el-input v-model="addForm.actualMakeBeginTime" clearable placeholder="请输入实际开始时间"/>
    </el-form-item>
    <el-form-item label="实际结束时间" prop="actualMakeEndTime">
      <el-input v-model="addForm.actualMakeEndTime" clearable placeholder="请输入实际结束时间"/>
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

