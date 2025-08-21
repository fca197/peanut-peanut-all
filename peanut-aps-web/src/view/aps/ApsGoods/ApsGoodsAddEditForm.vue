<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsGoods} from "./ApsGoodsType.ts"
import {getById, postNoResult, postResultInfoList} from "@/common/utils/common-js.ts"
import {type FormInstance, FormRules} from "element-plus"
import {Factory, queryFactoryList} from "@v/base/Factory/FactoryType.ts";
import {ApsProcessPath} from "@v/aps/ApsProcessPath/ApsProcessPathType.ts";
import {ApsProduceProcess} from "@v/aps/ApsProduceProcess/ApsProduceProcessType.ts";

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
const dtoUrl = ref<string>("/apsGoods")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 商品名称
  goodsName: [
    {required: true, message: "请输入商品名称", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 商品备注
  goodsRemark: [
    {required: true, message: "请输入商品备注", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 工厂ID
  factoryId: [
    {required: true, message: "请输入工厂ID", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
})

// 添加对象
const addForm = ref<ApsGoods | null>({
  goodsName: "", // 商品名称
  goodsRemark: "", // 商品备注
  supplierStatus: "", //
  factoryId: "", // 工厂ID
  processPathId: "", // 工艺路线
  produceProcessId: "", // 制造流水线ID produceProcess
  id: ""
})
const factoryList = ref<Factory[]>([])
const processPathList = ref<ApsProcessPath []>([]);
const ApsProduceProcessList = ref<ApsProduceProcess []>([]);

watch(() => addForm.value?.factoryId, (n) => {
  postResultInfoList("/apsProcessPath/queryPageList", {queryPage: false, data: {factoryId: n}}).then(r => processPathList.value = r)
  postResultInfoList("/apsProduceProcess/queryPageList", {queryPage: false, data: {factoryId: n}}).then(r => ApsProduceProcessList.value = r)
  addForm.value.processPathId = undefined
  addForm.value.produceProcessId = undefined
})

// 页面加载事件
onMounted(() => {
  loadById()
  queryFactoryList().then((r) => {
    factoryList.value = r
  })
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


//保存
function saveForm() {
  console.info("addForm ", addForm)
  addFormRef.value?.validate((valid) => {
    if (valid) {
      // 存在ID ，调用更新
      if (props.editId) {
        postNoResult(`${dtoUrl.value}/updateById`, addForm.value, "修改成功", saveFormAfter)
      } else {
        //调用保存
        postNoResult(`${dtoUrl.value}/insert`, addForm.value, "保存成功", saveFormAfter)
      }
    } else {
      ElMessage.error("表单校验失败，请检查必填项")
    }
  })
}

//保存成功后，方法， 目前关闭弹窗
function saveFormAfter() {
  cancelForm()
}

// 取消方法
function cancelForm() {
  if (props.saveFun) {
    props.saveFun()
  }
}
</script>

<template>
  <el-form label-width="100px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="商品名称" prop="goodsName">
      <el-input v-model="addForm.goodsName" clearable placeholder="请输入商品名称"/>
    </el-form-item>
    <el-form-item label="商品备注" prop="goodsRemark">
      <el-input v-model="addForm.goodsRemark" clearable placeholder="请输入商品备注"/>
    </el-form-item>
    <el-form-item label="工厂" prop="factoryId">
      <el-select v-model="addForm.factoryId" style="width: 100%">
        <el-option v-for="f in factoryList" :value="f.id" :label="f.factoryName" :key="f.id"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="工艺路线" prop="processPathId">
      <el-select v-model="addForm.processPathId" clearable placeholder="请选择工艺路线">
        <el-option
          v-for="t in processPathList"
          :value="t.id"
          :label="t.processPathName"
          :key="t.id"
        />
      </el-select>
    </el-form-item>
    <el-form-item label="制造流水线" prop="produceProcessId">
      <el-select v-model="addForm.produceProcessId" clearable placeholder="请选择制造流水线">
        <el-option
          v-for="t in ApsProduceProcessList"
          :key="t.id"
          :label="t.produceProcessName"
          :value="t.id"
        />
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
