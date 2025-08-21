<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsOrderGoodsSaleConfigHistory} from "./ApsOrderGoodsSaleConfigHistoryType.ts"
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
const dtoUrl = ref<string>("/apsOrderGoodsSaleConfigHistory")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
    // 工厂ID
    factoryId: [
      {required: true, message: "请输入工厂ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 商品ID
    goodsId: [
      {required: true, message: "请输入商品ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 商品名称
    goodsName: [
      {required: true, message: "请输入商品名称", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 销售上级ID
    saleParentId: [
      {required: true, message: "请输入销售上级ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 销售组名称
    saleParentConfigName: [
      {required: true, message: "请输入销售组名称", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 销售ID
    saleConfigId: [
      {required: true, message: "请输入销售ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 销售组名称
    saleConfigName: [
      {required: true, message: "请输入销售组名称", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 年份
    year: [
      {required: true, message: "请输入年份", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 1月销售数量
    monthCount01: [
      {required: true, message: "请输入1月销售数量", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 1月销售占比
    monthRatio01: [
      {required: true, message: "请输入1月销售占比", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 2月销售数量
    monthCount02: [
      {required: true, message: "请输入2月销售数量", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 2月销售占比
    monthRatio02: [
      {required: true, message: "请输入2月销售占比", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 3月销售数量
    monthCount03: [
      {required: true, message: "请输入3月销售数量", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 3月销售占比
    monthRatio03: [
      {required: true, message: "请输入3月销售占比", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 4月销售数量
    monthCount04: [
      {required: true, message: "请输入4月销售数量", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 4月销售占比
    monthRatio04: [
      {required: true, message: "请输入4月销售占比", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 5月销售数量
    monthCount05: [
      {required: true, message: "请输入5月销售数量", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 5月销售占比
    monthRatio05: [
      {required: true, message: "请输入5月销售占比", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 6月销售数量
    monthCount06: [
      {required: true, message: "请输入6月销售数量", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 6月销售占比
    monthRatio06: [
      {required: true, message: "请输入6月销售占比", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 7月销售数量
    monthCount07: [
      {required: true, message: "请输入7月销售数量", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 7月销售占比
    monthRatio07: [
      {required: true, message: "请输入7月销售占比", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 8月销售数量
    monthCount08: [
      {required: true, message: "请输入8月销售数量", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 8月销售占比
    monthRatio08: [
      {required: true, message: "请输入8月销售占比", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 9月销售数量
    monthCount09: [
      {required: true, message: "请输入9月销售数量", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 9月销售占比
    monthRatio09: [
      {required: true, message: "请输入9月销售占比", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 10月销售数量
    monthCount10: [
      {required: true, message: "请输入10月销售数量", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 10月销售占比
    monthRatio10: [
      {required: true, message: "请输入10月销售占比", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 11月销售数量
    monthCount11: [
      {required: true, message: "请输入11月销售数量", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 11月销售占比
    monthRatio11: [
      {required: true, message: "请输入11月销售占比", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 12月销售数量
    monthCount12: [
      {required: true, message: "请输入12月销售数量", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 12月销售占比
    monthRatio12: [
      {required: true, message: "请输入12月销售占比", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],

})


// 添加对象
const addForm = ref<ApsOrderGoodsSaleConfigHistory>({
      factoryId: "",
      goodsId: "",
      goodsName: "",
      saleParentId: "",
      saleParentConfigName: "",
      saleConfigId: "",
      saleConfigName: "",
      year: "",
      monthCount01: "",
      monthRatio01: "",
      monthCount02: "",
      monthRatio02: "",
      monthCount03: "",
      monthRatio03: "",
      monthCount04: "",
      monthRatio04: "",
      monthCount05: "",
      monthRatio05: "",
      monthCount06: "",
      monthRatio06: "",
      monthCount07: "",
      monthRatio07: "",
      monthCount08: "",
      monthRatio08: "",
      monthCount09: "",
      monthRatio09: "",
      monthCount10: "",
      monthRatio10: "",
      monthCount11: "",
      monthRatio11: "",
      monthCount12: "",
      monthRatio12: "",
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
    <el-form-item label="工厂" prop="factoryId">
      <el-input v-model="addForm.factoryId" clearable placeholder="请输入工厂ID"/>
    </el-form-item>
    <el-form-item label="商品" prop="goodsId">
      <el-input v-model="addForm.goodsId" clearable placeholder="请输入商品ID"/>
    </el-form-item>
    <el-form-item label="商品名称" prop="goodsName">
      <el-input v-model="addForm.goodsName" clearable placeholder="请输入商品名称"/>
    </el-form-item>
    <el-form-item label="销售上级ID" prop="saleParentId">
      <el-input v-model="addForm.saleParentId" clearable placeholder="请输入销售上级ID"/>
    </el-form-item>
    <el-form-item label="销售组名称" prop="saleParentConfigName">
      <el-input v-model="addForm.saleParentConfigName" clearable placeholder="请输入销售组名称"/>
    </el-form-item>
    <el-form-item label="销售ID" prop="saleConfigId">
      <el-input v-model="addForm.saleConfigId" clearable placeholder="请输入销售ID"/>
    </el-form-item>
    <el-form-item label="销售组名称" prop="saleConfigName">
      <el-input v-model="addForm.saleConfigName" clearable placeholder="请输入销售组名称"/>
    </el-form-item>
    <el-form-item label="年份" prop="year">
      <el-input v-model="addForm.year" clearable placeholder="请输入年份"/>
    </el-form-item>
    <el-form-item label="1月销售数量" prop="monthCount01">
      <el-input v-model="addForm.monthCount01" clearable placeholder="请输入1月销售数量"/>
    </el-form-item>
    <el-form-item label="1月销售占比" prop="monthRatio01">
      <el-input v-model="addForm.monthRatio01" clearable placeholder="请输入1月销售占比"/>
    </el-form-item>
    <el-form-item label="2月销售数量" prop="monthCount02">
      <el-input v-model="addForm.monthCount02" clearable placeholder="请输入2月销售数量"/>
    </el-form-item>
    <el-form-item label="2月销售占比" prop="monthRatio02">
      <el-input v-model="addForm.monthRatio02" clearable placeholder="请输入2月销售占比"/>
    </el-form-item>
    <el-form-item label="3月销售数量" prop="monthCount03">
      <el-input v-model="addForm.monthCount03" clearable placeholder="请输入3月销售数量"/>
    </el-form-item>
    <el-form-item label="3月销售占比" prop="monthRatio03">
      <el-input v-model="addForm.monthRatio03" clearable placeholder="请输入3月销售占比"/>
    </el-form-item>
    <el-form-item label="4月销售数量" prop="monthCount04">
      <el-input v-model="addForm.monthCount04" clearable placeholder="请输入4月销售数量"/>
    </el-form-item>
    <el-form-item label="4月销售占比" prop="monthRatio04">
      <el-input v-model="addForm.monthRatio04" clearable placeholder="请输入4月销售占比"/>
    </el-form-item>
    <el-form-item label="5月销售数量" prop="monthCount05">
      <el-input v-model="addForm.monthCount05" clearable placeholder="请输入5月销售数量"/>
    </el-form-item>
    <el-form-item label="5月销售占比" prop="monthRatio05">
      <el-input v-model="addForm.monthRatio05" clearable placeholder="请输入5月销售占比"/>
    </el-form-item>
    <el-form-item label="6月销售数量" prop="monthCount06">
      <el-input v-model="addForm.monthCount06" clearable placeholder="请输入6月销售数量"/>
    </el-form-item>
    <el-form-item label="6月销售占比" prop="monthRatio06">
      <el-input v-model="addForm.monthRatio06" clearable placeholder="请输入6月销售占比"/>
    </el-form-item>
    <el-form-item label="7月销售数量" prop="monthCount07">
      <el-input v-model="addForm.monthCount07" clearable placeholder="请输入7月销售数量"/>
    </el-form-item>
    <el-form-item label="7月销售占比" prop="monthRatio07">
      <el-input v-model="addForm.monthRatio07" clearable placeholder="请输入7月销售占比"/>
    </el-form-item>
    <el-form-item label="8月销售数量" prop="monthCount08">
      <el-input v-model="addForm.monthCount08" clearable placeholder="请输入8月销售数量"/>
    </el-form-item>
    <el-form-item label="8月销售占比" prop="monthRatio08">
      <el-input v-model="addForm.monthRatio08" clearable placeholder="请输入8月销售占比"/>
    </el-form-item>
    <el-form-item label="9月销售数量" prop="monthCount09">
      <el-input v-model="addForm.monthCount09" clearable placeholder="请输入9月销售数量"/>
    </el-form-item>
    <el-form-item label="9月销售占比" prop="monthRatio09">
      <el-input v-model="addForm.monthRatio09" clearable placeholder="请输入9月销售占比"/>
    </el-form-item>
    <el-form-item label="10月销售数量" prop="monthCount10">
      <el-input v-model="addForm.monthCount10" clearable placeholder="请输入10月销售数量"/>
    </el-form-item>
    <el-form-item label="10月销售占比" prop="monthRatio10">
      <el-input v-model="addForm.monthRatio10" clearable placeholder="请输入10月销售占比"/>
    </el-form-item>
    <el-form-item label="11月销售数量" prop="monthCount11">
      <el-input v-model="addForm.monthCount11" clearable placeholder="请输入11月销售数量"/>
    </el-form-item>
    <el-form-item label="11月销售占比" prop="monthRatio11">
      <el-input v-model="addForm.monthRatio11" clearable placeholder="请输入11月销售占比"/>
    </el-form-item>
    <el-form-item label="12月销售数量" prop="monthCount12">
      <el-input v-model="addForm.monthCount12" clearable placeholder="请输入12月销售数量"/>
    </el-form-item>
    <el-form-item label="12月销售占比" prop="monthRatio12">
      <el-input v-model="addForm.monthRatio12" clearable placeholder="请输入12月销售占比"/>
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

