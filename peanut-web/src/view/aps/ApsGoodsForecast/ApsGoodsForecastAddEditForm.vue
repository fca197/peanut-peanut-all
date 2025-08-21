<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsGoodsForecast} from "./ApsGoodsForecastType.ts"
import {getById, pinyin4jSzm, postNoResult} from "@/common/utils/common-js.ts"
import {type FormInstance, FormRules} from "element-plus"
import {ApsGoods, queryGoodsList} from "@v/aps/ApsGoods/ApsGoodsType.ts";
import dayjs from "dayjs";
import {ApsSaleConfig, querySaleGroupConfigList} from "@v/aps/ApsSaleConfig/ApsSaleConfigType.ts";

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
const dtoUrl = ref<string>("/apsGoodsForecast")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 商品ID
  goodsId: [
    {required: true, message: "请输入商品ID", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 预测编码
  forecastNo: [
    {required: true, message: "请输入预测编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 预测名称
  forecastName: [
    {required: true, message: "请输入预测名称", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 开始时间
  forecastBeginDate: [
    {required: true, message: "请输入开始时间", trigger: "blur"}
  ],
  // 结束时间
  forecastEndDate: [
    {required: true, message: "请输入结束时间", trigger: "blur"}
  ]

})

const goodsList = ref<ApsGoods []>([])
const saleGroupList = ref<ApsSaleConfig []>([])
// 添加对象
const addForm = ref<ApsGoodsForecast>({
  goodsId: "",
  forecastNo: "",
  forecastName: "",
  forecastBeginDate: dayjs().format("YYYY-MM"),
  forecastEndDate: "",
  month: "",
  months: "",
  forecastStatus: "",
  id: "",
  saleConfigList: []
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

function disabledDateNow(time: Date) {
  return time.getTime() < Date.now()
}

function disabledDateBegin(time: Date) {
  return time.getTime() < dayjs(`${addForm.value.forecastBeginDate}-01`).toDate().getTime()
}

function loadSzm() {
  pinyin4jSzm(addForm.value.forecastName).then((r) => addForm.value.forecastNo = r)
}

// 页面加载事件
onMounted(() => {
  loadById()
  queryGoodsList().then(t => {
    goodsList.value = t
    addForm.value.goodsId = t[0].id
  })
  querySaleGroupConfigList().then((r) => {
    saleGroupList.value = r
  })
})
</script>

<template>
  <el-form label-width="100px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="商品" prop="goodsId">
      <el-select v-model="addForm.goodsId">
        <el-option v-for="g in goodsList" :value="g.id" :label="g.goodsName" :key="g.id"/>
      </el-select>
    </el-form-item>
    <el-form-item label="预测名称" prop="forecastName">
      <el-input
        v-model="addForm.forecastName" clearable placeholder="请输入预测名称"
        @change="loadSzm"/>
    </el-form-item>
    <el-form-item label="预测编码" prop="forecastNo">
      <el-input v-model="addForm.forecastNo" clearable placeholder="请输入预测编码"/>
    </el-form-item>
    <el-form-item label="开始时间" prop="forecastBeginDate">
      <el-date-picker
        type="month" value-format="YYYY-MM" format="YYYY-MM" v-model="addForm.forecastBeginDate"
        clearable style="width: 100%"
        placeholder="请输入开始时间" :disabled-date="disabledDateNow"/>
    </el-form-item>
    <el-form-item label="结束时间" prop="forecastEndDate">
      <el-date-picker
        type="month" value-format="YYYY-MM" format="YYYY-MM" v-model="addForm.forecastEndDate"
        clearable style="width: 100%"
        placeholder="请输入结束时间" :disabled-date="disabledDateBegin"/>
    </el-form-item>
    <el-form-item label="销售配置组">
      <el-select
        style="width: 100%" multiple
        v-model="addForm.saleConfigList">
        <el-option
          v-for="s in saleGroupList"
          :value="s.id" :label="s.saleName" :key="s.id"
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


<style scoped lang="scss">

</style>

