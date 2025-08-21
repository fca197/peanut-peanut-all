<script setup lang="ts">
import { getById, pinyin4jSzm, postNoResult } from "@/common/utils/common-js.ts"
import { supplyModeList } from "@v/aps/ApsBom/ApsBomType.ts"
import { FormInstance, FormRules } from "element-plus"
import { onMounted, ref } from "vue"

const props = defineProps({
  saveFun: {
    type: Function
  },
  editId: {
    type: String,
    required: false
  }
})

const dtoUrl = ref<string>("/apsBom")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  bomCode: [
    { required: true, message: "请输入bom 编码", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  bomName: [
    { required: true, message: "请输入bom 名称", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  bomCostPrice: [
    { required: true, message: "请输入成本价", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  bomCostPriceUnit: [
    { required: true, message: "请输入单位", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  bomInventory: [
    { required: true, message: "请输入库存", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  groupId: [
    { required: true, message: "请输入组ID", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  supplyMode: [
    { required: true, message: "请输入供给方式 make, buy", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  useUnit: [
    { required: true, message: "请输入使用单位", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  bomUnit: [
    { required: true, message: "请输入零件单位", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  produceProcessId: [
    { required: true, message: "请输入制造路径", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  deliveryCycleDay: [
    { required: true, message: "请输入到货周期", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ],
  apsBomSupplierId: [
    { required: true, message: "请输入供应商ID", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" }
  ]

})

onMounted(() => {
  loadById()
})

const addForm = ref({
  bomCode: String,
  bomName: String,
  bomCostPrice: undefined,
  bomCostPriceUnit: undefined,
  bomInventory: undefined,
  groupId: undefined,
  supplyMode: undefined,
  useUnit: undefined,
  bomUnit: undefined,
  produceProcessId: undefined,
  deliveryCycleDay: undefined,
  apsBomSupplierId: undefined,
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

function loadSzm() {
  pinyin4jSzm(addForm.value.bomName).then(r => addForm.value.bomCode = r)
}
</script>

<template>
  <el-form label-width="130px" :model="addForm" ref="addFormRef" :rules="checkRules">

    <el-form-item label="零件名称" prop="bomName">
      <el-input v-model="addForm.bomName" placeholder="请输入名称" clearable @blur="loadSzm" />
    </el-form-item>
    <el-form-item label="零件编号" prop="bomCode">
      <el-input v-model="addForm.bomCode" placeholder="请输入编号" clearable />
    </el-form-item>
    <el-form-item label="零件价格" prop="bomCostPrice">
      <el-input v-model="addForm.bomCostPrice" placeholder="请输入价格" />
    </el-form-item>
    <el-form-item label="价格规格" prop="bomCostPriceUnit">
      <el-input v-model="addForm.bomCostPriceUnit" placeholder="请输入价格规格如:  45元/瓶" />
    </el-form-item>

    <el-form-item label="库存" prop="bomInventory">
      <el-input v-model="addForm.bomInventory" placeholder="请输入库存如： 123.909" />
    </el-form-item>
    <el-form-item label="购买方式" prop="supplyMode">
      <el-select v-model="addForm.supplyMode">
        <el-option v-for="(sm) in supplyModeList" :value="sm.value" :key="sm.value" :label="sm.label" />
      </el-select>
    </el-form-item>
    <el-form-item label="规格" prop="bomUnit">
      <el-input v-model="addForm.bomUnit" placeholder="请输入规格如： 550ML" />
    </el-form-item>
    <el-form-item label="使用规格" prop="useUnit">
      <el-input v-model="addForm.useUnit" placeholder="请输入使用规格如：10ML " />
    </el-form-item>
    <el-form-item label="供货周期(天)" prop="deliveryCycleDay">
      <el-input v-model.number="addForm.deliveryCycleDay" placeholder="请输入供货周期 如： 1" />
    </el-form-item>
    <el-form-item label="制造路径" v-show="addForm.supplyMode === 'make'" prop="apsBomSupplierId">
      <!--      <el-select v-model="addForm.produceProcessId">-->
      <!--        <el-option v-for="(p,i) in produceProcessList" :key="p.id" :value="p.id"-->
      <!--                   :label="p.produceProcessName"></el-option>-->
      <!--      </el-select>-->
    </el-form-item>
    <el-form-item label="供应商" v-show="addForm.supplyMode === 'buy'" prop="apsBomSupplierId">
      <!--      <el-select v-model="addForm.apsBomSupplierId">-->
      <!--        <el-option v-for="(p,i) in apsBomSupplierList" :key="p.id" :value="p.id" :label="p.bomSupplierName"></el-option>-->
      <!--      </el-select>-->
    </el-form-item>
    <el-form-item label="零件组" prop="groupId">
      <!--      <tree-select ref="treeSelect" :list="groupData.filter(t=>t.id!=='0')" :multiple="false" :clearable="true"-->
      <!--                   :checkStrictly="true" width="120px" v-model="addForm.groupId"-->
      <!--      ></tree-select>-->
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
