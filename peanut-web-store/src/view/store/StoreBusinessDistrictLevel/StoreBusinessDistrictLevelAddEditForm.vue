<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type StoreBusinessDistrictLevel} from "./StoreBusinessDistrictLevelType.ts"
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
const dtoUrl = ref<string>("/storeBusinessDistrictLevel")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 商圈名称
  businessDistrictLevelName: [
    {required: true, message: "请输入商圈名称", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],

})


// 添加对象
const addForm = ref<StoreBusinessDistrictLevel>({
  businessDistrictLevelName: "",
  businessDistrictLevelDesc: "",
  businessDistrictLevelColor: "",
  id: ""
})

const loadById = () => {
  if (!props.editId) {
    loadEntity.value = false
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
    <el-form-item label="商圈名称" prop="businessDistrictLevelName">
      <el-input
          v-model="addForm.businessDistrictLevelName"
          clearable
          placeholder="请输入商圈名称"
      />
    </el-form-item>

    <el-form-item label="商圈颜色" prop="businessDistrictLevelColor">
      <el-color-picker
          v-model="addForm.businessDistrictLevelColor"
          clearable
          placeholder="请输入商圈颜色"
      />
    </el-form-item>
    <el-form-item label="商圈描述" prop="businessDistrictLevelDesc">
      <el-input
          :rows="5"
          maxlength="300"
          show-word-limit
          type="textarea"
          v-model="addForm.businessDistrictLevelDesc"
          clearable
          placeholder="请输入商圈描述"
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
