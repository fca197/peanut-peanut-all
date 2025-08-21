<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsProduceProcess, isDefaultKVList} from "./ApsProduceProcessType.ts"
import {getById, pinyin4jSzmV4, postNoResult} from "@/common/utils/common-js.ts"
import {type FormInstance, FormRules} from "element-plus"
import {Factory, queryFactoryList} from "@v/base/Factory/FactoryType.ts";
import {ApsMachineWorkstation, queryApsMachineWorkstationList} from "@v/aps/ApsMachineWorkstation/ApsMachineWorkstationType.ts";
import {ApsStatus, queryApsStatusList} from "@v/aps/ApsStatus/ApsStatusType.ts";

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
const dtoUrl = ref<string>("/apsProduceProcess")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  // 生产路径编码
  produceProcessNo: [
    {required: true, message: "请输入生产路径编码", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 生产路径名称
  produceProcessName: [
    {required: true, message: "请输入生产路径名称", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 工厂ID
  factoryId: [
    {required: true, message: "请输入工厂ID", trigger: "blur"},
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],

})
const factoryList = ref<Factory[]>([])
// 添加对象
const addForm = ref<ApsProduceProcess>({
  produceProcessNo: "",
  produceProcessName: "",
  factoryId: "",
  isDefault: false,
  id: "",
  produceProcessItemDtoList: []
})

const apsMachineWorkstationList = ref<ApsMachineWorkstation[]>([])
watch(() => addForm.value.factoryId, () => {
  queryApsMachineWorkstationList(addForm.value.factoryId).then(r => {
    r.forEach(t => {
      // t.machineName =
    })
    apsMachineWorkstationList.value = r
    console.info("apsMachineWorkstationList.value =", r);
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

const apsStatusList = ref<ApsStatus[]>([])
// 页面加载事件
onMounted(() => {
  loadById()
  queryFactoryList().then((t) => factoryList.value = t)
  queryApsStatusList().then((t) => apsStatusList.value = t)
})

const produceProcessNameBlur = () => {
  pinyin4jSzmV4(addForm.value.produceProcessName, addForm, "produceProcessNo")
}

const addApsWorkstation = (index: number) => {
  var valueElement = apsMachineWorkstationList.value[index];
  const t = valueElement.machineWorkstationItemDtoList;
  t?.forEach(tt => {
    tt.useTime = parseInt(tt.useTime);
    // tt.minPower = parseInt(tt.minPower);
    tt.maxPower = parseInt(tt.maxPower);
    tt.machineWorkstationName = valueElement.machineWorkstationName
    addForm.value.produceProcessItemDtoList.push({...tt})
  })
}
const deleteApsProduceProcessItem = (index: number) => {
  addForm.value.produceProcessItemDtoList.splice(index, 1)
}
const updateStatusName = (index: number, value: string) => {
  const name = apsStatusList.value.filter(t => t.id === value)[0].statusName;
  addForm.value.produceProcessItemDtoList[index].goodsStatusName = name;
}
</script>

<template>
  <el-form label-width="100px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="工厂" prop="factoryId">
      <el-select v-model="addForm.factoryId" clearable style="width:100%">
        <el-option v-for="f in factoryList" :label="f.factoryName" :value="f.id" :key="f.id"/>
      </el-select>
    </el-form-item>

    <el-form-item label="路径名称" prop="produceProcessName">
      <el-input
        v-model="addForm.produceProcessName" clearable placeholder="请输入生产路径名称"
        @blur="produceProcessNameBlur"/>
    </el-form-item>
    <el-form-item label="路径编码" prop="produceProcessNo">
      <el-input v-model="addForm.produceProcessNo" clearable placeholder="请输入生产路径编码"/>
    </el-form-item>

    <el-form-item label="机器工作站">
      <el-select clearable filterable @change="addApsWorkstation" style="margin-bottom:  10px">
        <el-option
          v-for="(w,i) in apsMachineWorkstationList"
          :label="w.machineWorkstationName"
          :key="w.id"
          :value="i"
        />
      </el-select>
      <div
        style="width: 1000px; overflow-x: scroll">
        <el-table style="width: 100%"
                  :data="addForm.produceProcessItemDtoList">
          <el-table-column label="机器名称" prop="machineWorkstationName" width="200"/>
          <el-table-column label="机器名称" prop="machineName" width="200"/>
          <el-table-column label="最大功率" prop="maxPower" width="200">
            <template #default="scope">
              <el-input-number
                v-model="scope.row.maxPower"
              />
            </template>
          </el-table-column>

          <el-table-column label="耗时" prop="useTime" width="200">
            <template #default="scope">
              <el-input-number
                v-model="scope.row.useTime"
              />
            </template>
          </el-table-column>
          <el-table-column label="耗时" prop="staute" width="200">
            <template #default="scope">
              <el-select
                v-model="scope.row.goodsStatusId" @change="value=>{updateStatusName(scope.$index,value)}"
              >
                <el-option
                  v-for="s in apsStatusList"
                  :label="s.statusName"
                  :key="s.id"
                  :value="s.id"
                />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200" style="float: right">
            <template #default="s">
              <el-button type="danger" @click="deleteApsProduceProcessItem(s.$index)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-form-item>

    <el-form-item label="是否默认" prop="isDefault">
      <el-select v-model="addForm.isDefault" clearable style="width:100%">
        <el-option
          v-for="d in isDefaultKVList" :label="d.label" :value="d.value" :key="d.label"
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

