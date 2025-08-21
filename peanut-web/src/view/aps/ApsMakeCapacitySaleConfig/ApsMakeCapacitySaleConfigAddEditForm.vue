<template>
  <el-form label-width="80px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="销售配置" prop="saleConfigId">
      <el-select v-model="addForm.saleConfigId">
        <el-option-group v-for="f in saleConfigList" :value="f.id" :label="f.saleName" :key="f.id">
          <el-option v-for="option in f.children" :key="option.saleName" :label="option.saleName" :value="option.id">
          </el-option>
        </el-option-group>
      </el-select>
    </el-form-item>
    <el-form-item :key="dataConfigListKey">
      <el-row>
        <el-button icon="plus" type="primary" @click="addDataConfigList">
          添加
        </el-button>
      </el-row>
    </el-form-item>
    <el-form-item
      v-for="(item ,index) in dataConfigList" :key="index"
      :label="'生产日期:'+(index + 1)">
      <el-col :span="10">
        <el-date-picker
          @change="dataConfigListKeyFun"
          style="width: 260px"
          v-model="item.date" type="daterange" placeholder="选择日期"
          value-format="YYYY-MM-DD"
          start-placeholder="开始日期" end-placeholder="结束日期"
        />

      </el-col>
      <el-col :span="4">
        <el-input v-model="item.minValue" placeholder="最小产能"/>
      </el-col>
      <el-col :span="4">
        <el-input v-model="item.maxValue" placeholder="最大产能"/>
      </el-col>
      <el-col :span="6">
        <el-button icon="delete" type="danger" @click="deleteDataConfig(index)">
          删除
        </el-button>
      </el-col>
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
import { onMounted, ref } from "vue"
import { type ApsMakeCapacitySaleConfig } from "./ApsMakeCapacitySaleConfigType.ts"
import { getById, postNoResult } from "@/common/utils/common-js.ts"
import { type FormInstance, FormRules } from "element-plus"
import { ApsSaleConfig, querySaleConfigList } from "@v/aps/ApsSaleConfig/ApsSaleConfigType.ts";

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
const dtoUrl = ref<string>("/apsMakeCapacitySaleConfig")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({

})

interface DataConfig {
  date: string[] | null
  minValue: undefined
  maxValue: undefined
}

const dataConfigList = ref<DataConfig[]>([])
const dataConfigListKey = ref<string>("")
// 添加对象
const addForm = ref<ApsMakeCapacitySaleConfig>({
  factoryId: "",
  saleConfigId:"",
  makeCapacityQuantity: "",
  id: ""
})

const saleConfigList = ref<ApsSaleConfig[]>([])

function loadById() {
  if(!props.editId) {
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
  const mqList = []
  dataConfigList.value.forEach(t => {
    mqList.push({
      beginDate: t.date[0],
      endDate: t.date[1],
      minValue: t.minValue,
      maxValue: t.maxValue
    })
  })
  addForm.value.makeCapacityConfigList = mqList
  console.info("addForm ", addForm)
  addFormRef.value?.validate((valid) => {
    if(valid) {
      // 存在ID ，调用更新
      if(props.editId) {
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
  if(props.saveFun) {
    props.saveFun()
  }
}
function deleteDataConfig(index: number) {
  dataConfigList.value.splice(index, 1)
}
function addDataConfigList() {
  dataConfigList.value.push({
    date: undefined,
    minValue: undefined,
    maxValue: undefined
  })
}
function dataConfigListKeyFun() {
  dataConfigListKey.value = Math.random() + ""
  console.info("dataConfigListKey", dataConfigListKey.value)
}
// 页面加载事件
onMounted(() => {
  loadById()
  querySaleConfigList().then(r => {
    saleConfigList.value = r
    addForm.value.saleConfigId =r[0].children[0].id
  })
})
</script>

<style scoped lang="scss">

</style>

