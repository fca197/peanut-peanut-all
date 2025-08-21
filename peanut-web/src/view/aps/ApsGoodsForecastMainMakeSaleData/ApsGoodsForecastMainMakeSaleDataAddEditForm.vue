<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsGoodsForecastMainMakeSaleData} from "./ApsGoodsForecastMainMakeSaleDataType.ts"
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
const dtoUrl = ref<string>("/apsGoodsForecastMainMakeSaleData")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
    // 商品ID
    goodsId: [
      {required: true, message: "请输入商品ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    mainMakeId: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    saleConfigCode: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 
    year: [
      {required: true, message: "请输入${column.comment}", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum1: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum2: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum3: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum4: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum5: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum6: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum7: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum8: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum9: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum10: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum11: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum12: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum13: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum14: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum15: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum16: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum17: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum18: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum19: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum20: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum21: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum22: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum23: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum24: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum25: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum26: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum27: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum28: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum29: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum30: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum31: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum32: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum33: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum34: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum35: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum36: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum37: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum38: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum39: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum40: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum41: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum42: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum43: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum44: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum45: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum46: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum47: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum48: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum49: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum50: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum51: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum52: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum53: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum54: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum55: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum56: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum57: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum58: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum59: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum60: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum61: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum62: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum63: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum64: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum65: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum66: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum67: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum68: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum69: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum70: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum71: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum72: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum73: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum74: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum75: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum76: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum77: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum78: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum79: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum80: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum81: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum82: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum83: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum84: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum85: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum86: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum87: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum88: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum89: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum90: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum91: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum92: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum93: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum94: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum95: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum96: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum97: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum98: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum99: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum100: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum101: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum102: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum103: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum104: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum105: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum106: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum107: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum108: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum109: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum110: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum111: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum112: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum113: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum114: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum115: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum116: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum117: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum118: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum119: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum120: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum121: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum122: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum123: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum124: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum125: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum126: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum127: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum128: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum129: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum130: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum131: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum132: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum133: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum134: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum135: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum136: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum137: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum138: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum139: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum140: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum141: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum142: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum143: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum144: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum145: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum146: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum147: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum148: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum149: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum150: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum151: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum152: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum153: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum154: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum155: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum156: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum157: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum158: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum159: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum160: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum161: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum162: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum163: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum164: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum165: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum166: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum167: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum168: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum169: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum170: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum171: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum172: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum173: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum174: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum175: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum176: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum177: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum178: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum179: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum180: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum181: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum182: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum183: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum184: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum185: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum186: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum187: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum188: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum189: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum190: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum191: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum192: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum193: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum194: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum195: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum196: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum197: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum198: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum199: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum200: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum201: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum202: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum203: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum204: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum205: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum206: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum207: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum208: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum209: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum210: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum211: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum212: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum213: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum214: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum215: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum216: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum217: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum218: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum219: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum220: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum221: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum222: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum223: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum224: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum225: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum226: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum227: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum228: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum229: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum230: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum231: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum232: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum233: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum234: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum235: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum236: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum237: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum238: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum239: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum240: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum241: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum242: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum243: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum244: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum245: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum246: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum247: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum248: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum249: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum250: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum251: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum252: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum253: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum254: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum255: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum256: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum257: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum258: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum259: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum260: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum261: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum262: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum263: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum264: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum265: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum266: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum267: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum268: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum269: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum270: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum271: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum272: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum273: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum274: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum275: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum276: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum277: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum278: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum279: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum280: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum281: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum282: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum283: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum284: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum285: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum286: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum287: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum288: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum289: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum290: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum291: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum292: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum293: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum294: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum295: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum296: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum297: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum298: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum299: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum300: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum301: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum302: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum303: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum304: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum305: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum306: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum307: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum308: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum309: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum310: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum311: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum312: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum313: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum314: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum315: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum316: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum317: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum318: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum319: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum320: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum321: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum322: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum323: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum324: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum325: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum326: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum327: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum328: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum329: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum330: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum331: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum332: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum333: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum334: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum335: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum336: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum337: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum338: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum339: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum340: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum341: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum342: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum343: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum344: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum345: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum346: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum347: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum348: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum349: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum350: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum351: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum352: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum353: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum354: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum355: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum356: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum357: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum358: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum359: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum360: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum361: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum362: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum363: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum364: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum365: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 制造数
    dayNum366: [
      {required: true, message: "请输入制造数", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 工厂ID
    factoryId: [
      {required: true, message: "请输入工厂ID", trigger: "blur"},
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],

})


// 添加对象
const addForm = ref<ApsGoodsForecastMainMakeSaleData>({
      goodsId: "",
      mainMakeId: "",
      saleConfigCode: "",
      year: "",
      dayNum1: "",
      dayNum2: "",
      dayNum3: "",
      dayNum4: "",
      dayNum5: "",
      dayNum6: "",
      dayNum7: "",
      dayNum8: "",
      dayNum9: "",
      dayNum10: "",
      dayNum11: "",
      dayNum12: "",
      dayNum13: "",
      dayNum14: "",
      dayNum15: "",
      dayNum16: "",
      dayNum17: "",
      dayNum18: "",
      dayNum19: "",
      dayNum20: "",
      dayNum21: "",
      dayNum22: "",
      dayNum23: "",
      dayNum24: "",
      dayNum25: "",
      dayNum26: "",
      dayNum27: "",
      dayNum28: "",
      dayNum29: "",
      dayNum30: "",
      dayNum31: "",
      dayNum32: "",
      dayNum33: "",
      dayNum34: "",
      dayNum35: "",
      dayNum36: "",
      dayNum37: "",
      dayNum38: "",
      dayNum39: "",
      dayNum40: "",
      dayNum41: "",
      dayNum42: "",
      dayNum43: "",
      dayNum44: "",
      dayNum45: "",
      dayNum46: "",
      dayNum47: "",
      dayNum48: "",
      dayNum49: "",
      dayNum50: "",
      dayNum51: "",
      dayNum52: "",
      dayNum53: "",
      dayNum54: "",
      dayNum55: "",
      dayNum56: "",
      dayNum57: "",
      dayNum58: "",
      dayNum59: "",
      dayNum60: "",
      dayNum61: "",
      dayNum62: "",
      dayNum63: "",
      dayNum64: "",
      dayNum65: "",
      dayNum66: "",
      dayNum67: "",
      dayNum68: "",
      dayNum69: "",
      dayNum70: "",
      dayNum71: "",
      dayNum72: "",
      dayNum73: "",
      dayNum74: "",
      dayNum75: "",
      dayNum76: "",
      dayNum77: "",
      dayNum78: "",
      dayNum79: "",
      dayNum80: "",
      dayNum81: "",
      dayNum82: "",
      dayNum83: "",
      dayNum84: "",
      dayNum85: "",
      dayNum86: "",
      dayNum87: "",
      dayNum88: "",
      dayNum89: "",
      dayNum90: "",
      dayNum91: "",
      dayNum92: "",
      dayNum93: "",
      dayNum94: "",
      dayNum95: "",
      dayNum96: "",
      dayNum97: "",
      dayNum98: "",
      dayNum99: "",
      dayNum100: "",
      dayNum101: "",
      dayNum102: "",
      dayNum103: "",
      dayNum104: "",
      dayNum105: "",
      dayNum106: "",
      dayNum107: "",
      dayNum108: "",
      dayNum109: "",
      dayNum110: "",
      dayNum111: "",
      dayNum112: "",
      dayNum113: "",
      dayNum114: "",
      dayNum115: "",
      dayNum116: "",
      dayNum117: "",
      dayNum118: "",
      dayNum119: "",
      dayNum120: "",
      dayNum121: "",
      dayNum122: "",
      dayNum123: "",
      dayNum124: "",
      dayNum125: "",
      dayNum126: "",
      dayNum127: "",
      dayNum128: "",
      dayNum129: "",
      dayNum130: "",
      dayNum131: "",
      dayNum132: "",
      dayNum133: "",
      dayNum134: "",
      dayNum135: "",
      dayNum136: "",
      dayNum137: "",
      dayNum138: "",
      dayNum139: "",
      dayNum140: "",
      dayNum141: "",
      dayNum142: "",
      dayNum143: "",
      dayNum144: "",
      dayNum145: "",
      dayNum146: "",
      dayNum147: "",
      dayNum148: "",
      dayNum149: "",
      dayNum150: "",
      dayNum151: "",
      dayNum152: "",
      dayNum153: "",
      dayNum154: "",
      dayNum155: "",
      dayNum156: "",
      dayNum157: "",
      dayNum158: "",
      dayNum159: "",
      dayNum160: "",
      dayNum161: "",
      dayNum162: "",
      dayNum163: "",
      dayNum164: "",
      dayNum165: "",
      dayNum166: "",
      dayNum167: "",
      dayNum168: "",
      dayNum169: "",
      dayNum170: "",
      dayNum171: "",
      dayNum172: "",
      dayNum173: "",
      dayNum174: "",
      dayNum175: "",
      dayNum176: "",
      dayNum177: "",
      dayNum178: "",
      dayNum179: "",
      dayNum180: "",
      dayNum181: "",
      dayNum182: "",
      dayNum183: "",
      dayNum184: "",
      dayNum185: "",
      dayNum186: "",
      dayNum187: "",
      dayNum188: "",
      dayNum189: "",
      dayNum190: "",
      dayNum191: "",
      dayNum192: "",
      dayNum193: "",
      dayNum194: "",
      dayNum195: "",
      dayNum196: "",
      dayNum197: "",
      dayNum198: "",
      dayNum199: "",
      dayNum200: "",
      dayNum201: "",
      dayNum202: "",
      dayNum203: "",
      dayNum204: "",
      dayNum205: "",
      dayNum206: "",
      dayNum207: "",
      dayNum208: "",
      dayNum209: "",
      dayNum210: "",
      dayNum211: "",
      dayNum212: "",
      dayNum213: "",
      dayNum214: "",
      dayNum215: "",
      dayNum216: "",
      dayNum217: "",
      dayNum218: "",
      dayNum219: "",
      dayNum220: "",
      dayNum221: "",
      dayNum222: "",
      dayNum223: "",
      dayNum224: "",
      dayNum225: "",
      dayNum226: "",
      dayNum227: "",
      dayNum228: "",
      dayNum229: "",
      dayNum230: "",
      dayNum231: "",
      dayNum232: "",
      dayNum233: "",
      dayNum234: "",
      dayNum235: "",
      dayNum236: "",
      dayNum237: "",
      dayNum238: "",
      dayNum239: "",
      dayNum240: "",
      dayNum241: "",
      dayNum242: "",
      dayNum243: "",
      dayNum244: "",
      dayNum245: "",
      dayNum246: "",
      dayNum247: "",
      dayNum248: "",
      dayNum249: "",
      dayNum250: "",
      dayNum251: "",
      dayNum252: "",
      dayNum253: "",
      dayNum254: "",
      dayNum255: "",
      dayNum256: "",
      dayNum257: "",
      dayNum258: "",
      dayNum259: "",
      dayNum260: "",
      dayNum261: "",
      dayNum262: "",
      dayNum263: "",
      dayNum264: "",
      dayNum265: "",
      dayNum266: "",
      dayNum267: "",
      dayNum268: "",
      dayNum269: "",
      dayNum270: "",
      dayNum271: "",
      dayNum272: "",
      dayNum273: "",
      dayNum274: "",
      dayNum275: "",
      dayNum276: "",
      dayNum277: "",
      dayNum278: "",
      dayNum279: "",
      dayNum280: "",
      dayNum281: "",
      dayNum282: "",
      dayNum283: "",
      dayNum284: "",
      dayNum285: "",
      dayNum286: "",
      dayNum287: "",
      dayNum288: "",
      dayNum289: "",
      dayNum290: "",
      dayNum291: "",
      dayNum292: "",
      dayNum293: "",
      dayNum294: "",
      dayNum295: "",
      dayNum296: "",
      dayNum297: "",
      dayNum298: "",
      dayNum299: "",
      dayNum300: "",
      dayNum301: "",
      dayNum302: "",
      dayNum303: "",
      dayNum304: "",
      dayNum305: "",
      dayNum306: "",
      dayNum307: "",
      dayNum308: "",
      dayNum309: "",
      dayNum310: "",
      dayNum311: "",
      dayNum312: "",
      dayNum313: "",
      dayNum314: "",
      dayNum315: "",
      dayNum316: "",
      dayNum317: "",
      dayNum318: "",
      dayNum319: "",
      dayNum320: "",
      dayNum321: "",
      dayNum322: "",
      dayNum323: "",
      dayNum324: "",
      dayNum325: "",
      dayNum326: "",
      dayNum327: "",
      dayNum328: "",
      dayNum329: "",
      dayNum330: "",
      dayNum331: "",
      dayNum332: "",
      dayNum333: "",
      dayNum334: "",
      dayNum335: "",
      dayNum336: "",
      dayNum337: "",
      dayNum338: "",
      dayNum339: "",
      dayNum340: "",
      dayNum341: "",
      dayNum342: "",
      dayNum343: "",
      dayNum344: "",
      dayNum345: "",
      dayNum346: "",
      dayNum347: "",
      dayNum348: "",
      dayNum349: "",
      dayNum350: "",
      dayNum351: "",
      dayNum352: "",
      dayNum353: "",
      dayNum354: "",
      dayNum355: "",
      dayNum356: "",
      dayNum357: "",
      dayNum358: "",
      dayNum359: "",
      dayNum360: "",
      dayNum361: "",
      dayNum362: "",
      dayNum363: "",
      dayNum364: "",
      dayNum365: "",
      dayNum366: "",
      factoryId: "",
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
    <el-form-item label="商品" prop="goodsId">
      <el-input v-model="addForm.goodsId" clearable placeholder="请输入商品ID"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="mainMakeId">
      <el-input v-model="addForm.mainMakeId" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="saleConfigCode">
      <el-input v-model="addForm.saleConfigCode" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="${column.comment}" prop="year">
      <el-input v-model="addForm.year" clearable placeholder="请输入${column.comment}"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum1">
      <el-input v-model="addForm.dayNum1" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum2">
      <el-input v-model="addForm.dayNum2" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum3">
      <el-input v-model="addForm.dayNum3" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum4">
      <el-input v-model="addForm.dayNum4" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum5">
      <el-input v-model="addForm.dayNum5" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum6">
      <el-input v-model="addForm.dayNum6" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum7">
      <el-input v-model="addForm.dayNum7" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum8">
      <el-input v-model="addForm.dayNum8" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum9">
      <el-input v-model="addForm.dayNum9" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum10">
      <el-input v-model="addForm.dayNum10" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum11">
      <el-input v-model="addForm.dayNum11" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum12">
      <el-input v-model="addForm.dayNum12" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum13">
      <el-input v-model="addForm.dayNum13" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum14">
      <el-input v-model="addForm.dayNum14" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum15">
      <el-input v-model="addForm.dayNum15" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum16">
      <el-input v-model="addForm.dayNum16" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum17">
      <el-input v-model="addForm.dayNum17" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum18">
      <el-input v-model="addForm.dayNum18" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum19">
      <el-input v-model="addForm.dayNum19" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum20">
      <el-input v-model="addForm.dayNum20" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum21">
      <el-input v-model="addForm.dayNum21" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum22">
      <el-input v-model="addForm.dayNum22" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum23">
      <el-input v-model="addForm.dayNum23" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum24">
      <el-input v-model="addForm.dayNum24" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum25">
      <el-input v-model="addForm.dayNum25" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum26">
      <el-input v-model="addForm.dayNum26" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum27">
      <el-input v-model="addForm.dayNum27" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum28">
      <el-input v-model="addForm.dayNum28" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum29">
      <el-input v-model="addForm.dayNum29" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum30">
      <el-input v-model="addForm.dayNum30" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum31">
      <el-input v-model="addForm.dayNum31" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum32">
      <el-input v-model="addForm.dayNum32" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum33">
      <el-input v-model="addForm.dayNum33" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum34">
      <el-input v-model="addForm.dayNum34" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum35">
      <el-input v-model="addForm.dayNum35" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum36">
      <el-input v-model="addForm.dayNum36" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum37">
      <el-input v-model="addForm.dayNum37" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum38">
      <el-input v-model="addForm.dayNum38" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum39">
      <el-input v-model="addForm.dayNum39" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum40">
      <el-input v-model="addForm.dayNum40" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum41">
      <el-input v-model="addForm.dayNum41" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum42">
      <el-input v-model="addForm.dayNum42" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum43">
      <el-input v-model="addForm.dayNum43" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum44">
      <el-input v-model="addForm.dayNum44" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum45">
      <el-input v-model="addForm.dayNum45" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum46">
      <el-input v-model="addForm.dayNum46" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum47">
      <el-input v-model="addForm.dayNum47" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum48">
      <el-input v-model="addForm.dayNum48" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum49">
      <el-input v-model="addForm.dayNum49" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum50">
      <el-input v-model="addForm.dayNum50" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum51">
      <el-input v-model="addForm.dayNum51" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum52">
      <el-input v-model="addForm.dayNum52" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum53">
      <el-input v-model="addForm.dayNum53" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum54">
      <el-input v-model="addForm.dayNum54" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum55">
      <el-input v-model="addForm.dayNum55" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum56">
      <el-input v-model="addForm.dayNum56" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum57">
      <el-input v-model="addForm.dayNum57" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum58">
      <el-input v-model="addForm.dayNum58" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum59">
      <el-input v-model="addForm.dayNum59" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum60">
      <el-input v-model="addForm.dayNum60" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum61">
      <el-input v-model="addForm.dayNum61" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum62">
      <el-input v-model="addForm.dayNum62" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum63">
      <el-input v-model="addForm.dayNum63" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum64">
      <el-input v-model="addForm.dayNum64" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum65">
      <el-input v-model="addForm.dayNum65" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum66">
      <el-input v-model="addForm.dayNum66" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum67">
      <el-input v-model="addForm.dayNum67" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum68">
      <el-input v-model="addForm.dayNum68" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum69">
      <el-input v-model="addForm.dayNum69" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum70">
      <el-input v-model="addForm.dayNum70" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum71">
      <el-input v-model="addForm.dayNum71" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum72">
      <el-input v-model="addForm.dayNum72" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum73">
      <el-input v-model="addForm.dayNum73" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum74">
      <el-input v-model="addForm.dayNum74" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum75">
      <el-input v-model="addForm.dayNum75" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum76">
      <el-input v-model="addForm.dayNum76" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum77">
      <el-input v-model="addForm.dayNum77" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum78">
      <el-input v-model="addForm.dayNum78" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum79">
      <el-input v-model="addForm.dayNum79" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum80">
      <el-input v-model="addForm.dayNum80" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum81">
      <el-input v-model="addForm.dayNum81" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum82">
      <el-input v-model="addForm.dayNum82" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum83">
      <el-input v-model="addForm.dayNum83" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum84">
      <el-input v-model="addForm.dayNum84" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum85">
      <el-input v-model="addForm.dayNum85" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum86">
      <el-input v-model="addForm.dayNum86" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum87">
      <el-input v-model="addForm.dayNum87" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum88">
      <el-input v-model="addForm.dayNum88" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum89">
      <el-input v-model="addForm.dayNum89" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum90">
      <el-input v-model="addForm.dayNum90" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum91">
      <el-input v-model="addForm.dayNum91" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum92">
      <el-input v-model="addForm.dayNum92" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum93">
      <el-input v-model="addForm.dayNum93" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum94">
      <el-input v-model="addForm.dayNum94" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum95">
      <el-input v-model="addForm.dayNum95" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum96">
      <el-input v-model="addForm.dayNum96" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum97">
      <el-input v-model="addForm.dayNum97" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum98">
      <el-input v-model="addForm.dayNum98" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum99">
      <el-input v-model="addForm.dayNum99" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum100">
      <el-input v-model="addForm.dayNum100" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum101">
      <el-input v-model="addForm.dayNum101" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum102">
      <el-input v-model="addForm.dayNum102" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum103">
      <el-input v-model="addForm.dayNum103" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum104">
      <el-input v-model="addForm.dayNum104" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum105">
      <el-input v-model="addForm.dayNum105" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum106">
      <el-input v-model="addForm.dayNum106" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum107">
      <el-input v-model="addForm.dayNum107" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum108">
      <el-input v-model="addForm.dayNum108" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum109">
      <el-input v-model="addForm.dayNum109" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum110">
      <el-input v-model="addForm.dayNum110" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum111">
      <el-input v-model="addForm.dayNum111" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum112">
      <el-input v-model="addForm.dayNum112" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum113">
      <el-input v-model="addForm.dayNum113" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum114">
      <el-input v-model="addForm.dayNum114" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum115">
      <el-input v-model="addForm.dayNum115" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum116">
      <el-input v-model="addForm.dayNum116" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum117">
      <el-input v-model="addForm.dayNum117" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum118">
      <el-input v-model="addForm.dayNum118" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum119">
      <el-input v-model="addForm.dayNum119" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum120">
      <el-input v-model="addForm.dayNum120" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum121">
      <el-input v-model="addForm.dayNum121" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum122">
      <el-input v-model="addForm.dayNum122" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum123">
      <el-input v-model="addForm.dayNum123" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum124">
      <el-input v-model="addForm.dayNum124" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum125">
      <el-input v-model="addForm.dayNum125" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum126">
      <el-input v-model="addForm.dayNum126" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum127">
      <el-input v-model="addForm.dayNum127" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum128">
      <el-input v-model="addForm.dayNum128" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum129">
      <el-input v-model="addForm.dayNum129" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum130">
      <el-input v-model="addForm.dayNum130" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum131">
      <el-input v-model="addForm.dayNum131" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum132">
      <el-input v-model="addForm.dayNum132" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum133">
      <el-input v-model="addForm.dayNum133" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum134">
      <el-input v-model="addForm.dayNum134" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum135">
      <el-input v-model="addForm.dayNum135" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum136">
      <el-input v-model="addForm.dayNum136" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum137">
      <el-input v-model="addForm.dayNum137" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum138">
      <el-input v-model="addForm.dayNum138" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum139">
      <el-input v-model="addForm.dayNum139" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum140">
      <el-input v-model="addForm.dayNum140" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum141">
      <el-input v-model="addForm.dayNum141" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum142">
      <el-input v-model="addForm.dayNum142" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum143">
      <el-input v-model="addForm.dayNum143" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum144">
      <el-input v-model="addForm.dayNum144" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum145">
      <el-input v-model="addForm.dayNum145" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum146">
      <el-input v-model="addForm.dayNum146" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum147">
      <el-input v-model="addForm.dayNum147" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum148">
      <el-input v-model="addForm.dayNum148" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum149">
      <el-input v-model="addForm.dayNum149" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum150">
      <el-input v-model="addForm.dayNum150" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum151">
      <el-input v-model="addForm.dayNum151" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum152">
      <el-input v-model="addForm.dayNum152" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum153">
      <el-input v-model="addForm.dayNum153" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum154">
      <el-input v-model="addForm.dayNum154" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum155">
      <el-input v-model="addForm.dayNum155" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum156">
      <el-input v-model="addForm.dayNum156" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum157">
      <el-input v-model="addForm.dayNum157" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum158">
      <el-input v-model="addForm.dayNum158" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum159">
      <el-input v-model="addForm.dayNum159" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum160">
      <el-input v-model="addForm.dayNum160" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum161">
      <el-input v-model="addForm.dayNum161" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum162">
      <el-input v-model="addForm.dayNum162" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum163">
      <el-input v-model="addForm.dayNum163" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum164">
      <el-input v-model="addForm.dayNum164" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum165">
      <el-input v-model="addForm.dayNum165" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum166">
      <el-input v-model="addForm.dayNum166" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum167">
      <el-input v-model="addForm.dayNum167" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum168">
      <el-input v-model="addForm.dayNum168" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum169">
      <el-input v-model="addForm.dayNum169" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum170">
      <el-input v-model="addForm.dayNum170" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum171">
      <el-input v-model="addForm.dayNum171" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum172">
      <el-input v-model="addForm.dayNum172" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum173">
      <el-input v-model="addForm.dayNum173" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum174">
      <el-input v-model="addForm.dayNum174" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum175">
      <el-input v-model="addForm.dayNum175" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum176">
      <el-input v-model="addForm.dayNum176" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum177">
      <el-input v-model="addForm.dayNum177" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum178">
      <el-input v-model="addForm.dayNum178" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum179">
      <el-input v-model="addForm.dayNum179" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum180">
      <el-input v-model="addForm.dayNum180" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum181">
      <el-input v-model="addForm.dayNum181" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum182">
      <el-input v-model="addForm.dayNum182" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum183">
      <el-input v-model="addForm.dayNum183" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum184">
      <el-input v-model="addForm.dayNum184" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum185">
      <el-input v-model="addForm.dayNum185" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum186">
      <el-input v-model="addForm.dayNum186" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum187">
      <el-input v-model="addForm.dayNum187" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum188">
      <el-input v-model="addForm.dayNum188" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum189">
      <el-input v-model="addForm.dayNum189" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum190">
      <el-input v-model="addForm.dayNum190" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum191">
      <el-input v-model="addForm.dayNum191" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum192">
      <el-input v-model="addForm.dayNum192" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum193">
      <el-input v-model="addForm.dayNum193" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum194">
      <el-input v-model="addForm.dayNum194" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum195">
      <el-input v-model="addForm.dayNum195" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum196">
      <el-input v-model="addForm.dayNum196" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum197">
      <el-input v-model="addForm.dayNum197" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum198">
      <el-input v-model="addForm.dayNum198" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum199">
      <el-input v-model="addForm.dayNum199" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum200">
      <el-input v-model="addForm.dayNum200" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum201">
      <el-input v-model="addForm.dayNum201" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum202">
      <el-input v-model="addForm.dayNum202" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum203">
      <el-input v-model="addForm.dayNum203" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum204">
      <el-input v-model="addForm.dayNum204" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum205">
      <el-input v-model="addForm.dayNum205" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum206">
      <el-input v-model="addForm.dayNum206" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum207">
      <el-input v-model="addForm.dayNum207" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum208">
      <el-input v-model="addForm.dayNum208" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum209">
      <el-input v-model="addForm.dayNum209" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum210">
      <el-input v-model="addForm.dayNum210" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum211">
      <el-input v-model="addForm.dayNum211" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum212">
      <el-input v-model="addForm.dayNum212" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum213">
      <el-input v-model="addForm.dayNum213" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum214">
      <el-input v-model="addForm.dayNum214" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum215">
      <el-input v-model="addForm.dayNum215" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum216">
      <el-input v-model="addForm.dayNum216" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum217">
      <el-input v-model="addForm.dayNum217" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum218">
      <el-input v-model="addForm.dayNum218" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum219">
      <el-input v-model="addForm.dayNum219" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum220">
      <el-input v-model="addForm.dayNum220" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum221">
      <el-input v-model="addForm.dayNum221" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum222">
      <el-input v-model="addForm.dayNum222" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum223">
      <el-input v-model="addForm.dayNum223" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum224">
      <el-input v-model="addForm.dayNum224" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum225">
      <el-input v-model="addForm.dayNum225" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum226">
      <el-input v-model="addForm.dayNum226" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum227">
      <el-input v-model="addForm.dayNum227" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum228">
      <el-input v-model="addForm.dayNum228" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum229">
      <el-input v-model="addForm.dayNum229" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum230">
      <el-input v-model="addForm.dayNum230" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum231">
      <el-input v-model="addForm.dayNum231" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum232">
      <el-input v-model="addForm.dayNum232" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum233">
      <el-input v-model="addForm.dayNum233" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum234">
      <el-input v-model="addForm.dayNum234" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum235">
      <el-input v-model="addForm.dayNum235" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum236">
      <el-input v-model="addForm.dayNum236" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum237">
      <el-input v-model="addForm.dayNum237" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum238">
      <el-input v-model="addForm.dayNum238" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum239">
      <el-input v-model="addForm.dayNum239" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum240">
      <el-input v-model="addForm.dayNum240" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum241">
      <el-input v-model="addForm.dayNum241" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum242">
      <el-input v-model="addForm.dayNum242" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum243">
      <el-input v-model="addForm.dayNum243" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum244">
      <el-input v-model="addForm.dayNum244" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum245">
      <el-input v-model="addForm.dayNum245" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum246">
      <el-input v-model="addForm.dayNum246" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum247">
      <el-input v-model="addForm.dayNum247" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum248">
      <el-input v-model="addForm.dayNum248" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum249">
      <el-input v-model="addForm.dayNum249" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum250">
      <el-input v-model="addForm.dayNum250" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum251">
      <el-input v-model="addForm.dayNum251" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum252">
      <el-input v-model="addForm.dayNum252" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum253">
      <el-input v-model="addForm.dayNum253" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum254">
      <el-input v-model="addForm.dayNum254" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum255">
      <el-input v-model="addForm.dayNum255" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum256">
      <el-input v-model="addForm.dayNum256" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum257">
      <el-input v-model="addForm.dayNum257" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum258">
      <el-input v-model="addForm.dayNum258" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum259">
      <el-input v-model="addForm.dayNum259" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum260">
      <el-input v-model="addForm.dayNum260" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum261">
      <el-input v-model="addForm.dayNum261" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum262">
      <el-input v-model="addForm.dayNum262" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum263">
      <el-input v-model="addForm.dayNum263" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum264">
      <el-input v-model="addForm.dayNum264" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum265">
      <el-input v-model="addForm.dayNum265" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum266">
      <el-input v-model="addForm.dayNum266" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum267">
      <el-input v-model="addForm.dayNum267" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum268">
      <el-input v-model="addForm.dayNum268" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum269">
      <el-input v-model="addForm.dayNum269" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum270">
      <el-input v-model="addForm.dayNum270" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum271">
      <el-input v-model="addForm.dayNum271" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum272">
      <el-input v-model="addForm.dayNum272" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum273">
      <el-input v-model="addForm.dayNum273" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum274">
      <el-input v-model="addForm.dayNum274" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum275">
      <el-input v-model="addForm.dayNum275" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum276">
      <el-input v-model="addForm.dayNum276" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum277">
      <el-input v-model="addForm.dayNum277" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum278">
      <el-input v-model="addForm.dayNum278" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum279">
      <el-input v-model="addForm.dayNum279" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum280">
      <el-input v-model="addForm.dayNum280" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum281">
      <el-input v-model="addForm.dayNum281" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum282">
      <el-input v-model="addForm.dayNum282" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum283">
      <el-input v-model="addForm.dayNum283" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum284">
      <el-input v-model="addForm.dayNum284" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum285">
      <el-input v-model="addForm.dayNum285" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum286">
      <el-input v-model="addForm.dayNum286" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum287">
      <el-input v-model="addForm.dayNum287" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum288">
      <el-input v-model="addForm.dayNum288" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum289">
      <el-input v-model="addForm.dayNum289" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum290">
      <el-input v-model="addForm.dayNum290" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum291">
      <el-input v-model="addForm.dayNum291" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum292">
      <el-input v-model="addForm.dayNum292" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum293">
      <el-input v-model="addForm.dayNum293" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum294">
      <el-input v-model="addForm.dayNum294" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum295">
      <el-input v-model="addForm.dayNum295" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum296">
      <el-input v-model="addForm.dayNum296" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum297">
      <el-input v-model="addForm.dayNum297" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum298">
      <el-input v-model="addForm.dayNum298" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum299">
      <el-input v-model="addForm.dayNum299" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum300">
      <el-input v-model="addForm.dayNum300" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum301">
      <el-input v-model="addForm.dayNum301" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum302">
      <el-input v-model="addForm.dayNum302" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum303">
      <el-input v-model="addForm.dayNum303" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum304">
      <el-input v-model="addForm.dayNum304" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum305">
      <el-input v-model="addForm.dayNum305" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum306">
      <el-input v-model="addForm.dayNum306" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum307">
      <el-input v-model="addForm.dayNum307" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum308">
      <el-input v-model="addForm.dayNum308" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum309">
      <el-input v-model="addForm.dayNum309" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum310">
      <el-input v-model="addForm.dayNum310" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum311">
      <el-input v-model="addForm.dayNum311" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum312">
      <el-input v-model="addForm.dayNum312" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum313">
      <el-input v-model="addForm.dayNum313" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum314">
      <el-input v-model="addForm.dayNum314" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum315">
      <el-input v-model="addForm.dayNum315" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum316">
      <el-input v-model="addForm.dayNum316" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum317">
      <el-input v-model="addForm.dayNum317" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum318">
      <el-input v-model="addForm.dayNum318" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum319">
      <el-input v-model="addForm.dayNum319" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum320">
      <el-input v-model="addForm.dayNum320" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum321">
      <el-input v-model="addForm.dayNum321" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum322">
      <el-input v-model="addForm.dayNum322" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum323">
      <el-input v-model="addForm.dayNum323" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum324">
      <el-input v-model="addForm.dayNum324" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum325">
      <el-input v-model="addForm.dayNum325" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum326">
      <el-input v-model="addForm.dayNum326" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum327">
      <el-input v-model="addForm.dayNum327" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum328">
      <el-input v-model="addForm.dayNum328" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum329">
      <el-input v-model="addForm.dayNum329" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum330">
      <el-input v-model="addForm.dayNum330" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum331">
      <el-input v-model="addForm.dayNum331" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum332">
      <el-input v-model="addForm.dayNum332" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum333">
      <el-input v-model="addForm.dayNum333" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum334">
      <el-input v-model="addForm.dayNum334" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum335">
      <el-input v-model="addForm.dayNum335" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum336">
      <el-input v-model="addForm.dayNum336" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum337">
      <el-input v-model="addForm.dayNum337" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum338">
      <el-input v-model="addForm.dayNum338" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum339">
      <el-input v-model="addForm.dayNum339" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum340">
      <el-input v-model="addForm.dayNum340" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum341">
      <el-input v-model="addForm.dayNum341" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum342">
      <el-input v-model="addForm.dayNum342" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum343">
      <el-input v-model="addForm.dayNum343" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum344">
      <el-input v-model="addForm.dayNum344" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum345">
      <el-input v-model="addForm.dayNum345" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum346">
      <el-input v-model="addForm.dayNum346" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum347">
      <el-input v-model="addForm.dayNum347" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum348">
      <el-input v-model="addForm.dayNum348" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum349">
      <el-input v-model="addForm.dayNum349" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum350">
      <el-input v-model="addForm.dayNum350" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum351">
      <el-input v-model="addForm.dayNum351" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum352">
      <el-input v-model="addForm.dayNum352" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum353">
      <el-input v-model="addForm.dayNum353" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum354">
      <el-input v-model="addForm.dayNum354" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum355">
      <el-input v-model="addForm.dayNum355" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum356">
      <el-input v-model="addForm.dayNum356" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum357">
      <el-input v-model="addForm.dayNum357" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum358">
      <el-input v-model="addForm.dayNum358" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum359">
      <el-input v-model="addForm.dayNum359" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum360">
      <el-input v-model="addForm.dayNum360" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum361">
      <el-input v-model="addForm.dayNum361" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum362">
      <el-input v-model="addForm.dayNum362" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum363">
      <el-input v-model="addForm.dayNum363" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum364">
      <el-input v-model="addForm.dayNum364" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum365">
      <el-input v-model="addForm.dayNum365" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="制造数" prop="dayNum366">
      <el-input v-model="addForm.dayNum366" clearable placeholder="请输入制造数"/>
    </el-form-item>
    <el-form-item label="工厂" prop="factoryId">
      <el-input v-model="addForm.factoryId" clearable placeholder="请输入工厂ID"/>
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

