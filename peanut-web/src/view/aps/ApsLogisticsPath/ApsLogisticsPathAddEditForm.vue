<template>
  <el-form label-width="80px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="工厂" prop="factoryId">
      <el-select v-model="addForm.factoryId">
        <el-option v-for="f in factoryList" :key="f.id" :label="f.factoryName" :value="f.id"/>
      </el-select>
    </el-form-item>
    <el-form-item label="名称" prop="logisticsPathName">
      <el-input v-model="addForm.logisticsPathName" clearable placeholder="请输入名称" @change="loadSzm"/>
    </el-form-item>
    <el-form-item label="编码" prop="logisticsPathCode">
      <el-input v-model="addForm.logisticsPathCode" clearable placeholder="请输入编码"/>
    </el-form-item>
    <el-form-item label="备注" prop="logisticsPathRemark">
      <el-input v-model="addForm.logisticsPathRemark" clearable placeholder="请输入备注"/>
    </el-form-item>
    <el-form-item label="默认" prop="isDefault">
      <el-select v-model="addForm.isDefault">
        <el-option v-for="kv in isDefaultList" :key="kv.value" :value="kv.value" :label="kv.label"/>
      </el-select>
    </el-form-item>
    <el-form-item label="省市" prop="apsLogisticsPathItemList">
      <el-form-item>
        <el-input-number v-model="defaultAllDayCount" :min="0" :max="100" placeholder="请输入天数"/>
        <el-button type="primary" @click="defaultAllDayCountFun">
          统一设置
        </el-button>
      </el-form-item>
      <ElTable :data="addForm.apsLogisticsPathItemList">
        <ElTableColumn label="省编码" prop="provinceCode"/>
        <ElTableColumn label="省名称" prop="provinceName"/>
        <ElTableColumn label="市编码" prop="cityCode"/>
        <ElTableColumn label="市名称" prop="cityName"/>
        <ElTableColumn label="天数" prop="transportDay">
          <template #default="sc">
            <el-input-number :min="0" :max="100" :step="1" v-model="sc.row.transportDay"/>
          </template>
        </ElTableColumn>
      </ElTable>
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
import {type ApsLogisticsPath, isDefaultList} from "./ApsLogisticsPathType.ts"
import {getById, pinyin4jSzm, postNoResult} from "@/common/utils/common-js.ts"
import {type FormInstance, FormRules} from "element-plus"
import {Factory, queryFactoryList} from "@v/base/Factory/FactoryType.ts";
import {queryDistrictByParentCode} from "@v/base/DistrictCode/DistrictCodeType.ts";
import {ApsLogisticsPathItem} from "@v/aps/ApsLogisticsPathItem/ApsLogisticsPathItemType.ts";

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
const dtoUrl = ref<string>("/apsLogisticsPath")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 编码
  logisticsPathCode: [
    {required: true, message: "请输入编码", trigger: "blur" },
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 名称
  logisticsPathName: [
    {required: true, message: "请输入名称", trigger: "blur" },
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 工厂ID
  factoryId: [
    {required: true, message: "请输入工厂ID", trigger: "blur" },
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
})

// 添加对象
const addForm = ref<ApsLogisticsPath>({
  logisticsPathCode: "",
  logisticsPathName: "",
  logisticsPathRemark: "",
  isDefault: 0,
  factoryId: "",
  id: "",
  apsLogisticsPathItemList: []
})

const factoryList = ref<Factory[]>([])
const defaultAllDayCount = ref<number>(1)

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

//
function loadSzm() {
  pinyin4jSzm(addForm.value.logisticsPathName).then(r => addForm.value.logisticsPathCode = r)
}

function defaultAllDayCountFun() {
  addForm.value.apsLogisticsPathItemList.forEach(t => t.transportDay = defaultAllDayCount.value)
}

// 页面加载事件
onMounted(() => {
  loadById()
  queryFactoryList().then(r => factoryList.value = r)
  queryDistrictByParentCode("0").then(r => {
    r.forEach(t => {
      const item: ApsLogisticsPathItem = {
        provinceCode: t.code,
        provinceName: t.name,
        cityCode: undefined,
        cityName: undefined,
        transportDay: 3,
        logisticsPathId: undefined,
        isDefault: 0,
        factoryId: undefined,
        id: undefined
      }
      addForm.value.apsLogisticsPathItemList.push(item)
    })
  })
})
</script>

<style scoped lang="scss">

</style>

