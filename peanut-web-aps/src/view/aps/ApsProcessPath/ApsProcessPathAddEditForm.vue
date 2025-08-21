<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsProcessPath, isDefaultList} from "./ApsProcessPathType.ts"
import {getById, pinyin4jSzm, postNoResult} from "@/common/utils/common-js.ts"
import {type FormInstance, FormRules} from "element-plus"
import {Factory, queryFactoryList} from "@v/base/Factory/FactoryType.ts";
import {ApsRoom, queryApsRoomList} from "@v/aps/ApsRoom/ApsRoomType.ts";

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
const dtoUrl = ref<string>("/apsProcessPath")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
  //
  processPathCode: [
    {required: true, message: "请输入", trigger: "blur" },
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  //
  processPathName: [
    {required: true, message: "请输入", trigger: "blur" },
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],
  // 工厂ID
  factoryId: [
    {required: true, message: "请输入工厂ID", trigger: "blur" },
    {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
  ],

})

// 添加对象
const addForm = ref<ApsProcessPath>({
  processPathCode: "",
  processPathName: "",
  processPathRemark: "",
  isDefault: false,
  factoryId: "",
  id: "",
  pathRoomList: []
})

const factoryList = ref<Factory[]>([])
const roomList = ref<ApsRoom[]>([])

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

function loadSzm() {
  pinyin4jSzm(addForm.value.processPathName).then(r => addForm.value.processPathCode = r)
}

function addRoom() {
  addForm.value.pathRoomList.push({})
}

function deleteRoom(index) {
  addForm.value.pathRoomList.splice(index, 1)
}

// 页面加载事件
onMounted(() => {
  loadById()
  queryFactoryList().then(r => {
    factoryList.value = r
    addForm.value.factoryId = r[0].id
  })
  queryApsRoomList().then(r => roomList.value = r)
})
</script>

<template>
  <el-form label-width="100px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="工厂" prop="factoryId">
      <el-select v-model="addForm.factoryId">
        <el-option v-for="f in factoryList" :key="f.id" :label="f.factoryName" :value="f.id"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="名称" prop="processPathName">
      <el-input v-model="addForm.processPathName" clearable placeholder="请输入名称" @change="loadSzm"/>
    </el-form-item>
    <el-form-item label="编码" prop="processPathCode">
      <el-input v-model="addForm.processPathCode" clearable placeholder="请输入编码"/>
    </el-form-item>
    <el-form-item label="备注" prop="processPathRemark">
      <el-input v-model="addForm.processPathRemark" clearable placeholder="请输入"/>
    </el-form-item>
    <el-form-item label="默认" prop="isDefault">
      <el-select v-model="addForm.isDefault">
        <el-option v-for="i in isDefaultList" :label="i.label" :value="i.value" :key="i.value"/>
      </el-select>
    </el-form-item>
    <el-form-item label="工艺路径车间" prop="roomList">
      <div style="width: 800px">
        <el-button icon="plus" type="primary" @click="addRoom">
          添加
        </el-button>
      </div>
      <div v-for="(item,index) in addForm.pathRoomList">
        <el-select v-model="item.roomId" clearable placeholder="请选择车间" style="width: 700px">
          <el-option v-for="item in roomList" :key="item.id" :label="item.roomName" :value="item.id"></el-option>
        </el-select>
        <el-button icon="delete" type="danger" @click="deleteRoom(index)">
          删除
        </el-button>
      </div>
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

