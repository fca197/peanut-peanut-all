<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsOrderGoodsBom} from "./ApsOrderGoodsBomType.ts"
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
const dtoUrl = ref<string>("/apsOrderGoodsBom")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
    // 订单ID
    orderId: [
      {required: true, message: "请输入订单ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // aps_goods_bom ID 
    goodsBomId: [
      {required: true, message: "请输入aps_goods_bom ID ", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 商品ID
    goodsId: [
      {required: true, message: "请输入商品ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 零件组ID
    groupId: [
      {required: true, message: "请输入零件组ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 商品ID
    bomId: [
      {required: true, message: "请输入商品ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // bom 编码
    bomCode: [
      {required: true, message: "请输入bom 编码", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // bom 名称
    bomName: [
      {required: true, message: "请输入bom 名称", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用量
    bomUsage: [
      {required: true, message: "请输入使用量", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 单位
    bomUnit: [
      {required: true, message: "请输入单位", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 成本价
    bomCostPrice: [
      {required: true, message: "请输入成本价", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 单位
    bomCostPriceUnit: [
      {required: true, message: "请输入单位", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用工位
    bomUseWorkStation: [
      {required: true, message: "请输入使用工位", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用表达式
    bomUseExpression: [
      {required: true, message: "请输入使用表达式", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 库存
    bomInventory: [
      {required: true, message: "请输入库存", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 是否关注
    isFollow: [
      {required: true, message: "请输入是否关注", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 工厂ID
    factoryId: [
      {required: true, message: "请输入工厂ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 状态
    goodsStatusId: [
      {required: true, message: "请输入状态", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用时间
    bomUseDate: [
      {required: true, message: "请输入使用时间", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],

})


// 添加对象
const addForm = ref<ApsOrderGoodsBom>({
      orderId: "",
      goodsBomId: "",
      goodsId: "",
      groupId: "",
      bomId: "",
      bomCode: "",
      bomName: "",
      bomUsage: "",
      bomUnit: "",
      bomCostPrice: "",
      bomCostPriceUnit: "",
      bomUseWorkStation: "",
      bomUseExpression: "",
      bomInventory: "",
      isFollow: "",
      factoryId: "",
      goodsStatusId: "",
      bomUseDate: "",
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
    <el-form-item label="订单ID" prop="orderId">
      <el-input v-model="addForm.orderId" clearable placeholder="请输入订单ID"/>
    </el-form-item>
    <el-form-item label="aps_goods_bom ID " prop="goodsBomId">
      <el-input v-model="addForm.goodsBomId" clearable placeholder="请输入aps_goods_bom ID "/>
    </el-form-item>
    <el-form-item label="商品" prop="goodsId">
      <el-input v-model="addForm.goodsId" clearable placeholder="请输入商品ID"/>
    </el-form-item>
    <el-form-item label="零件组ID" prop="groupId">
      <el-input v-model="addForm.groupId" clearable placeholder="请输入零件组ID"/>
    </el-form-item>
    <el-form-item label="商品" prop="bomId">
      <el-input v-model="addForm.bomId" clearable placeholder="请输入商品ID"/>
    </el-form-item>
    <el-form-item label="bom 编码" prop="bomCode">
      <el-input v-model="addForm.bomCode" clearable placeholder="请输入bom 编码"/>
    </el-form-item>
    <el-form-item label="bom 名称" prop="bomName">
      <el-input v-model="addForm.bomName" clearable placeholder="请输入bom 名称"/>
    </el-form-item>
    <el-form-item label="使用量" prop="bomUsage">
      <el-input v-model="addForm.bomUsage" clearable placeholder="请输入使用量"/>
    </el-form-item>
    <el-form-item label="单位" prop="bomUnit">
      <el-input v-model="addForm.bomUnit" clearable placeholder="请输入单位"/>
    </el-form-item>
    <el-form-item label="成本价" prop="bomCostPrice">
      <el-input v-model="addForm.bomCostPrice" clearable placeholder="请输入成本价"/>
    </el-form-item>
    <el-form-item label="单位" prop="bomCostPriceUnit">
      <el-input v-model="addForm.bomCostPriceUnit" clearable placeholder="请输入单位"/>
    </el-form-item>
    <el-form-item label="使用工位" prop="bomUseWorkStation">
      <el-input v-model="addForm.bomUseWorkStation" clearable placeholder="请输入使用工位"/>
    </el-form-item>
    <el-form-item label="使用表达式" prop="bomUseExpression">
      <el-input v-model="addForm.bomUseExpression" clearable placeholder="请输入使用表达式"/>
    </el-form-item>
    <el-form-item label="库存" prop="bomInventory">
      <el-input v-model="addForm.bomInventory" clearable placeholder="请输入库存"/>
    </el-form-item>
    <el-form-item label="是否关注" prop="isFollow">
      <el-input v-model="addForm.isFollow" clearable placeholder="请输入是否关注"/>
    </el-form-item>
    <el-form-item label="工厂" prop="factoryId">
      <el-input v-model="addForm.factoryId" clearable placeholder="请输入工厂ID"/>
    </el-form-item>
    <el-form-item label="状态" prop="goodsStatusId">
      <el-input v-model="addForm.goodsStatusId" clearable placeholder="请输入状态"/>
    </el-form-item>
    <el-form-item label="使用时间" prop="bomUseDate">
      <el-input v-model="addForm.bomUseDate" clearable placeholder="请输入使用时间"/>
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

