<script setup lang="ts">
import { onMounted, ref } from "vue"
import { getById, postNoResult } from "@/common/utils/common-js.ts"
import { type FormInstance, FormRules } from "element-plus"
import { ApsBomGroup, apsGroupDefaultProps, queryApsBomGroupTree } from "@v/aps/ApsBomGroup/ApsBomGroupType.ts"

const props = defineProps({
  saveFun: {
    type: Function
  },
  editId: {
    type: String,
    required: false
  }
})

const bomGroupTreeRef = ref(null)
const dtoUrl = ref<string>("/apsBomGroup")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  groupCode: [
    { required: true, message: "请输入组编码", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  groupName: [
    { required: true, message: "请输入组名称", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  parentId: [
    { required: true, message: "请选择上级分组", trigger: "blur" }
  ]
})

onMounted(() => {
  loadById()
  queryData()
})

const addForm = ref({
  groupCode: undefined,
  groupName: undefined,
  parentId: undefined,
  pathId: undefined,
  id: undefined
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

const bomGroupList = ref<ApsBomGroup[]>([])

function queryData() {
  queryApsBomGroupTree().then((t) => {
    // t = removeLeaves(t)
    bomGroupList.value = t
  })
}

function removeLeaves(nodes) {
  if (!nodes) return [];
  return nodes.filter((node) => {
    if (node.children && node.children.length > 0) {
      node.children = removeLeaves(node.children);
      return true;
    }
    return false;
  });
};

const handleCheckChange = (data: any, checked: boolean, indeterminate: boolean) => {
  console.log(data, checked, indeterminate)
  console.log("bomGroupTreeRef.value.getCheckedKeys() ", bomGroupTreeRef.value.getCheckedKeys())
  if (bomGroupTreeRef.value.getCheckedKeys().length > 1) {
    bomGroupTreeRef.value.setCheckedKeys([data.id], true)
  }
  addForm.value.parentId = data.id
}
</script>

<template>
  <el-form label-width="80px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="组编码" prop="groupCode">
      <el-input v-model="addForm.groupCode" clearable placeholder="请输入组编码" />
    </el-form-item>
    <el-form-item label="组名称" prop="groupName">
      <el-input v-model="addForm.groupName" clearable placeholder="请输入组名称" />
    </el-form-item>
    <el-form-item label="上级分组" prop="parentId">
      <el-tree-select v-model="addForm.parentId" ref="bomGroupTreeRef" node-key="id" :highlight-current="true"
        :check-strictly="true" show-checkbox :data="bomGroupList" :props="apsGroupDefaultProps" />
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

<style scoped lang="scss"></style>
