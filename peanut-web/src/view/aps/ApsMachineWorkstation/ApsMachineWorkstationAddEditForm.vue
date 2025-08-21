<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsMachineWorkstation} from "./ApsMachineWorkstationType.ts"
import {getById, pinyin4jSzm, postNoResult} from "@/common/utils/common-js.ts"
import {type FormInstance, FormRules} from "element-plus"
import {Factory, queryFactoryList} from "@v/base/Factory/FactoryType.ts"
import {ApsMachine, queryApsMachineList} from "@v/aps/ApsMachine/ApsMachineType.ts"
import {ApsStatus, queryApsStatusList} from "@v/aps/ApsStatus/ApsStatusType.ts"

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
const dtoUrl = ref<string>("/apsMachineWorkstation")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 工作站编号
  machineWorkstationNo: [
    {required: true, message: "请输入工作站编号", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 工作站名称
  machineWorkstationName: [
    {required: true, message: "请输入工作站名称", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],

  // 工厂ID
  factoryId: [
    {required: true, message: "请输入工厂ID", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 排序索引
  sortIndex: [
    {required: true, message: "请输入排序索引", trigger: "blur"}
  ],

  // 耗时
  useTime: [
    {required: true, message: "请输入排序索引", trigger: "blur"}
  ],

})

const loadEntity = ref<boolean>(true)
// 添加对象
const addForm = ref<ApsMachineWorkstation>({
  machineWorkstationNo: "",
  machineWorkstationName: "",
  // minPower: "",
  maxPower: "",
  factoryId: null,
  sortIndex: 0,
  id: "",
  machineWorkstationItemDtoList: []
})

const apsMachineList = ref<ApsMachine[]>([])
const loadingMachineList = ref<boolean>(false)
watch(() => addForm.value.factoryId, (n) => {
  if (n) {
    loadingMachineList.value = true
    // addForm.value.machineWorkstationItemDtoList = []
    queryApsMachineList(addForm.value.factoryId).then((res) => {
      apsMachineList.value = res
      apsMachineList.value.forEach(t => {
        t.machineId = t.id
        t.id = undefined
        t.sortIndex = Number.parseInt(t.sortIndex)
      })
      loadingMachineList.value = false
      loadEntity.value = false
    })
  } else {
    apsMachineList.value = []
  }
})

const loadById = () => {
  if (!props.editId) {
    loadEntity.value = false
    return
  }
  console.info("props.editId ", props.editId)
  getById(`${dtoUrl.value}/queryByIdList`, props.editId).then((t) => {
    t.sortIndex = Number.parseInt(t.sortIndex)
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
const factoryList = ref<Factory[]>([])

const apsStatusList = ref<ApsStatus[]>([])
// 页面加载事件
onMounted(() => {
  loadById()
  queryApsStatusList().then(t => apsStatusList.value = t)
  queryFactoryList().then(t => factoryList.value = t)
})
const machineWorkstationNameBlur = () => {
  pinyin4jSzm(addForm.value.machineWorkstationName).then((res) => addForm.value.machineWorkstationNo = res)
}

// 交换数组中两个元素的位置
const swapItems = (indexA, indexB) => {
  const array = addForm.value.machineWorkstationItemDtoList;
  if (indexA >= 0 && indexA < array.length && indexB >= 0 && indexB < array.length) {
    [array[indexA], array[indexB]] = [array[indexB], array[indexA]];
  }
}

const addMachine = (dataIndex: number) => {
  const data = apsMachineList.value[dataIndex]
  addForm.value?.machineWorkstationItemDtoList.push({...data})

  sumMaxPower()
}
const deleteMachine = (index: number) => {
  addForm.value.machineWorkstationItemDtoList.splice(index, 1)

  sumMaxPower()
}

const sumUseTime = () => {
  addForm.value.useTime = addForm.value?.machineWorkstationItemDtoList
  .map(t => t.useTime)
  .filter(t => t !== undefined && t !== null)
  .map(t => Number.parseInt(t))
  .reduce((acc, curr) => acc + curr, 0)
}

watch(() => addForm.value?.machineWorkstationItemDtoList, () => {
  sumUseTime();
}, {
  deep: true
})

const sumMaxPower = () => {
  addForm.value.maxPower = addForm.value?.machineWorkstationItemDtoList
  .map(t => t.maxPower)
  .filter(t => t !== undefined && t !== null)
  .map(t => Number.parseInt(t))
  .reduce((acc, curr) => acc + curr, 0)
}
</script>

<template>
  <el-form
    v-loading="loadEntity" label-width="100px" :model="addForm" ref="addFormRef"
    :rules="checkRules">
    <el-form-item label="工厂" prop="factoryId">
      <el-select v-model="addForm.factoryId" clearable placeholder="请选择工厂" style="width: 100%"
        :disabled="addForm.factoryId !== null "
      >
        <el-option
          v-for=" f in factoryList" :label="f.factoryName" :key="f.id" :value="f.id"
        />
      </el-select>
    </el-form-item>
    <el-form-item label="工作站名称" prop="machineWorkstationName">
      <el-input
        v-model="addForm.machineWorkstationName" @blur="machineWorkstationNameBlur"
        clearable placeholder="请输入工作站名称"/>
    </el-form-item>
    <el-form-item label="工作站编号" prop="machineWorkstationNo">
      <el-input v-model="addForm.machineWorkstationNo" clearable placeholder="请输入工作站编号"/>
    </el-form-item>
    <el-form-item label="机器">
      <el-select @change="addMachine" style="margin-bottom: 10px" filterable>
        <el-option
          :value="i" v-for="(m,i) in apsMachineList" :key="m.machineId" :label="m.machineName"
        />
      </el-select>
      <el-table
        :data="addForm.machineWorkstationItemDtoList">
        <el-table-column prop="machineName" label="机器名称"/>
        <!--        <el-table-column prop="minPower" label="最小功率"/>-->
        <el-table-column prop="maxPower" label="最大功率"/>
        <el-table-column prop="useTime" label="耗时">
          <template #default="scope">
            <el-input v-model="scope.row.useTime"/>
          </template>
        </el-table-column>
        <ElTableColumn prop="goodsStatusId" label="商品状态" width="170">
          <template #default="scope">
            <el-select v-model="scope.row.goodsStatusId" clearable style="width: 150px">
              <el-option v-for="s in apsStatusList" :value="s.id" :label="s.statusName"
                         :key="s.id"/>
            </el-select>
          </template>
        </ElTableColumn>
        <ElTableColumn fixed="right" label="操作" width="250" align="center">
          <template #default="scope">
            <el-button type="primary" icon="ArrowUp" v-if="scope.$index !==0"
                       @click="swapItems(scope.$index,scope.$index-1)">
              上移
            </el-button>
            <el-button type="danger" @click="deleteMachine(scope.$index)">
              删除
            </el-button>
          </template>
        </ElTableColumn>
      </el-table>
    </el-form-item>
    <el-form-item label="最大功率" prop="maxPower">
      <el-input v-model="addForm.maxPower" readonly clearable placeholder="请输入最大功率">
<!--        <template #append>-->
<!--          <el-button icon="Refresh" @click="sumMaxPower"/>-->
<!--        </template>-->
      </el-input>
    </el-form-item>
    <el-form-item label="耗时" prop="useTime">
      <el-input v-model="addForm.useTime" readonly clearable placeholder="请输入耗时">
<!--        <template #append>-->
<!--          <el-button icon="Refresh" @click="sumUseTime"/>-->
<!--        </template>-->
      </el-input>
    </el-form-item>
    <el-form-item label="排序索引" prop="sortIndex">
      <el-input-number
        v-model="addForm.sortIndex" style="width: 100%" clearable
        placeholder="请输入排序索引"/>
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
