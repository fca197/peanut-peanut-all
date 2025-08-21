<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsOrderGoodsBomKittingTemplate} from "./ApsOrderGoodsBomKittingTemplateType.ts"
import {getById, KVEntity, pinyin4jSzm, postNoResult} from "@/common/utils/common-js.ts"
import {type FormInstance, FormRules} from "element-plus"
import {querySaleConfigList} from "@v/aps/ApsSaleConfig/ApsSaleConfigType.ts";
import {queryOrderFieldList} from "@v/aps/ApsOrder/ApsOrderType.ts";
import {queryOrderUserFieldList} from "@v/aps/ApsOrderUser/ApsOrderUserType.ts";
import {Factory, queryFactoryList} from "@v/base/Factory/FactoryType.ts";

const props = defineProps({
  saveFun: {
    type: Function
  },
  editId: {
    type: String,
    required: false
  }
})

const orderFieldList = ref<KVEntity[]>([])
const orderUserFieldList = ref<KVEntity[]>([])
const apsSaleConfigList = ref<KVEntity[]>([])
const factoryList = ref<Factory[]>([])

// 对象URL
const dtoUrl = ref<string>("/apsOrderGoodsBomKittingTemplate")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 模板编号
  kittingTemplateNo: [
    {required: true, message: "请输入模板编号", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 模板名称
  kittingTemplateName: [
    {required: true, message: "请输入模板名称", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // // 工厂ID
  // factoryId: [
  //   {required: true, message: "请输入工厂ID", trigger: "blur"},
  //   {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  // ],

})


// 添加对象
const addForm = ref<ApsOrderGoodsBomKittingTemplate>({
  kittingTemplateNo: "",
  kittingTemplateName: "",
  kittingTemplateUserConfigList: "",
  kittingTemplateSaleConfigList: "",
  kittingTemplateOrderConfigList: "",
  kittingTemplateOrderUserConfigList: "",
  factoryId: "",
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

watch(()=>addForm.value.kittingTemplateName,(data)=>{
  pinyin4jSzm(addForm.value.kittingTemplateName).then(r=> addForm.value.kittingTemplateNo = r)
})
// 页面加载事件
onMounted(() => {
  loadById()
  queryOrderFieldList().then(r => orderFieldList.value = r)
  queryOrderUserFieldList().then(r => orderUserFieldList.value = r)
  querySaleConfigList().then(r => apsSaleConfigList.value = r.filter(t => t.isValue === 0).map(t => {
    return {
      label: t.saleName,
      value: t.id
    }
  }))
  queryFactoryList().then(r => factoryList.value = r)
})
</script>

<template>
  <el-form label-width="120px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="模板名称" prop="kittingTemplateName">
      <el-input v-model="addForm.kittingTemplateName" clearable placeholder="请输入模板名称"/>
    </el-form-item>
    <el-form-item label="模板编号" prop="kittingTemplateNo">
      <el-input v-model="addForm.kittingTemplateNo" clearable placeholder="请输入模板编号"/>
    </el-form-item>
<!--    <el-form-item label="工厂" prop="factoryId">-->
<!--      <el-select v-model="addForm.factoryId" clearable placeholder="请输入工厂">-->
<!--        <el-option v-for="f in factoryList" :key="f.id" :value="f.id" :label="f.factoryName"/>-->
<!--      </el-select>-->
<!--    </el-form-item>-->
    <el-form-item label="订单配置" prop="kittingTemplateOrderConfigList">
      <el-select
        v-model="addForm.kittingTemplateOrderConfigList" clearable multiple
        placeholder="请输入订单配置">
        <el-option v-for="k in orderFieldList" :value="k" :key="k.value" :label="k.label"/>
      </el-select>
    </el-form-item>
    <el-form-item label="订单用户配置" prop="kittingTemplateOrderUserConfigList">
      <el-select
        v-model="addForm.kittingTemplateOrderUserConfigList" clearable multiple
        placeholder="请输入订单配置">
        <el-option v-for="k in orderUserFieldList" :value="k" :key="k.value" :label="k.label"/>
      </el-select>
    </el-form-item>
    <el-form-item label="销售配置" prop="kittingTemplateSaleConfigList">
      <el-select
        v-model="addForm.kittingTemplateSaleConfigList" clearable multiple
        placeholder="请输入销售配置">
        <el-option v-for="s in apsSaleConfigList" :value="s" :label="s.label" :key="s.label"/>
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
