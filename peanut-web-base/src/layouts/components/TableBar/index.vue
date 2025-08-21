<script lang="ts" setup>

import {downloadFilePost, postNoResult} from "@@/utils/common-js.ts"
import  UploadFile from "./UploadFile.vue"
import {ElTable} from "element-plus";
const props = defineProps({
  dialogWith: {
    type: Number,
    default: 500
  },
  multipleSelection: {
    type: Array,
    default: () => []
  },
  dataBatchDeleteUrl: {
    type: String,
    required: false,
    default: ""
  },
  dataTableRef: {
    type: Object,
    required: true
  },
  documentTitle: {
    type: String,
    required: true
  },
  showAddBtn: {
    type: Boolean,
    default: true
  },
  showDelBtn: {
    type: Boolean,
    default: true
  },
  showRefreshBtn: {
    type: Boolean,
    default: true
  },
  downLoadUrl: {
    type: String,
    required: false,
    default: ""
  },
  uploadUrl: {
    type: String,
    required: false,
    default: ""
  },
  addBtnFun: {
    type: Function,
    required: false
  },
  saveBeforeFun: {
    type: Function,
    required: false
  },
  refreshList: {
    type: Function,
    required: false
  },
  addComponent: {
    type: Object,
    required: false
  },
  queryDto: {
    type: Object,
    required: false
  }
})

const addDialogShow = ref(false)
const dialogType = ref<string>("")
const editId = ref<string>("")

const config = ref({
  title: ""
})

function addItem() {
  editId.value = ""
  config.value.title = `添加${props.documentTitle}`
  dialogType.value = "addItem"
  addDialogShow.value = true
}

function deleteById() {
  if (props.dataBatchDeleteUrl === "") {
    ElMessage.error("请配置删除地址")
    return
  }
  if (props.multipleSelection.length < 1) {
    ElMessage.error("请选择需要删除的对象")
    return
  }
  console.info("dataTableRef.value ", props.multipleSelection)
  ElMessageBox.confirm(`确定需要删除<span style="color: red; margin: 0 5px">${props.multipleSelection.length}</span>条数据吗？`, "删除提示", {
    dangerouslyUseHTMLString: true,
    type: "warning"
  }).then(() => {
    postNoResult(props.dataBatchDeleteUrl, {idList: props.multipleSelection}, "删除成功", () => {
      props.refreshList && props.refreshList()
    })
  })
}

function saveFun() {
  addDialogShow.value = false
  props.refreshList && props.refreshList()
}

function showEditDialog(eId: string) {
  console.info("editId ", eId)
  try {
    editId.value = eId
    config.value.title = `修改${props.documentTitle}`
    dialogType.value = "addItem"
    addDialogShow.value = true
  }catch (e){
    console.error("showEditDialog error" ,e)
  }
}

function downloadFun() {
  downloadFilePost(props.downLoadUrl, props.queryDto)
}

function uploadFun() {
  config.value.title = `上传文件`
  dialogType.value = "upload"
  addDialogShow.value = true
}
defineExpose({
  showEditDialog
})
</script>

<template>
  <el-button v-if="props.showAddBtn" type="primary" icon="plus" @click="addItem">
    添加
  </el-button>
  <el-button v-if="props.showDelBtn" type="danger" icon="minus" @click="deleteById">
    删除
  </el-button>
  <el-button v-if="props.showRefreshBtn && props.refreshList" type="info" icon="Refresh" @click="props.refreshList">
    刷新
  </el-button>
  <el-button v-if="props.downLoadUrl" type="warning" icon="download" @click="downloadFun">
    下载
  </el-button>
  <el-button v-if="props.uploadUrl" type="primary" icon="upload" @click="uploadFun">
    上传
  </el-button>
  <slot name="otherBtn"/>
  <el-dialog :title="config.title" v-model="addDialogShow" destroy-on-close :width="dialogWith">
    <component
      v-if="dialogType === 'addItem'"
      :is="props.addComponent" :save-fun="saveFun" :edit-id="editId"
    />
    <div v-if="dialogType === 'upload'">
      <upload-file/>
    </div>
  </el-dialog>
</template>

<style lang="scss" scoped>

</style>
