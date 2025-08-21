<script setup lang="ts">
import {onMounted, ref} from "vue"
import {type ApsGoodsBomBuyPlanItem} from "./ApsGoodsBomBuyPlanItemType.ts"
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
const dtoUrl = ref<string>("/apsGoodsBomBuyPlanItem")
// 表单引用
const addFormRef = ref<FormInstance>()
// 表单校验规则
const checkRules = ref<FormRules>({
    // 购买计划ID
    buyPlanId: [
      {required: true, message: "请输入购买计划ID", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 年
    year: [
      {required: true, message: "请输入年", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 零件ID
    bomId: [
      {required: true, message: "请输入零件ID", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 商品零件ID
    goodsBomId: [
      {required: true, message: "请输入商品零件ID", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // bom 编码
    bomCode: [
      {required: true, message: "请输入bom 编码", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // bom 名称
    bomName: [
      {required: true, message: "请输入bom 名称", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用量
    bomUsage: [
      {required: true, message: "请输入使用量", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 规格
    bomUnit: [
      {required: true, message: "请输入规格", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 成本价
    bomCostPrice: [
      {required: true, message: "请输入成本价", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 规格
    bomCostPriceUnit: [
      {required: true, message: "请输入规格", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用工位
    bomUseWorkStation: [
      {required: true, message: "请输入使用工位", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay1: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay2: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay3: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay4: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay5: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay6: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay7: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay8: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay9: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay10: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay11: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay12: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay13: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay14: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay15: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay16: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay17: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay18: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay19: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay20: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay21: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay22: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay23: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay24: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay25: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay26: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay27: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay28: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay29: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay30: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay31: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay32: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay33: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay34: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay35: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay36: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay37: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay38: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay39: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay40: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay41: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay42: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay43: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay44: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay45: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay46: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay47: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay48: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay49: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay50: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay51: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay52: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay53: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay54: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay55: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay56: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay57: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay58: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay59: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay60: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay61: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay62: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay63: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay64: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay65: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay66: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay67: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay68: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay69: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay70: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay71: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay72: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay73: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay74: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay75: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay76: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay77: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay78: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay79: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay80: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay81: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay82: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay83: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay84: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay85: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay86: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay87: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay88: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay89: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay90: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay91: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay92: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay93: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay94: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay95: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay96: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay97: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay98: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay99: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay100: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay101: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay102: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay103: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay104: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay105: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay106: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay107: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay108: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay109: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay110: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay111: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay112: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay113: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay114: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay115: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay116: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay117: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay118: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay119: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay120: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay121: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay122: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay123: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay124: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay125: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay126: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay127: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay128: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay129: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay130: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay131: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay132: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay133: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay134: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay135: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay136: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay137: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay138: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay139: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay140: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay141: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay142: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay143: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay144: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay145: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay146: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay147: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay148: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay149: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay150: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay151: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay152: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay153: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay154: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay155: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay156: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay157: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay158: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay159: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay160: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay161: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay162: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay163: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay164: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay165: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay166: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay167: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay168: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay169: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay170: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay171: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay172: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay173: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay174: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay175: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay176: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay177: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay178: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay179: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay180: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay181: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay182: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay183: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay184: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay185: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay186: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay187: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay188: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay189: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay190: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay191: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay192: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay193: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay194: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay195: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay196: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay197: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay198: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay199: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay200: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay201: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay202: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay203: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay204: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay205: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay206: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay207: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay208: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay209: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay210: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay211: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay212: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay213: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay214: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay215: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay216: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay217: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay218: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay219: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay220: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay221: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay222: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay223: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay224: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay225: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay226: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay227: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay228: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay229: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay230: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay231: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay232: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay233: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay234: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay235: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay236: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay237: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay238: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay239: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay240: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay241: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay242: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay243: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay244: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay245: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay246: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay247: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay248: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay249: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay250: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay251: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay252: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay253: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay254: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay255: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay256: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay257: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay258: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay259: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay260: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay261: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay262: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay263: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay264: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay265: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay266: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay267: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay268: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay269: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay270: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay271: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay272: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay273: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay274: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay275: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay276: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay277: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay278: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay279: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay280: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay281: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay282: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay283: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay284: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay285: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay286: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay287: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay288: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay289: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay290: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay291: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay292: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay293: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay294: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay295: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay296: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay297: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay298: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay299: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay300: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay301: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay302: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay303: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay304: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay305: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay306: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay307: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay308: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay309: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay310: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay311: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay312: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay313: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay314: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay315: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay316: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay317: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay318: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay319: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay320: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay321: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay322: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay323: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay324: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay325: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay326: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay327: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay328: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay329: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay330: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay331: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay332: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay333: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay334: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay335: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay336: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay337: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay338: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay339: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay340: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay341: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay342: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay343: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay344: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay345: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay346: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay347: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay348: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay349: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay350: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay351: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay352: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay353: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay354: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay355: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay356: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay357: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay358: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay359: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay360: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay361: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay362: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay363: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay364: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay365: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 使用信息 {lack:1, quantity:3}
    bomUseDay366: [
      {required: true, message: "请输入使用信息 {lack:1, quantity:3}", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 工厂ID
    factoryId: [
      {required: true, message: "请输入工厂ID", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],
    // 是否关注
    isFollow: [
      {required: true, message: "请输入是否关注", trigger: "blur" },
      {min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur"}
    ],

})


// 添加对象
const addForm = ref<ApsGoodsBomBuyPlanItem>({
      buyPlanId: "",
      year: "",
      bomId: "",
      goodsBomId: "",
      bomCode: "",
      bomName: "",
      bomUsage: "",
      bomUnit: "",
      bomCostPrice: "",
      bomCostPriceUnit: "",
      bomUseWorkStation: "",
      bomUseDay1: "",
      bomUseDay2: "",
      bomUseDay3: "",
      bomUseDay4: "",
      bomUseDay5: "",
      bomUseDay6: "",
      bomUseDay7: "",
      bomUseDay8: "",
      bomUseDay9: "",
      bomUseDay10: "",
      bomUseDay11: "",
      bomUseDay12: "",
      bomUseDay13: "",
      bomUseDay14: "",
      bomUseDay15: "",
      bomUseDay16: "",
      bomUseDay17: "",
      bomUseDay18: "",
      bomUseDay19: "",
      bomUseDay20: "",
      bomUseDay21: "",
      bomUseDay22: "",
      bomUseDay23: "",
      bomUseDay24: "",
      bomUseDay25: "",
      bomUseDay26: "",
      bomUseDay27: "",
      bomUseDay28: "",
      bomUseDay29: "",
      bomUseDay30: "",
      bomUseDay31: "",
      bomUseDay32: "",
      bomUseDay33: "",
      bomUseDay34: "",
      bomUseDay35: "",
      bomUseDay36: "",
      bomUseDay37: "",
      bomUseDay38: "",
      bomUseDay39: "",
      bomUseDay40: "",
      bomUseDay41: "",
      bomUseDay42: "",
      bomUseDay43: "",
      bomUseDay44: "",
      bomUseDay45: "",
      bomUseDay46: "",
      bomUseDay47: "",
      bomUseDay48: "",
      bomUseDay49: "",
      bomUseDay50: "",
      bomUseDay51: "",
      bomUseDay52: "",
      bomUseDay53: "",
      bomUseDay54: "",
      bomUseDay55: "",
      bomUseDay56: "",
      bomUseDay57: "",
      bomUseDay58: "",
      bomUseDay59: "",
      bomUseDay60: "",
      bomUseDay61: "",
      bomUseDay62: "",
      bomUseDay63: "",
      bomUseDay64: "",
      bomUseDay65: "",
      bomUseDay66: "",
      bomUseDay67: "",
      bomUseDay68: "",
      bomUseDay69: "",
      bomUseDay70: "",
      bomUseDay71: "",
      bomUseDay72: "",
      bomUseDay73: "",
      bomUseDay74: "",
      bomUseDay75: "",
      bomUseDay76: "",
      bomUseDay77: "",
      bomUseDay78: "",
      bomUseDay79: "",
      bomUseDay80: "",
      bomUseDay81: "",
      bomUseDay82: "",
      bomUseDay83: "",
      bomUseDay84: "",
      bomUseDay85: "",
      bomUseDay86: "",
      bomUseDay87: "",
      bomUseDay88: "",
      bomUseDay89: "",
      bomUseDay90: "",
      bomUseDay91: "",
      bomUseDay92: "",
      bomUseDay93: "",
      bomUseDay94: "",
      bomUseDay95: "",
      bomUseDay96: "",
      bomUseDay97: "",
      bomUseDay98: "",
      bomUseDay99: "",
      bomUseDay100: "",
      bomUseDay101: "",
      bomUseDay102: "",
      bomUseDay103: "",
      bomUseDay104: "",
      bomUseDay105: "",
      bomUseDay106: "",
      bomUseDay107: "",
      bomUseDay108: "",
      bomUseDay109: "",
      bomUseDay110: "",
      bomUseDay111: "",
      bomUseDay112: "",
      bomUseDay113: "",
      bomUseDay114: "",
      bomUseDay115: "",
      bomUseDay116: "",
      bomUseDay117: "",
      bomUseDay118: "",
      bomUseDay119: "",
      bomUseDay120: "",
      bomUseDay121: "",
      bomUseDay122: "",
      bomUseDay123: "",
      bomUseDay124: "",
      bomUseDay125: "",
      bomUseDay126: "",
      bomUseDay127: "",
      bomUseDay128: "",
      bomUseDay129: "",
      bomUseDay130: "",
      bomUseDay131: "",
      bomUseDay132: "",
      bomUseDay133: "",
      bomUseDay134: "",
      bomUseDay135: "",
      bomUseDay136: "",
      bomUseDay137: "",
      bomUseDay138: "",
      bomUseDay139: "",
      bomUseDay140: "",
      bomUseDay141: "",
      bomUseDay142: "",
      bomUseDay143: "",
      bomUseDay144: "",
      bomUseDay145: "",
      bomUseDay146: "",
      bomUseDay147: "",
      bomUseDay148: "",
      bomUseDay149: "",
      bomUseDay150: "",
      bomUseDay151: "",
      bomUseDay152: "",
      bomUseDay153: "",
      bomUseDay154: "",
      bomUseDay155: "",
      bomUseDay156: "",
      bomUseDay157: "",
      bomUseDay158: "",
      bomUseDay159: "",
      bomUseDay160: "",
      bomUseDay161: "",
      bomUseDay162: "",
      bomUseDay163: "",
      bomUseDay164: "",
      bomUseDay165: "",
      bomUseDay166: "",
      bomUseDay167: "",
      bomUseDay168: "",
      bomUseDay169: "",
      bomUseDay170: "",
      bomUseDay171: "",
      bomUseDay172: "",
      bomUseDay173: "",
      bomUseDay174: "",
      bomUseDay175: "",
      bomUseDay176: "",
      bomUseDay177: "",
      bomUseDay178: "",
      bomUseDay179: "",
      bomUseDay180: "",
      bomUseDay181: "",
      bomUseDay182: "",
      bomUseDay183: "",
      bomUseDay184: "",
      bomUseDay185: "",
      bomUseDay186: "",
      bomUseDay187: "",
      bomUseDay188: "",
      bomUseDay189: "",
      bomUseDay190: "",
      bomUseDay191: "",
      bomUseDay192: "",
      bomUseDay193: "",
      bomUseDay194: "",
      bomUseDay195: "",
      bomUseDay196: "",
      bomUseDay197: "",
      bomUseDay198: "",
      bomUseDay199: "",
      bomUseDay200: "",
      bomUseDay201: "",
      bomUseDay202: "",
      bomUseDay203: "",
      bomUseDay204: "",
      bomUseDay205: "",
      bomUseDay206: "",
      bomUseDay207: "",
      bomUseDay208: "",
      bomUseDay209: "",
      bomUseDay210: "",
      bomUseDay211: "",
      bomUseDay212: "",
      bomUseDay213: "",
      bomUseDay214: "",
      bomUseDay215: "",
      bomUseDay216: "",
      bomUseDay217: "",
      bomUseDay218: "",
      bomUseDay219: "",
      bomUseDay220: "",
      bomUseDay221: "",
      bomUseDay222: "",
      bomUseDay223: "",
      bomUseDay224: "",
      bomUseDay225: "",
      bomUseDay226: "",
      bomUseDay227: "",
      bomUseDay228: "",
      bomUseDay229: "",
      bomUseDay230: "",
      bomUseDay231: "",
      bomUseDay232: "",
      bomUseDay233: "",
      bomUseDay234: "",
      bomUseDay235: "",
      bomUseDay236: "",
      bomUseDay237: "",
      bomUseDay238: "",
      bomUseDay239: "",
      bomUseDay240: "",
      bomUseDay241: "",
      bomUseDay242: "",
      bomUseDay243: "",
      bomUseDay244: "",
      bomUseDay245: "",
      bomUseDay246: "",
      bomUseDay247: "",
      bomUseDay248: "",
      bomUseDay249: "",
      bomUseDay250: "",
      bomUseDay251: "",
      bomUseDay252: "",
      bomUseDay253: "",
      bomUseDay254: "",
      bomUseDay255: "",
      bomUseDay256: "",
      bomUseDay257: "",
      bomUseDay258: "",
      bomUseDay259: "",
      bomUseDay260: "",
      bomUseDay261: "",
      bomUseDay262: "",
      bomUseDay263: "",
      bomUseDay264: "",
      bomUseDay265: "",
      bomUseDay266: "",
      bomUseDay267: "",
      bomUseDay268: "",
      bomUseDay269: "",
      bomUseDay270: "",
      bomUseDay271: "",
      bomUseDay272: "",
      bomUseDay273: "",
      bomUseDay274: "",
      bomUseDay275: "",
      bomUseDay276: "",
      bomUseDay277: "",
      bomUseDay278: "",
      bomUseDay279: "",
      bomUseDay280: "",
      bomUseDay281: "",
      bomUseDay282: "",
      bomUseDay283: "",
      bomUseDay284: "",
      bomUseDay285: "",
      bomUseDay286: "",
      bomUseDay287: "",
      bomUseDay288: "",
      bomUseDay289: "",
      bomUseDay290: "",
      bomUseDay291: "",
      bomUseDay292: "",
      bomUseDay293: "",
      bomUseDay294: "",
      bomUseDay295: "",
      bomUseDay296: "",
      bomUseDay297: "",
      bomUseDay298: "",
      bomUseDay299: "",
      bomUseDay300: "",
      bomUseDay301: "",
      bomUseDay302: "",
      bomUseDay303: "",
      bomUseDay304: "",
      bomUseDay305: "",
      bomUseDay306: "",
      bomUseDay307: "",
      bomUseDay308: "",
      bomUseDay309: "",
      bomUseDay310: "",
      bomUseDay311: "",
      bomUseDay312: "",
      bomUseDay313: "",
      bomUseDay314: "",
      bomUseDay315: "",
      bomUseDay316: "",
      bomUseDay317: "",
      bomUseDay318: "",
      bomUseDay319: "",
      bomUseDay320: "",
      bomUseDay321: "",
      bomUseDay322: "",
      bomUseDay323: "",
      bomUseDay324: "",
      bomUseDay325: "",
      bomUseDay326: "",
      bomUseDay327: "",
      bomUseDay328: "",
      bomUseDay329: "",
      bomUseDay330: "",
      bomUseDay331: "",
      bomUseDay332: "",
      bomUseDay333: "",
      bomUseDay334: "",
      bomUseDay335: "",
      bomUseDay336: "",
      bomUseDay337: "",
      bomUseDay338: "",
      bomUseDay339: "",
      bomUseDay340: "",
      bomUseDay341: "",
      bomUseDay342: "",
      bomUseDay343: "",
      bomUseDay344: "",
      bomUseDay345: "",
      bomUseDay346: "",
      bomUseDay347: "",
      bomUseDay348: "",
      bomUseDay349: "",
      bomUseDay350: "",
      bomUseDay351: "",
      bomUseDay352: "",
      bomUseDay353: "",
      bomUseDay354: "",
      bomUseDay355: "",
      bomUseDay356: "",
      bomUseDay357: "",
      bomUseDay358: "",
      bomUseDay359: "",
      bomUseDay360: "",
      bomUseDay361: "",
      bomUseDay362: "",
      bomUseDay363: "",
      bomUseDay364: "",
      bomUseDay365: "",
      bomUseDay366: "",
      factoryId: "",
      isFollow: "",
      id: ""
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

// 页面加载事件
onMounted(() => {
  loadById()
})
</script>

<template>
  <el-form label-width="80px" :model="addForm" ref="addFormRef" :rules="checkRules">
    <el-form-item label="购买计划ID" prop="buyPlanId">
      <el-input v-model="addForm.buyPlanId" clearable placeholder="请输入购买计划ID"/>
    </el-form-item>
    <el-form-item label="年" prop="year">
      <el-input v-model="addForm.year" clearable placeholder="请输入年"/>
    </el-form-item>
    <el-form-item label="零件ID" prop="bomId">
      <el-input v-model="addForm.bomId" clearable placeholder="请输入零件ID"/>
    </el-form-item>
    <el-form-item label="商品零件ID" prop="goodsBomId">
      <el-input v-model="addForm.goodsBomId" clearable placeholder="请输入商品零件ID"/>
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
    <el-form-item label="规格" prop="bomUnit">
      <el-input v-model="addForm.bomUnit" clearable placeholder="请输入规格"/>
    </el-form-item>
    <el-form-item label="成本价" prop="bomCostPrice">
      <el-input v-model="addForm.bomCostPrice" clearable placeholder="请输入成本价"/>
    </el-form-item>
    <el-form-item label="规格" prop="bomCostPriceUnit">
      <el-input v-model="addForm.bomCostPriceUnit" clearable placeholder="请输入规格"/>
    </el-form-item>
    <el-form-item label="使用工位" prop="bomUseWorkStation">
      <el-input v-model="addForm.bomUseWorkStation" clearable placeholder="请输入使用工位"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay1">
      <el-input v-model="addForm.bomUseDay1" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay2">
      <el-input v-model="addForm.bomUseDay2" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay3">
      <el-input v-model="addForm.bomUseDay3" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay4">
      <el-input v-model="addForm.bomUseDay4" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay5">
      <el-input v-model="addForm.bomUseDay5" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay6">
      <el-input v-model="addForm.bomUseDay6" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay7">
      <el-input v-model="addForm.bomUseDay7" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay8">
      <el-input v-model="addForm.bomUseDay8" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay9">
      <el-input v-model="addForm.bomUseDay9" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay10">
      <el-input v-model="addForm.bomUseDay10" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay11">
      <el-input v-model="addForm.bomUseDay11" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay12">
      <el-input v-model="addForm.bomUseDay12" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay13">
      <el-input v-model="addForm.bomUseDay13" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay14">
      <el-input v-model="addForm.bomUseDay14" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay15">
      <el-input v-model="addForm.bomUseDay15" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay16">
      <el-input v-model="addForm.bomUseDay16" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay17">
      <el-input v-model="addForm.bomUseDay17" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay18">
      <el-input v-model="addForm.bomUseDay18" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay19">
      <el-input v-model="addForm.bomUseDay19" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay20">
      <el-input v-model="addForm.bomUseDay20" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay21">
      <el-input v-model="addForm.bomUseDay21" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay22">
      <el-input v-model="addForm.bomUseDay22" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay23">
      <el-input v-model="addForm.bomUseDay23" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay24">
      <el-input v-model="addForm.bomUseDay24" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay25">
      <el-input v-model="addForm.bomUseDay25" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay26">
      <el-input v-model="addForm.bomUseDay26" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay27">
      <el-input v-model="addForm.bomUseDay27" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay28">
      <el-input v-model="addForm.bomUseDay28" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay29">
      <el-input v-model="addForm.bomUseDay29" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay30">
      <el-input v-model="addForm.bomUseDay30" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay31">
      <el-input v-model="addForm.bomUseDay31" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay32">
      <el-input v-model="addForm.bomUseDay32" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay33">
      <el-input v-model="addForm.bomUseDay33" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay34">
      <el-input v-model="addForm.bomUseDay34" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay35">
      <el-input v-model="addForm.bomUseDay35" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay36">
      <el-input v-model="addForm.bomUseDay36" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay37">
      <el-input v-model="addForm.bomUseDay37" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay38">
      <el-input v-model="addForm.bomUseDay38" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay39">
      <el-input v-model="addForm.bomUseDay39" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay40">
      <el-input v-model="addForm.bomUseDay40" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay41">
      <el-input v-model="addForm.bomUseDay41" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay42">
      <el-input v-model="addForm.bomUseDay42" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay43">
      <el-input v-model="addForm.bomUseDay43" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay44">
      <el-input v-model="addForm.bomUseDay44" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay45">
      <el-input v-model="addForm.bomUseDay45" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay46">
      <el-input v-model="addForm.bomUseDay46" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay47">
      <el-input v-model="addForm.bomUseDay47" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay48">
      <el-input v-model="addForm.bomUseDay48" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay49">
      <el-input v-model="addForm.bomUseDay49" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay50">
      <el-input v-model="addForm.bomUseDay50" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay51">
      <el-input v-model="addForm.bomUseDay51" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay52">
      <el-input v-model="addForm.bomUseDay52" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay53">
      <el-input v-model="addForm.bomUseDay53" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay54">
      <el-input v-model="addForm.bomUseDay54" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay55">
      <el-input v-model="addForm.bomUseDay55" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay56">
      <el-input v-model="addForm.bomUseDay56" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay57">
      <el-input v-model="addForm.bomUseDay57" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay58">
      <el-input v-model="addForm.bomUseDay58" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay59">
      <el-input v-model="addForm.bomUseDay59" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay60">
      <el-input v-model="addForm.bomUseDay60" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay61">
      <el-input v-model="addForm.bomUseDay61" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay62">
      <el-input v-model="addForm.bomUseDay62" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay63">
      <el-input v-model="addForm.bomUseDay63" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay64">
      <el-input v-model="addForm.bomUseDay64" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay65">
      <el-input v-model="addForm.bomUseDay65" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay66">
      <el-input v-model="addForm.bomUseDay66" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay67">
      <el-input v-model="addForm.bomUseDay67" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay68">
      <el-input v-model="addForm.bomUseDay68" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay69">
      <el-input v-model="addForm.bomUseDay69" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay70">
      <el-input v-model="addForm.bomUseDay70" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay71">
      <el-input v-model="addForm.bomUseDay71" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay72">
      <el-input v-model="addForm.bomUseDay72" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay73">
      <el-input v-model="addForm.bomUseDay73" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay74">
      <el-input v-model="addForm.bomUseDay74" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay75">
      <el-input v-model="addForm.bomUseDay75" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay76">
      <el-input v-model="addForm.bomUseDay76" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay77">
      <el-input v-model="addForm.bomUseDay77" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay78">
      <el-input v-model="addForm.bomUseDay78" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay79">
      <el-input v-model="addForm.bomUseDay79" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay80">
      <el-input v-model="addForm.bomUseDay80" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay81">
      <el-input v-model="addForm.bomUseDay81" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay82">
      <el-input v-model="addForm.bomUseDay82" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay83">
      <el-input v-model="addForm.bomUseDay83" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay84">
      <el-input v-model="addForm.bomUseDay84" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay85">
      <el-input v-model="addForm.bomUseDay85" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay86">
      <el-input v-model="addForm.bomUseDay86" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay87">
      <el-input v-model="addForm.bomUseDay87" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay88">
      <el-input v-model="addForm.bomUseDay88" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay89">
      <el-input v-model="addForm.bomUseDay89" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay90">
      <el-input v-model="addForm.bomUseDay90" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay91">
      <el-input v-model="addForm.bomUseDay91" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay92">
      <el-input v-model="addForm.bomUseDay92" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay93">
      <el-input v-model="addForm.bomUseDay93" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay94">
      <el-input v-model="addForm.bomUseDay94" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay95">
      <el-input v-model="addForm.bomUseDay95" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay96">
      <el-input v-model="addForm.bomUseDay96" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay97">
      <el-input v-model="addForm.bomUseDay97" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay98">
      <el-input v-model="addForm.bomUseDay98" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay99">
      <el-input v-model="addForm.bomUseDay99" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay100">
      <el-input v-model="addForm.bomUseDay100" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay101">
      <el-input v-model="addForm.bomUseDay101" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay102">
      <el-input v-model="addForm.bomUseDay102" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay103">
      <el-input v-model="addForm.bomUseDay103" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay104">
      <el-input v-model="addForm.bomUseDay104" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay105">
      <el-input v-model="addForm.bomUseDay105" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay106">
      <el-input v-model="addForm.bomUseDay106" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay107">
      <el-input v-model="addForm.bomUseDay107" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay108">
      <el-input v-model="addForm.bomUseDay108" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay109">
      <el-input v-model="addForm.bomUseDay109" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay110">
      <el-input v-model="addForm.bomUseDay110" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay111">
      <el-input v-model="addForm.bomUseDay111" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay112">
      <el-input v-model="addForm.bomUseDay112" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay113">
      <el-input v-model="addForm.bomUseDay113" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay114">
      <el-input v-model="addForm.bomUseDay114" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay115">
      <el-input v-model="addForm.bomUseDay115" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay116">
      <el-input v-model="addForm.bomUseDay116" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay117">
      <el-input v-model="addForm.bomUseDay117" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay118">
      <el-input v-model="addForm.bomUseDay118" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay119">
      <el-input v-model="addForm.bomUseDay119" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay120">
      <el-input v-model="addForm.bomUseDay120" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay121">
      <el-input v-model="addForm.bomUseDay121" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay122">
      <el-input v-model="addForm.bomUseDay122" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay123">
      <el-input v-model="addForm.bomUseDay123" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay124">
      <el-input v-model="addForm.bomUseDay124" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay125">
      <el-input v-model="addForm.bomUseDay125" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay126">
      <el-input v-model="addForm.bomUseDay126" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay127">
      <el-input v-model="addForm.bomUseDay127" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay128">
      <el-input v-model="addForm.bomUseDay128" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay129">
      <el-input v-model="addForm.bomUseDay129" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay130">
      <el-input v-model="addForm.bomUseDay130" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay131">
      <el-input v-model="addForm.bomUseDay131" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay132">
      <el-input v-model="addForm.bomUseDay132" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay133">
      <el-input v-model="addForm.bomUseDay133" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay134">
      <el-input v-model="addForm.bomUseDay134" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay135">
      <el-input v-model="addForm.bomUseDay135" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay136">
      <el-input v-model="addForm.bomUseDay136" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay137">
      <el-input v-model="addForm.bomUseDay137" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay138">
      <el-input v-model="addForm.bomUseDay138" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay139">
      <el-input v-model="addForm.bomUseDay139" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay140">
      <el-input v-model="addForm.bomUseDay140" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay141">
      <el-input v-model="addForm.bomUseDay141" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay142">
      <el-input v-model="addForm.bomUseDay142" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay143">
      <el-input v-model="addForm.bomUseDay143" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay144">
      <el-input v-model="addForm.bomUseDay144" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay145">
      <el-input v-model="addForm.bomUseDay145" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay146">
      <el-input v-model="addForm.bomUseDay146" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay147">
      <el-input v-model="addForm.bomUseDay147" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay148">
      <el-input v-model="addForm.bomUseDay148" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay149">
      <el-input v-model="addForm.bomUseDay149" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay150">
      <el-input v-model="addForm.bomUseDay150" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay151">
      <el-input v-model="addForm.bomUseDay151" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay152">
      <el-input v-model="addForm.bomUseDay152" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay153">
      <el-input v-model="addForm.bomUseDay153" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay154">
      <el-input v-model="addForm.bomUseDay154" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay155">
      <el-input v-model="addForm.bomUseDay155" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay156">
      <el-input v-model="addForm.bomUseDay156" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay157">
      <el-input v-model="addForm.bomUseDay157" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay158">
      <el-input v-model="addForm.bomUseDay158" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay159">
      <el-input v-model="addForm.bomUseDay159" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay160">
      <el-input v-model="addForm.bomUseDay160" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay161">
      <el-input v-model="addForm.bomUseDay161" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay162">
      <el-input v-model="addForm.bomUseDay162" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay163">
      <el-input v-model="addForm.bomUseDay163" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay164">
      <el-input v-model="addForm.bomUseDay164" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay165">
      <el-input v-model="addForm.bomUseDay165" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay166">
      <el-input v-model="addForm.bomUseDay166" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay167">
      <el-input v-model="addForm.bomUseDay167" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay168">
      <el-input v-model="addForm.bomUseDay168" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay169">
      <el-input v-model="addForm.bomUseDay169" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay170">
      <el-input v-model="addForm.bomUseDay170" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay171">
      <el-input v-model="addForm.bomUseDay171" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay172">
      <el-input v-model="addForm.bomUseDay172" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay173">
      <el-input v-model="addForm.bomUseDay173" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay174">
      <el-input v-model="addForm.bomUseDay174" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay175">
      <el-input v-model="addForm.bomUseDay175" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay176">
      <el-input v-model="addForm.bomUseDay176" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay177">
      <el-input v-model="addForm.bomUseDay177" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay178">
      <el-input v-model="addForm.bomUseDay178" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay179">
      <el-input v-model="addForm.bomUseDay179" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay180">
      <el-input v-model="addForm.bomUseDay180" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay181">
      <el-input v-model="addForm.bomUseDay181" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay182">
      <el-input v-model="addForm.bomUseDay182" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay183">
      <el-input v-model="addForm.bomUseDay183" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay184">
      <el-input v-model="addForm.bomUseDay184" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay185">
      <el-input v-model="addForm.bomUseDay185" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay186">
      <el-input v-model="addForm.bomUseDay186" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay187">
      <el-input v-model="addForm.bomUseDay187" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay188">
      <el-input v-model="addForm.bomUseDay188" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay189">
      <el-input v-model="addForm.bomUseDay189" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay190">
      <el-input v-model="addForm.bomUseDay190" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay191">
      <el-input v-model="addForm.bomUseDay191" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay192">
      <el-input v-model="addForm.bomUseDay192" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay193">
      <el-input v-model="addForm.bomUseDay193" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay194">
      <el-input v-model="addForm.bomUseDay194" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay195">
      <el-input v-model="addForm.bomUseDay195" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay196">
      <el-input v-model="addForm.bomUseDay196" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay197">
      <el-input v-model="addForm.bomUseDay197" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay198">
      <el-input v-model="addForm.bomUseDay198" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay199">
      <el-input v-model="addForm.bomUseDay199" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay200">
      <el-input v-model="addForm.bomUseDay200" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay201">
      <el-input v-model="addForm.bomUseDay201" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay202">
      <el-input v-model="addForm.bomUseDay202" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay203">
      <el-input v-model="addForm.bomUseDay203" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay204">
      <el-input v-model="addForm.bomUseDay204" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay205">
      <el-input v-model="addForm.bomUseDay205" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay206">
      <el-input v-model="addForm.bomUseDay206" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay207">
      <el-input v-model="addForm.bomUseDay207" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay208">
      <el-input v-model="addForm.bomUseDay208" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay209">
      <el-input v-model="addForm.bomUseDay209" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay210">
      <el-input v-model="addForm.bomUseDay210" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay211">
      <el-input v-model="addForm.bomUseDay211" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay212">
      <el-input v-model="addForm.bomUseDay212" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay213">
      <el-input v-model="addForm.bomUseDay213" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay214">
      <el-input v-model="addForm.bomUseDay214" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay215">
      <el-input v-model="addForm.bomUseDay215" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay216">
      <el-input v-model="addForm.bomUseDay216" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay217">
      <el-input v-model="addForm.bomUseDay217" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay218">
      <el-input v-model="addForm.bomUseDay218" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay219">
      <el-input v-model="addForm.bomUseDay219" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay220">
      <el-input v-model="addForm.bomUseDay220" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay221">
      <el-input v-model="addForm.bomUseDay221" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay222">
      <el-input v-model="addForm.bomUseDay222" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay223">
      <el-input v-model="addForm.bomUseDay223" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay224">
      <el-input v-model="addForm.bomUseDay224" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay225">
      <el-input v-model="addForm.bomUseDay225" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay226">
      <el-input v-model="addForm.bomUseDay226" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay227">
      <el-input v-model="addForm.bomUseDay227" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay228">
      <el-input v-model="addForm.bomUseDay228" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay229">
      <el-input v-model="addForm.bomUseDay229" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay230">
      <el-input v-model="addForm.bomUseDay230" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay231">
      <el-input v-model="addForm.bomUseDay231" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay232">
      <el-input v-model="addForm.bomUseDay232" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay233">
      <el-input v-model="addForm.bomUseDay233" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay234">
      <el-input v-model="addForm.bomUseDay234" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay235">
      <el-input v-model="addForm.bomUseDay235" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay236">
      <el-input v-model="addForm.bomUseDay236" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay237">
      <el-input v-model="addForm.bomUseDay237" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay238">
      <el-input v-model="addForm.bomUseDay238" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay239">
      <el-input v-model="addForm.bomUseDay239" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay240">
      <el-input v-model="addForm.bomUseDay240" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay241">
      <el-input v-model="addForm.bomUseDay241" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay242">
      <el-input v-model="addForm.bomUseDay242" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay243">
      <el-input v-model="addForm.bomUseDay243" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay244">
      <el-input v-model="addForm.bomUseDay244" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay245">
      <el-input v-model="addForm.bomUseDay245" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay246">
      <el-input v-model="addForm.bomUseDay246" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay247">
      <el-input v-model="addForm.bomUseDay247" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay248">
      <el-input v-model="addForm.bomUseDay248" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay249">
      <el-input v-model="addForm.bomUseDay249" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay250">
      <el-input v-model="addForm.bomUseDay250" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay251">
      <el-input v-model="addForm.bomUseDay251" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay252">
      <el-input v-model="addForm.bomUseDay252" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay253">
      <el-input v-model="addForm.bomUseDay253" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay254">
      <el-input v-model="addForm.bomUseDay254" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay255">
      <el-input v-model="addForm.bomUseDay255" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay256">
      <el-input v-model="addForm.bomUseDay256" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay257">
      <el-input v-model="addForm.bomUseDay257" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay258">
      <el-input v-model="addForm.bomUseDay258" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay259">
      <el-input v-model="addForm.bomUseDay259" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay260">
      <el-input v-model="addForm.bomUseDay260" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay261">
      <el-input v-model="addForm.bomUseDay261" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay262">
      <el-input v-model="addForm.bomUseDay262" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay263">
      <el-input v-model="addForm.bomUseDay263" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay264">
      <el-input v-model="addForm.bomUseDay264" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay265">
      <el-input v-model="addForm.bomUseDay265" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay266">
      <el-input v-model="addForm.bomUseDay266" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay267">
      <el-input v-model="addForm.bomUseDay267" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay268">
      <el-input v-model="addForm.bomUseDay268" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay269">
      <el-input v-model="addForm.bomUseDay269" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay270">
      <el-input v-model="addForm.bomUseDay270" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay271">
      <el-input v-model="addForm.bomUseDay271" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay272">
      <el-input v-model="addForm.bomUseDay272" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay273">
      <el-input v-model="addForm.bomUseDay273" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay274">
      <el-input v-model="addForm.bomUseDay274" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay275">
      <el-input v-model="addForm.bomUseDay275" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay276">
      <el-input v-model="addForm.bomUseDay276" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay277">
      <el-input v-model="addForm.bomUseDay277" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay278">
      <el-input v-model="addForm.bomUseDay278" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay279">
      <el-input v-model="addForm.bomUseDay279" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay280">
      <el-input v-model="addForm.bomUseDay280" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay281">
      <el-input v-model="addForm.bomUseDay281" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay282">
      <el-input v-model="addForm.bomUseDay282" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay283">
      <el-input v-model="addForm.bomUseDay283" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay284">
      <el-input v-model="addForm.bomUseDay284" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay285">
      <el-input v-model="addForm.bomUseDay285" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay286">
      <el-input v-model="addForm.bomUseDay286" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay287">
      <el-input v-model="addForm.bomUseDay287" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay288">
      <el-input v-model="addForm.bomUseDay288" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay289">
      <el-input v-model="addForm.bomUseDay289" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay290">
      <el-input v-model="addForm.bomUseDay290" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay291">
      <el-input v-model="addForm.bomUseDay291" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay292">
      <el-input v-model="addForm.bomUseDay292" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay293">
      <el-input v-model="addForm.bomUseDay293" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay294">
      <el-input v-model="addForm.bomUseDay294" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay295">
      <el-input v-model="addForm.bomUseDay295" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay296">
      <el-input v-model="addForm.bomUseDay296" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay297">
      <el-input v-model="addForm.bomUseDay297" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay298">
      <el-input v-model="addForm.bomUseDay298" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay299">
      <el-input v-model="addForm.bomUseDay299" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay300">
      <el-input v-model="addForm.bomUseDay300" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay301">
      <el-input v-model="addForm.bomUseDay301" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay302">
      <el-input v-model="addForm.bomUseDay302" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay303">
      <el-input v-model="addForm.bomUseDay303" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay304">
      <el-input v-model="addForm.bomUseDay304" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay305">
      <el-input v-model="addForm.bomUseDay305" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay306">
      <el-input v-model="addForm.bomUseDay306" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay307">
      <el-input v-model="addForm.bomUseDay307" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay308">
      <el-input v-model="addForm.bomUseDay308" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay309">
      <el-input v-model="addForm.bomUseDay309" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay310">
      <el-input v-model="addForm.bomUseDay310" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay311">
      <el-input v-model="addForm.bomUseDay311" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay312">
      <el-input v-model="addForm.bomUseDay312" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay313">
      <el-input v-model="addForm.bomUseDay313" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay314">
      <el-input v-model="addForm.bomUseDay314" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay315">
      <el-input v-model="addForm.bomUseDay315" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay316">
      <el-input v-model="addForm.bomUseDay316" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay317">
      <el-input v-model="addForm.bomUseDay317" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay318">
      <el-input v-model="addForm.bomUseDay318" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay319">
      <el-input v-model="addForm.bomUseDay319" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay320">
      <el-input v-model="addForm.bomUseDay320" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay321">
      <el-input v-model="addForm.bomUseDay321" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay322">
      <el-input v-model="addForm.bomUseDay322" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay323">
      <el-input v-model="addForm.bomUseDay323" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay324">
      <el-input v-model="addForm.bomUseDay324" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay325">
      <el-input v-model="addForm.bomUseDay325" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay326">
      <el-input v-model="addForm.bomUseDay326" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay327">
      <el-input v-model="addForm.bomUseDay327" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay328">
      <el-input v-model="addForm.bomUseDay328" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay329">
      <el-input v-model="addForm.bomUseDay329" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay330">
      <el-input v-model="addForm.bomUseDay330" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay331">
      <el-input v-model="addForm.bomUseDay331" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay332">
      <el-input v-model="addForm.bomUseDay332" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay333">
      <el-input v-model="addForm.bomUseDay333" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay334">
      <el-input v-model="addForm.bomUseDay334" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay335">
      <el-input v-model="addForm.bomUseDay335" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay336">
      <el-input v-model="addForm.bomUseDay336" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay337">
      <el-input v-model="addForm.bomUseDay337" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay338">
      <el-input v-model="addForm.bomUseDay338" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay339">
      <el-input v-model="addForm.bomUseDay339" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay340">
      <el-input v-model="addForm.bomUseDay340" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay341">
      <el-input v-model="addForm.bomUseDay341" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay342">
      <el-input v-model="addForm.bomUseDay342" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay343">
      <el-input v-model="addForm.bomUseDay343" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay344">
      <el-input v-model="addForm.bomUseDay344" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay345">
      <el-input v-model="addForm.bomUseDay345" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay346">
      <el-input v-model="addForm.bomUseDay346" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay347">
      <el-input v-model="addForm.bomUseDay347" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay348">
      <el-input v-model="addForm.bomUseDay348" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay349">
      <el-input v-model="addForm.bomUseDay349" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay350">
      <el-input v-model="addForm.bomUseDay350" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay351">
      <el-input v-model="addForm.bomUseDay351" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay352">
      <el-input v-model="addForm.bomUseDay352" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay353">
      <el-input v-model="addForm.bomUseDay353" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay354">
      <el-input v-model="addForm.bomUseDay354" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay355">
      <el-input v-model="addForm.bomUseDay355" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay356">
      <el-input v-model="addForm.bomUseDay356" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay357">
      <el-input v-model="addForm.bomUseDay357" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay358">
      <el-input v-model="addForm.bomUseDay358" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay359">
      <el-input v-model="addForm.bomUseDay359" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay360">
      <el-input v-model="addForm.bomUseDay360" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay361">
      <el-input v-model="addForm.bomUseDay361" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay362">
      <el-input v-model="addForm.bomUseDay362" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay363">
      <el-input v-model="addForm.bomUseDay363" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay364">
      <el-input v-model="addForm.bomUseDay364" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay365">
      <el-input v-model="addForm.bomUseDay365" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay366">
      <el-input v-model="addForm.bomUseDay366" clearable placeholder="请输入使用信息 {lack:1, quantity:3}"/>
    </el-form-item>
    <el-form-item label="工厂" prop="factoryId">
      <el-input v-model="addForm.factoryId" clearable placeholder="请输入工厂ID"/>
    </el-form-item>
    <el-form-item label="是否关注" prop="isFollow">
      <el-input v-model="addForm.isFollow" clearable placeholder="请输入是否关注"/>
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

