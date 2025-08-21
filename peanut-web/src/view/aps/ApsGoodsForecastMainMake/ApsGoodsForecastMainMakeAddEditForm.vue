<script setup lang="ts">
import { onMounted, ref } from "vue"
import { type ApsGoodsForecastMainMake } from "./ApsGoodsForecastMainMakeType.ts"
import { getById, pinyin4jSzm, postNoResult, postResultInfoList } from "@/common/utils/common-js.ts"
import { type FormInstance, FormRules } from "element-plus"
import { ApsGoodsForecastMain } from "@v/aps/ApsGoodsForecastMain/ApsGoodsForecastMainType.ts";

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
const dtoUrl = ref<string>("/apsGoodsForecastMake")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 商品ID
  forecastMainId: [
    { required: true, message: "请选择版本", trigger: "blur" }
  ],
  // 编码
  forecastMakeMonthNo: [
    { required: true, message: "请输入编码", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  // 名称
  forecastMakeMonthName: [
    { required: true, message: "请输入名称", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  // 开始时间
  forecastMakeMonthBeginDate: [
    { required: true, message: "请输入开始时间", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  // 结束时间
  forecastMakeMonthEndDate: [
    { required: true, message: "请输入结束时间", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ]
})

// 添加对象
const addForm = ref<ApsGoodsForecastMainMake>({
  goodsId: undefined,
  forecastMakeMonthName: undefined,
  forecastMakeMonthNo: undefined,
  forecastMakeMonthBeginDate: undefined,
  forecastMakeMonthEndDate: undefined,
  factoryId: undefined,
  id: undefined
})

const forecastMainList = ref<ApsGoodsForecastMain[]>([])

const loadById = () => {
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
const saveForm = () => {
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
const saveFormAfter = () => {
  cancelForm()
}

// 取消方法
const cancelForm = () => {
  if(props.saveFun) {
    props.saveFun()
  }
}
const loadSzm = () => {
  pinyin4jSzm(addForm.value.forecastMakeMonthName).then(r => addForm.value.forecastMakeMonthNo = r)
}
// 页面加载事件
onMounted(() => {
  loadById()

  postResultInfoList("/apsGoodsForecastMain/queryList", { queryPage: false }).then((r) => {
    forecastMainList.value = r
  })
})
</script>

<template>
  <el-form label-width="150px" :model="addForm" ref="addFormRef" :rules="checkRules">

    <el-form-item label="预测主版本" prop="goodsId">
      <el-select v-model="addForm.forecastMainId" placeholder="请选择预测主版本">
        <el-option v-for="item in forecastMainList" :key="item.id" :label="item.forecastName"
                   :value="item.id"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="(预)周生产版本名称" prop="forecastMakeMonthName">
      <el-input v-model="addForm.forecastMakeMonthName" clearable placeholder="请输入(预)周生产名称" @blur="loadSzm"/>
    </el-form-item>
    <el-form-item label="(预)周生产版本编码" prop="forecastMakeMonthNo">
      <el-input v-model="addForm.forecastMakeMonthNo" clearable placeholder="请输入(预)周生产编码"/>
    </el-form-item>
    <el-form-item label="(预)周生产开始时间" prop="forecastMakeMonthBeginDate">
      <el-date-picker v-model="addForm.forecastMakeMonthBeginDate" clearable format="YYYY-MM"
                      placeholder="请选择(预)周生产开始时间" type="month" value-format="YYYY-MM"/>
    </el-form-item>
    <el-form-item label="(预)周生产结束时间" prop="forecastMakeMonthEndDate">
      <el-date-picker v-model="addForm.forecastMakeMonthEndDate" clearable format="YYYY-MM"
                      placeholder="请选择(预)周生产结束时间" type="month" value-format="YYYY-MM"/>
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

