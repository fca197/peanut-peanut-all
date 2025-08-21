<script setup lang="ts">
import {ref, onMounted} from "vue"
import AddEditFormVue from "./ApsGoodsBomBuyPlanItemAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { ElTable } from "element-plus"
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {type ApsGoodsBomBuyPlanItem} from "./ApsGoodsBomBuyPlanItemType.ts"

const dtoUrl = ref<string>("/apsGoodsBomBuyPlanItem")
const documentTitle = ref<string>("订单商品零件汇总")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsGoodsBomBuyPlanItem>({
  buyPlanId: undefined,
  year: undefined,
  bomId: undefined,
  goodsBomId: undefined,
  bomCode: undefined,
  bomName: undefined,
  bomUsage: undefined,
  bomUnit: undefined,
  bomCostPrice: undefined,
  bomCostPriceUnit: undefined,
  bomUseWorkStation: undefined,
  bomUseDay1: undefined,
  bomUseDay2: undefined,
  bomUseDay3: undefined,
  bomUseDay4: undefined,
  bomUseDay5: undefined,
  bomUseDay6: undefined,
  bomUseDay7: undefined,
  bomUseDay8: undefined,
  bomUseDay9: undefined,
  bomUseDay10: undefined,
  bomUseDay11: undefined,
  bomUseDay12: undefined,
  bomUseDay13: undefined,
  bomUseDay14: undefined,
  bomUseDay15: undefined,
  bomUseDay16: undefined,
  bomUseDay17: undefined,
  bomUseDay18: undefined,
  bomUseDay19: undefined,
  bomUseDay20: undefined,
  bomUseDay21: undefined,
  bomUseDay22: undefined,
  bomUseDay23: undefined,
  bomUseDay24: undefined,
  bomUseDay25: undefined,
  bomUseDay26: undefined,
  bomUseDay27: undefined,
  bomUseDay28: undefined,
  bomUseDay29: undefined,
  bomUseDay30: undefined,
  bomUseDay31: undefined,
  bomUseDay32: undefined,
  bomUseDay33: undefined,
  bomUseDay34: undefined,
  bomUseDay35: undefined,
  bomUseDay36: undefined,
  bomUseDay37: undefined,
  bomUseDay38: undefined,
  bomUseDay39: undefined,
  bomUseDay40: undefined,
  bomUseDay41: undefined,
  bomUseDay42: undefined,
  bomUseDay43: undefined,
  bomUseDay44: undefined,
  bomUseDay45: undefined,
  bomUseDay46: undefined,
  bomUseDay47: undefined,
  bomUseDay48: undefined,
  bomUseDay49: undefined,
  bomUseDay50: undefined,
  bomUseDay51: undefined,
  bomUseDay52: undefined,
  bomUseDay53: undefined,
  bomUseDay54: undefined,
  bomUseDay55: undefined,
  bomUseDay56: undefined,
  bomUseDay57: undefined,
  bomUseDay58: undefined,
  bomUseDay59: undefined,
  bomUseDay60: undefined,
  bomUseDay61: undefined,
  bomUseDay62: undefined,
  bomUseDay63: undefined,
  bomUseDay64: undefined,
  bomUseDay65: undefined,
  bomUseDay66: undefined,
  bomUseDay67: undefined,
  bomUseDay68: undefined,
  bomUseDay69: undefined,
  bomUseDay70: undefined,
  bomUseDay71: undefined,
  bomUseDay72: undefined,
  bomUseDay73: undefined,
  bomUseDay74: undefined,
  bomUseDay75: undefined,
  bomUseDay76: undefined,
  bomUseDay77: undefined,
  bomUseDay78: undefined,
  bomUseDay79: undefined,
  bomUseDay80: undefined,
  bomUseDay81: undefined,
  bomUseDay82: undefined,
  bomUseDay83: undefined,
  bomUseDay84: undefined,
  bomUseDay85: undefined,
  bomUseDay86: undefined,
  bomUseDay87: undefined,
  bomUseDay88: undefined,
  bomUseDay89: undefined,
  bomUseDay90: undefined,
  bomUseDay91: undefined,
  bomUseDay92: undefined,
  bomUseDay93: undefined,
  bomUseDay94: undefined,
  bomUseDay95: undefined,
  bomUseDay96: undefined,
  bomUseDay97: undefined,
  bomUseDay98: undefined,
  bomUseDay99: undefined,
  bomUseDay100: undefined,
  bomUseDay101: undefined,
  bomUseDay102: undefined,
  bomUseDay103: undefined,
  bomUseDay104: undefined,
  bomUseDay105: undefined,
  bomUseDay106: undefined,
  bomUseDay107: undefined,
  bomUseDay108: undefined,
  bomUseDay109: undefined,
  bomUseDay110: undefined,
  bomUseDay111: undefined,
  bomUseDay112: undefined,
  bomUseDay113: undefined,
  bomUseDay114: undefined,
  bomUseDay115: undefined,
  bomUseDay116: undefined,
  bomUseDay117: undefined,
  bomUseDay118: undefined,
  bomUseDay119: undefined,
  bomUseDay120: undefined,
  bomUseDay121: undefined,
  bomUseDay122: undefined,
  bomUseDay123: undefined,
  bomUseDay124: undefined,
  bomUseDay125: undefined,
  bomUseDay126: undefined,
  bomUseDay127: undefined,
  bomUseDay128: undefined,
  bomUseDay129: undefined,
  bomUseDay130: undefined,
  bomUseDay131: undefined,
  bomUseDay132: undefined,
  bomUseDay133: undefined,
  bomUseDay134: undefined,
  bomUseDay135: undefined,
  bomUseDay136: undefined,
  bomUseDay137: undefined,
  bomUseDay138: undefined,
  bomUseDay139: undefined,
  bomUseDay140: undefined,
  bomUseDay141: undefined,
  bomUseDay142: undefined,
  bomUseDay143: undefined,
  bomUseDay144: undefined,
  bomUseDay145: undefined,
  bomUseDay146: undefined,
  bomUseDay147: undefined,
  bomUseDay148: undefined,
  bomUseDay149: undefined,
  bomUseDay150: undefined,
  bomUseDay151: undefined,
  bomUseDay152: undefined,
  bomUseDay153: undefined,
  bomUseDay154: undefined,
  bomUseDay155: undefined,
  bomUseDay156: undefined,
  bomUseDay157: undefined,
  bomUseDay158: undefined,
  bomUseDay159: undefined,
  bomUseDay160: undefined,
  bomUseDay161: undefined,
  bomUseDay162: undefined,
  bomUseDay163: undefined,
  bomUseDay164: undefined,
  bomUseDay165: undefined,
  bomUseDay166: undefined,
  bomUseDay167: undefined,
  bomUseDay168: undefined,
  bomUseDay169: undefined,
  bomUseDay170: undefined,
  bomUseDay171: undefined,
  bomUseDay172: undefined,
  bomUseDay173: undefined,
  bomUseDay174: undefined,
  bomUseDay175: undefined,
  bomUseDay176: undefined,
  bomUseDay177: undefined,
  bomUseDay178: undefined,
  bomUseDay179: undefined,
  bomUseDay180: undefined,
  bomUseDay181: undefined,
  bomUseDay182: undefined,
  bomUseDay183: undefined,
  bomUseDay184: undefined,
  bomUseDay185: undefined,
  bomUseDay186: undefined,
  bomUseDay187: undefined,
  bomUseDay188: undefined,
  bomUseDay189: undefined,
  bomUseDay190: undefined,
  bomUseDay191: undefined,
  bomUseDay192: undefined,
  bomUseDay193: undefined,
  bomUseDay194: undefined,
  bomUseDay195: undefined,
  bomUseDay196: undefined,
  bomUseDay197: undefined,
  bomUseDay198: undefined,
  bomUseDay199: undefined,
  bomUseDay200: undefined,
  bomUseDay201: undefined,
  bomUseDay202: undefined,
  bomUseDay203: undefined,
  bomUseDay204: undefined,
  bomUseDay205: undefined,
  bomUseDay206: undefined,
  bomUseDay207: undefined,
  bomUseDay208: undefined,
  bomUseDay209: undefined,
  bomUseDay210: undefined,
  bomUseDay211: undefined,
  bomUseDay212: undefined,
  bomUseDay213: undefined,
  bomUseDay214: undefined,
  bomUseDay215: undefined,
  bomUseDay216: undefined,
  bomUseDay217: undefined,
  bomUseDay218: undefined,
  bomUseDay219: undefined,
  bomUseDay220: undefined,
  bomUseDay221: undefined,
  bomUseDay222: undefined,
  bomUseDay223: undefined,
  bomUseDay224: undefined,
  bomUseDay225: undefined,
  bomUseDay226: undefined,
  bomUseDay227: undefined,
  bomUseDay228: undefined,
  bomUseDay229: undefined,
  bomUseDay230: undefined,
  bomUseDay231: undefined,
  bomUseDay232: undefined,
  bomUseDay233: undefined,
  bomUseDay234: undefined,
  bomUseDay235: undefined,
  bomUseDay236: undefined,
  bomUseDay237: undefined,
  bomUseDay238: undefined,
  bomUseDay239: undefined,
  bomUseDay240: undefined,
  bomUseDay241: undefined,
  bomUseDay242: undefined,
  bomUseDay243: undefined,
  bomUseDay244: undefined,
  bomUseDay245: undefined,
  bomUseDay246: undefined,
  bomUseDay247: undefined,
  bomUseDay248: undefined,
  bomUseDay249: undefined,
  bomUseDay250: undefined,
  bomUseDay251: undefined,
  bomUseDay252: undefined,
  bomUseDay253: undefined,
  bomUseDay254: undefined,
  bomUseDay255: undefined,
  bomUseDay256: undefined,
  bomUseDay257: undefined,
  bomUseDay258: undefined,
  bomUseDay259: undefined,
  bomUseDay260: undefined,
  bomUseDay261: undefined,
  bomUseDay262: undefined,
  bomUseDay263: undefined,
  bomUseDay264: undefined,
  bomUseDay265: undefined,
  bomUseDay266: undefined,
  bomUseDay267: undefined,
  bomUseDay268: undefined,
  bomUseDay269: undefined,
  bomUseDay270: undefined,
  bomUseDay271: undefined,
  bomUseDay272: undefined,
  bomUseDay273: undefined,
  bomUseDay274: undefined,
  bomUseDay275: undefined,
  bomUseDay276: undefined,
  bomUseDay277: undefined,
  bomUseDay278: undefined,
  bomUseDay279: undefined,
  bomUseDay280: undefined,
  bomUseDay281: undefined,
  bomUseDay282: undefined,
  bomUseDay283: undefined,
  bomUseDay284: undefined,
  bomUseDay285: undefined,
  bomUseDay286: undefined,
  bomUseDay287: undefined,
  bomUseDay288: undefined,
  bomUseDay289: undefined,
  bomUseDay290: undefined,
  bomUseDay291: undefined,
  bomUseDay292: undefined,
  bomUseDay293: undefined,
  bomUseDay294: undefined,
  bomUseDay295: undefined,
  bomUseDay296: undefined,
  bomUseDay297: undefined,
  bomUseDay298: undefined,
  bomUseDay299: undefined,
  bomUseDay300: undefined,
  bomUseDay301: undefined,
  bomUseDay302: undefined,
  bomUseDay303: undefined,
  bomUseDay304: undefined,
  bomUseDay305: undefined,
  bomUseDay306: undefined,
  bomUseDay307: undefined,
  bomUseDay308: undefined,
  bomUseDay309: undefined,
  bomUseDay310: undefined,
  bomUseDay311: undefined,
  bomUseDay312: undefined,
  bomUseDay313: undefined,
  bomUseDay314: undefined,
  bomUseDay315: undefined,
  bomUseDay316: undefined,
  bomUseDay317: undefined,
  bomUseDay318: undefined,
  bomUseDay319: undefined,
  bomUseDay320: undefined,
  bomUseDay321: undefined,
  bomUseDay322: undefined,
  bomUseDay323: undefined,
  bomUseDay324: undefined,
  bomUseDay325: undefined,
  bomUseDay326: undefined,
  bomUseDay327: undefined,
  bomUseDay328: undefined,
  bomUseDay329: undefined,
  bomUseDay330: undefined,
  bomUseDay331: undefined,
  bomUseDay332: undefined,
  bomUseDay333: undefined,
  bomUseDay334: undefined,
  bomUseDay335: undefined,
  bomUseDay336: undefined,
  bomUseDay337: undefined,
  bomUseDay338: undefined,
  bomUseDay339: undefined,
  bomUseDay340: undefined,
  bomUseDay341: undefined,
  bomUseDay342: undefined,
  bomUseDay343: undefined,
  bomUseDay344: undefined,
  bomUseDay345: undefined,
  bomUseDay346: undefined,
  bomUseDay347: undefined,
  bomUseDay348: undefined,
  bomUseDay349: undefined,
  bomUseDay350: undefined,
  bomUseDay351: undefined,
  bomUseDay352: undefined,
  bomUseDay353: undefined,
  bomUseDay354: undefined,
  bomUseDay355: undefined,
  bomUseDay356: undefined,
  bomUseDay357: undefined,
  bomUseDay358: undefined,
  bomUseDay359: undefined,
  bomUseDay360: undefined,
  bomUseDay361: undefined,
  bomUseDay362: undefined,
  bomUseDay363: undefined,
  bomUseDay364: undefined,
  bomUseDay365: undefined,
  bomUseDay366: undefined,
  factoryId: undefined,
  isFollow: undefined,
  id: undefined
})

// 表格选中的id
const multipleSelection = ref<(string | undefined) []>([])

// 表格
// const dataTableRef = ref<InstanceType<typeof ElTable> | null>(null)
const dataTableRef = ref({})
// 表格操作头
const tableBarRef = ref<InstanceType<typeof TableBar> | null>(null)
// 表格相关
const dataList = ref<ApsGoodsBomBuyPlanItem[] >([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
 { fieldName: "id", showName: "序号" },
  { fieldName: "buyPlanId", showName: "购买计划ID" },
  { fieldName: "year", showName: "年" },
  { fieldName: "bomId", showName: "零件ID" },
  { fieldName: "goodsBomId", showName: "商品零件ID" },
  { fieldName: "bomCode", showName: "bom 编码" },
  { fieldName: "bomName", showName: "bom 名称" },
  { fieldName: "bomUsage", showName: "使用量" },
  { fieldName: "bomUnit", showName: "规格" },
  { fieldName: "bomCostPrice", showName: "成本价" },
  { fieldName: "bomCostPriceUnit", showName: "规格" },
  { fieldName: "bomUseWorkStation", showName: "使用工位" },
  { fieldName: "bomUseDay1", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay2", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay3", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay4", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay5", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay6", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay7", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay8", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay9", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay10", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay11", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay12", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay13", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay14", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay15", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay16", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay17", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay18", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay19", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay20", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay21", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay22", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay23", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay24", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay25", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay26", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay27", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay28", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay29", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay30", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay31", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay32", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay33", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay34", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay35", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay36", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay37", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay38", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay39", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay40", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay41", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay42", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay43", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay44", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay45", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay46", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay47", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay48", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay49", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay50", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay51", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay52", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay53", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay54", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay55", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay56", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay57", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay58", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay59", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay60", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay61", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay62", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay63", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay64", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay65", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay66", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay67", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay68", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay69", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay70", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay71", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay72", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay73", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay74", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay75", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay76", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay77", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay78", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay79", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay80", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay81", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay82", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay83", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay84", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay85", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay86", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay87", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay88", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay89", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay90", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay91", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay92", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay93", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay94", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay95", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay96", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay97", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay98", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay99", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay100", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay101", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay102", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay103", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay104", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay105", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay106", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay107", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay108", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay109", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay110", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay111", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay112", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay113", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay114", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay115", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay116", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay117", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay118", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay119", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay120", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay121", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay122", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay123", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay124", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay125", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay126", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay127", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay128", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay129", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay130", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay131", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay132", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay133", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay134", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay135", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay136", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay137", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay138", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay139", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay140", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay141", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay142", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay143", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay144", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay145", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay146", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay147", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay148", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay149", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay150", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay151", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay152", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay153", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay154", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay155", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay156", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay157", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay158", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay159", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay160", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay161", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay162", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay163", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay164", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay165", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay166", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay167", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay168", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay169", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay170", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay171", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay172", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay173", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay174", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay175", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay176", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay177", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay178", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay179", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay180", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay181", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay182", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay183", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay184", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay185", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay186", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay187", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay188", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay189", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay190", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay191", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay192", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay193", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay194", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay195", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay196", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay197", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay198", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay199", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay200", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay201", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay202", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay203", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay204", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay205", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay206", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay207", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay208", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay209", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay210", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay211", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay212", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay213", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay214", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay215", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay216", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay217", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay218", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay219", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay220", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay221", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay222", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay223", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay224", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay225", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay226", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay227", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay228", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay229", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay230", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay231", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay232", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay233", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay234", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay235", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay236", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay237", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay238", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay239", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay240", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay241", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay242", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay243", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay244", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay245", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay246", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay247", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay248", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay249", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay250", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay251", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay252", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay253", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay254", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay255", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay256", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay257", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay258", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay259", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay260", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay261", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay262", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay263", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay264", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay265", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay266", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay267", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay268", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay269", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay270", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay271", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay272", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay273", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay274", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay275", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay276", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay277", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay278", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay279", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay280", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay281", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay282", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay283", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay284", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay285", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay286", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay287", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay288", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay289", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay290", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay291", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay292", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay293", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay294", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay295", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay296", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay297", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay298", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay299", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay300", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay301", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay302", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay303", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay304", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay305", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay306", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay307", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay308", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay309", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay310", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay311", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay312", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay313", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay314", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay315", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay316", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay317", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay318", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay319", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay320", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay321", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay322", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay323", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay324", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay325", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay326", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay327", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay328", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay329", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay330", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay331", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay332", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay333", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay334", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay335", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay336", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay337", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay338", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay339", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay340", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay341", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay342", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay343", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay344", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay345", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay346", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay347", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay348", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay349", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay350", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay351", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay352", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay353", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay354", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay355", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay356", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay357", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay358", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay359", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay360", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay361", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay362", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay363", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay364", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay365", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "bomUseDay366", showName: "使用信息 {lack:1, quantity:3}" },
  { fieldName: "factoryId", showName: "工厂" },
  { fieldName: "isFollow", showName: "是否关注" },
])


// 获取表格内数据
function getDataList() {
  const req = {
    pageSize: currentPageSize.value,
    pageNum: currentPageNum.value,
    data: queryForm.value
  }
  console.info("getDataList {}", req)
  postResultInfo(`${dtoUrl.value}/queryPageList`, req)
    .then((t) => {
      dataList.value = t.data.dataList
      tableTotal.value = Number.parseInt(t.data.total)
      headerList.value = t.data.headerList
    })
}

// table点击事件
function editData(data: any) {
  // console.info("data ", data)
  tableBarRef.value?.showEditDialog(data.id)
}
// 页面条数变更事件
function handleSizeChange(val: number) {
  currentPageSize.value = val
  getDataList()
}
// 页面变更事件
function handleCurrentChange(val: number) {
  currentPageNum.value = val
  getDataList()
}
// 表格选中事件
function handleSelectionChange(val: ApsGoodsBomBuyPlanItem[]) {
  multipleSelection.value = val.map(t => t.id)
  console.info("multipleSelection ", multipleSelection)
}

// 页面加载事件
onMounted(() => {
  getDataList()
})

</script>

<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-form v-model="queryForm" inline>
        <el-form-item label="购买计划ID" prop="buyPlanId">
          <el-input v-model="queryForm.buyPlanId" clearable placeholder="请输入购买计划ID" />
        </el-form-item>
        <el-form-item label="年" prop="year">
          <el-input v-model="queryForm.year" clearable placeholder="请输入年" />
        </el-form-item>
        <el-form-item label="零件ID" prop="bomId">
          <el-input v-model="queryForm.bomId" clearable placeholder="请输入零件ID" />
        </el-form-item>
        <el-form-item label="商品零件ID" prop="goodsBomId">
          <el-input v-model="queryForm.goodsBomId" clearable placeholder="请输入商品零件ID" />
        </el-form-item>
        <el-form-item label="bom 编码" prop="bomCode">
          <el-input v-model="queryForm.bomCode" clearable placeholder="请输入bom 编码" />
        </el-form-item>
        <el-form-item label="bom 名称" prop="bomName">
          <el-input v-model="queryForm.bomName" clearable placeholder="请输入bom 名称" />
        </el-form-item>
        <el-form-item label="使用量" prop="bomUsage">
          <el-input v-model="queryForm.bomUsage" clearable placeholder="请输入使用量" />
        </el-form-item>
        <el-form-item label="规格" prop="bomUnit">
          <el-input v-model="queryForm.bomUnit" clearable placeholder="请输入规格" />
        </el-form-item>
        <el-form-item label="成本价" prop="bomCostPrice">
          <el-input v-model="queryForm.bomCostPrice" clearable placeholder="请输入成本价" />
        </el-form-item>
        <el-form-item label="规格" prop="bomCostPriceUnit">
          <el-input v-model="queryForm.bomCostPriceUnit" clearable placeholder="请输入规格" />
        </el-form-item>
        <el-form-item label="使用工位" prop="bomUseWorkStation">
          <el-input v-model="queryForm.bomUseWorkStation" clearable placeholder="请输入使用工位" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay1">
          <el-input v-model="queryForm.bomUseDay1" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay2">
          <el-input v-model="queryForm.bomUseDay2" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay3">
          <el-input v-model="queryForm.bomUseDay3" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay4">
          <el-input v-model="queryForm.bomUseDay4" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay5">
          <el-input v-model="queryForm.bomUseDay5" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay6">
          <el-input v-model="queryForm.bomUseDay6" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay7">
          <el-input v-model="queryForm.bomUseDay7" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay8">
          <el-input v-model="queryForm.bomUseDay8" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay9">
          <el-input v-model="queryForm.bomUseDay9" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay10">
          <el-input v-model="queryForm.bomUseDay10" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay11">
          <el-input v-model="queryForm.bomUseDay11" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay12">
          <el-input v-model="queryForm.bomUseDay12" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay13">
          <el-input v-model="queryForm.bomUseDay13" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay14">
          <el-input v-model="queryForm.bomUseDay14" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay15">
          <el-input v-model="queryForm.bomUseDay15" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay16">
          <el-input v-model="queryForm.bomUseDay16" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay17">
          <el-input v-model="queryForm.bomUseDay17" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay18">
          <el-input v-model="queryForm.bomUseDay18" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay19">
          <el-input v-model="queryForm.bomUseDay19" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay20">
          <el-input v-model="queryForm.bomUseDay20" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay21">
          <el-input v-model="queryForm.bomUseDay21" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay22">
          <el-input v-model="queryForm.bomUseDay22" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay23">
          <el-input v-model="queryForm.bomUseDay23" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay24">
          <el-input v-model="queryForm.bomUseDay24" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay25">
          <el-input v-model="queryForm.bomUseDay25" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay26">
          <el-input v-model="queryForm.bomUseDay26" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay27">
          <el-input v-model="queryForm.bomUseDay27" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay28">
          <el-input v-model="queryForm.bomUseDay28" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay29">
          <el-input v-model="queryForm.bomUseDay29" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay30">
          <el-input v-model="queryForm.bomUseDay30" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay31">
          <el-input v-model="queryForm.bomUseDay31" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay32">
          <el-input v-model="queryForm.bomUseDay32" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay33">
          <el-input v-model="queryForm.bomUseDay33" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay34">
          <el-input v-model="queryForm.bomUseDay34" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay35">
          <el-input v-model="queryForm.bomUseDay35" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay36">
          <el-input v-model="queryForm.bomUseDay36" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay37">
          <el-input v-model="queryForm.bomUseDay37" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay38">
          <el-input v-model="queryForm.bomUseDay38" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay39">
          <el-input v-model="queryForm.bomUseDay39" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay40">
          <el-input v-model="queryForm.bomUseDay40" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay41">
          <el-input v-model="queryForm.bomUseDay41" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay42">
          <el-input v-model="queryForm.bomUseDay42" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay43">
          <el-input v-model="queryForm.bomUseDay43" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay44">
          <el-input v-model="queryForm.bomUseDay44" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay45">
          <el-input v-model="queryForm.bomUseDay45" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay46">
          <el-input v-model="queryForm.bomUseDay46" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay47">
          <el-input v-model="queryForm.bomUseDay47" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay48">
          <el-input v-model="queryForm.bomUseDay48" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay49">
          <el-input v-model="queryForm.bomUseDay49" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay50">
          <el-input v-model="queryForm.bomUseDay50" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay51">
          <el-input v-model="queryForm.bomUseDay51" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay52">
          <el-input v-model="queryForm.bomUseDay52" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay53">
          <el-input v-model="queryForm.bomUseDay53" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay54">
          <el-input v-model="queryForm.bomUseDay54" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay55">
          <el-input v-model="queryForm.bomUseDay55" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay56">
          <el-input v-model="queryForm.bomUseDay56" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay57">
          <el-input v-model="queryForm.bomUseDay57" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay58">
          <el-input v-model="queryForm.bomUseDay58" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay59">
          <el-input v-model="queryForm.bomUseDay59" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay60">
          <el-input v-model="queryForm.bomUseDay60" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay61">
          <el-input v-model="queryForm.bomUseDay61" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay62">
          <el-input v-model="queryForm.bomUseDay62" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay63">
          <el-input v-model="queryForm.bomUseDay63" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay64">
          <el-input v-model="queryForm.bomUseDay64" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay65">
          <el-input v-model="queryForm.bomUseDay65" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay66">
          <el-input v-model="queryForm.bomUseDay66" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay67">
          <el-input v-model="queryForm.bomUseDay67" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay68">
          <el-input v-model="queryForm.bomUseDay68" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay69">
          <el-input v-model="queryForm.bomUseDay69" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay70">
          <el-input v-model="queryForm.bomUseDay70" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay71">
          <el-input v-model="queryForm.bomUseDay71" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay72">
          <el-input v-model="queryForm.bomUseDay72" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay73">
          <el-input v-model="queryForm.bomUseDay73" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay74">
          <el-input v-model="queryForm.bomUseDay74" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay75">
          <el-input v-model="queryForm.bomUseDay75" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay76">
          <el-input v-model="queryForm.bomUseDay76" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay77">
          <el-input v-model="queryForm.bomUseDay77" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay78">
          <el-input v-model="queryForm.bomUseDay78" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay79">
          <el-input v-model="queryForm.bomUseDay79" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay80">
          <el-input v-model="queryForm.bomUseDay80" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay81">
          <el-input v-model="queryForm.bomUseDay81" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay82">
          <el-input v-model="queryForm.bomUseDay82" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay83">
          <el-input v-model="queryForm.bomUseDay83" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay84">
          <el-input v-model="queryForm.bomUseDay84" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay85">
          <el-input v-model="queryForm.bomUseDay85" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay86">
          <el-input v-model="queryForm.bomUseDay86" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay87">
          <el-input v-model="queryForm.bomUseDay87" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay88">
          <el-input v-model="queryForm.bomUseDay88" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay89">
          <el-input v-model="queryForm.bomUseDay89" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay90">
          <el-input v-model="queryForm.bomUseDay90" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay91">
          <el-input v-model="queryForm.bomUseDay91" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay92">
          <el-input v-model="queryForm.bomUseDay92" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay93">
          <el-input v-model="queryForm.bomUseDay93" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay94">
          <el-input v-model="queryForm.bomUseDay94" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay95">
          <el-input v-model="queryForm.bomUseDay95" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay96">
          <el-input v-model="queryForm.bomUseDay96" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay97">
          <el-input v-model="queryForm.bomUseDay97" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay98">
          <el-input v-model="queryForm.bomUseDay98" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay99">
          <el-input v-model="queryForm.bomUseDay99" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay100">
          <el-input v-model="queryForm.bomUseDay100" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay101">
          <el-input v-model="queryForm.bomUseDay101" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay102">
          <el-input v-model="queryForm.bomUseDay102" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay103">
          <el-input v-model="queryForm.bomUseDay103" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay104">
          <el-input v-model="queryForm.bomUseDay104" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay105">
          <el-input v-model="queryForm.bomUseDay105" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay106">
          <el-input v-model="queryForm.bomUseDay106" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay107">
          <el-input v-model="queryForm.bomUseDay107" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay108">
          <el-input v-model="queryForm.bomUseDay108" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay109">
          <el-input v-model="queryForm.bomUseDay109" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay110">
          <el-input v-model="queryForm.bomUseDay110" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay111">
          <el-input v-model="queryForm.bomUseDay111" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay112">
          <el-input v-model="queryForm.bomUseDay112" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay113">
          <el-input v-model="queryForm.bomUseDay113" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay114">
          <el-input v-model="queryForm.bomUseDay114" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay115">
          <el-input v-model="queryForm.bomUseDay115" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay116">
          <el-input v-model="queryForm.bomUseDay116" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay117">
          <el-input v-model="queryForm.bomUseDay117" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay118">
          <el-input v-model="queryForm.bomUseDay118" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay119">
          <el-input v-model="queryForm.bomUseDay119" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay120">
          <el-input v-model="queryForm.bomUseDay120" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay121">
          <el-input v-model="queryForm.bomUseDay121" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay122">
          <el-input v-model="queryForm.bomUseDay122" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay123">
          <el-input v-model="queryForm.bomUseDay123" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay124">
          <el-input v-model="queryForm.bomUseDay124" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay125">
          <el-input v-model="queryForm.bomUseDay125" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay126">
          <el-input v-model="queryForm.bomUseDay126" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay127">
          <el-input v-model="queryForm.bomUseDay127" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay128">
          <el-input v-model="queryForm.bomUseDay128" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay129">
          <el-input v-model="queryForm.bomUseDay129" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay130">
          <el-input v-model="queryForm.bomUseDay130" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay131">
          <el-input v-model="queryForm.bomUseDay131" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay132">
          <el-input v-model="queryForm.bomUseDay132" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay133">
          <el-input v-model="queryForm.bomUseDay133" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay134">
          <el-input v-model="queryForm.bomUseDay134" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay135">
          <el-input v-model="queryForm.bomUseDay135" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay136">
          <el-input v-model="queryForm.bomUseDay136" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay137">
          <el-input v-model="queryForm.bomUseDay137" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay138">
          <el-input v-model="queryForm.bomUseDay138" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay139">
          <el-input v-model="queryForm.bomUseDay139" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay140">
          <el-input v-model="queryForm.bomUseDay140" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay141">
          <el-input v-model="queryForm.bomUseDay141" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay142">
          <el-input v-model="queryForm.bomUseDay142" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay143">
          <el-input v-model="queryForm.bomUseDay143" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay144">
          <el-input v-model="queryForm.bomUseDay144" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay145">
          <el-input v-model="queryForm.bomUseDay145" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay146">
          <el-input v-model="queryForm.bomUseDay146" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay147">
          <el-input v-model="queryForm.bomUseDay147" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay148">
          <el-input v-model="queryForm.bomUseDay148" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay149">
          <el-input v-model="queryForm.bomUseDay149" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay150">
          <el-input v-model="queryForm.bomUseDay150" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay151">
          <el-input v-model="queryForm.bomUseDay151" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay152">
          <el-input v-model="queryForm.bomUseDay152" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay153">
          <el-input v-model="queryForm.bomUseDay153" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay154">
          <el-input v-model="queryForm.bomUseDay154" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay155">
          <el-input v-model="queryForm.bomUseDay155" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay156">
          <el-input v-model="queryForm.bomUseDay156" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay157">
          <el-input v-model="queryForm.bomUseDay157" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay158">
          <el-input v-model="queryForm.bomUseDay158" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay159">
          <el-input v-model="queryForm.bomUseDay159" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay160">
          <el-input v-model="queryForm.bomUseDay160" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay161">
          <el-input v-model="queryForm.bomUseDay161" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay162">
          <el-input v-model="queryForm.bomUseDay162" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay163">
          <el-input v-model="queryForm.bomUseDay163" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay164">
          <el-input v-model="queryForm.bomUseDay164" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay165">
          <el-input v-model="queryForm.bomUseDay165" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay166">
          <el-input v-model="queryForm.bomUseDay166" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay167">
          <el-input v-model="queryForm.bomUseDay167" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay168">
          <el-input v-model="queryForm.bomUseDay168" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay169">
          <el-input v-model="queryForm.bomUseDay169" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay170">
          <el-input v-model="queryForm.bomUseDay170" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay171">
          <el-input v-model="queryForm.bomUseDay171" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay172">
          <el-input v-model="queryForm.bomUseDay172" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay173">
          <el-input v-model="queryForm.bomUseDay173" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay174">
          <el-input v-model="queryForm.bomUseDay174" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay175">
          <el-input v-model="queryForm.bomUseDay175" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay176">
          <el-input v-model="queryForm.bomUseDay176" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay177">
          <el-input v-model="queryForm.bomUseDay177" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay178">
          <el-input v-model="queryForm.bomUseDay178" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay179">
          <el-input v-model="queryForm.bomUseDay179" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay180">
          <el-input v-model="queryForm.bomUseDay180" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay181">
          <el-input v-model="queryForm.bomUseDay181" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay182">
          <el-input v-model="queryForm.bomUseDay182" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay183">
          <el-input v-model="queryForm.bomUseDay183" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay184">
          <el-input v-model="queryForm.bomUseDay184" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay185">
          <el-input v-model="queryForm.bomUseDay185" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay186">
          <el-input v-model="queryForm.bomUseDay186" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay187">
          <el-input v-model="queryForm.bomUseDay187" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay188">
          <el-input v-model="queryForm.bomUseDay188" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay189">
          <el-input v-model="queryForm.bomUseDay189" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay190">
          <el-input v-model="queryForm.bomUseDay190" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay191">
          <el-input v-model="queryForm.bomUseDay191" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay192">
          <el-input v-model="queryForm.bomUseDay192" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay193">
          <el-input v-model="queryForm.bomUseDay193" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay194">
          <el-input v-model="queryForm.bomUseDay194" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay195">
          <el-input v-model="queryForm.bomUseDay195" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay196">
          <el-input v-model="queryForm.bomUseDay196" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay197">
          <el-input v-model="queryForm.bomUseDay197" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay198">
          <el-input v-model="queryForm.bomUseDay198" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay199">
          <el-input v-model="queryForm.bomUseDay199" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay200">
          <el-input v-model="queryForm.bomUseDay200" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay201">
          <el-input v-model="queryForm.bomUseDay201" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay202">
          <el-input v-model="queryForm.bomUseDay202" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay203">
          <el-input v-model="queryForm.bomUseDay203" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay204">
          <el-input v-model="queryForm.bomUseDay204" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay205">
          <el-input v-model="queryForm.bomUseDay205" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay206">
          <el-input v-model="queryForm.bomUseDay206" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay207">
          <el-input v-model="queryForm.bomUseDay207" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay208">
          <el-input v-model="queryForm.bomUseDay208" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay209">
          <el-input v-model="queryForm.bomUseDay209" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay210">
          <el-input v-model="queryForm.bomUseDay210" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay211">
          <el-input v-model="queryForm.bomUseDay211" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay212">
          <el-input v-model="queryForm.bomUseDay212" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay213">
          <el-input v-model="queryForm.bomUseDay213" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay214">
          <el-input v-model="queryForm.bomUseDay214" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay215">
          <el-input v-model="queryForm.bomUseDay215" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay216">
          <el-input v-model="queryForm.bomUseDay216" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay217">
          <el-input v-model="queryForm.bomUseDay217" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay218">
          <el-input v-model="queryForm.bomUseDay218" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay219">
          <el-input v-model="queryForm.bomUseDay219" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay220">
          <el-input v-model="queryForm.bomUseDay220" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay221">
          <el-input v-model="queryForm.bomUseDay221" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay222">
          <el-input v-model="queryForm.bomUseDay222" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay223">
          <el-input v-model="queryForm.bomUseDay223" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay224">
          <el-input v-model="queryForm.bomUseDay224" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay225">
          <el-input v-model="queryForm.bomUseDay225" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay226">
          <el-input v-model="queryForm.bomUseDay226" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay227">
          <el-input v-model="queryForm.bomUseDay227" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay228">
          <el-input v-model="queryForm.bomUseDay228" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay229">
          <el-input v-model="queryForm.bomUseDay229" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay230">
          <el-input v-model="queryForm.bomUseDay230" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay231">
          <el-input v-model="queryForm.bomUseDay231" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay232">
          <el-input v-model="queryForm.bomUseDay232" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay233">
          <el-input v-model="queryForm.bomUseDay233" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay234">
          <el-input v-model="queryForm.bomUseDay234" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay235">
          <el-input v-model="queryForm.bomUseDay235" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay236">
          <el-input v-model="queryForm.bomUseDay236" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay237">
          <el-input v-model="queryForm.bomUseDay237" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay238">
          <el-input v-model="queryForm.bomUseDay238" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay239">
          <el-input v-model="queryForm.bomUseDay239" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay240">
          <el-input v-model="queryForm.bomUseDay240" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay241">
          <el-input v-model="queryForm.bomUseDay241" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay242">
          <el-input v-model="queryForm.bomUseDay242" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay243">
          <el-input v-model="queryForm.bomUseDay243" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay244">
          <el-input v-model="queryForm.bomUseDay244" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay245">
          <el-input v-model="queryForm.bomUseDay245" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay246">
          <el-input v-model="queryForm.bomUseDay246" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay247">
          <el-input v-model="queryForm.bomUseDay247" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay248">
          <el-input v-model="queryForm.bomUseDay248" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay249">
          <el-input v-model="queryForm.bomUseDay249" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay250">
          <el-input v-model="queryForm.bomUseDay250" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay251">
          <el-input v-model="queryForm.bomUseDay251" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay252">
          <el-input v-model="queryForm.bomUseDay252" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay253">
          <el-input v-model="queryForm.bomUseDay253" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay254">
          <el-input v-model="queryForm.bomUseDay254" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay255">
          <el-input v-model="queryForm.bomUseDay255" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay256">
          <el-input v-model="queryForm.bomUseDay256" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay257">
          <el-input v-model="queryForm.bomUseDay257" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay258">
          <el-input v-model="queryForm.bomUseDay258" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay259">
          <el-input v-model="queryForm.bomUseDay259" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay260">
          <el-input v-model="queryForm.bomUseDay260" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay261">
          <el-input v-model="queryForm.bomUseDay261" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay262">
          <el-input v-model="queryForm.bomUseDay262" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay263">
          <el-input v-model="queryForm.bomUseDay263" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay264">
          <el-input v-model="queryForm.bomUseDay264" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay265">
          <el-input v-model="queryForm.bomUseDay265" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay266">
          <el-input v-model="queryForm.bomUseDay266" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay267">
          <el-input v-model="queryForm.bomUseDay267" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay268">
          <el-input v-model="queryForm.bomUseDay268" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay269">
          <el-input v-model="queryForm.bomUseDay269" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay270">
          <el-input v-model="queryForm.bomUseDay270" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay271">
          <el-input v-model="queryForm.bomUseDay271" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay272">
          <el-input v-model="queryForm.bomUseDay272" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay273">
          <el-input v-model="queryForm.bomUseDay273" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay274">
          <el-input v-model="queryForm.bomUseDay274" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay275">
          <el-input v-model="queryForm.bomUseDay275" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay276">
          <el-input v-model="queryForm.bomUseDay276" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay277">
          <el-input v-model="queryForm.bomUseDay277" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay278">
          <el-input v-model="queryForm.bomUseDay278" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay279">
          <el-input v-model="queryForm.bomUseDay279" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay280">
          <el-input v-model="queryForm.bomUseDay280" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay281">
          <el-input v-model="queryForm.bomUseDay281" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay282">
          <el-input v-model="queryForm.bomUseDay282" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay283">
          <el-input v-model="queryForm.bomUseDay283" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay284">
          <el-input v-model="queryForm.bomUseDay284" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay285">
          <el-input v-model="queryForm.bomUseDay285" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay286">
          <el-input v-model="queryForm.bomUseDay286" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay287">
          <el-input v-model="queryForm.bomUseDay287" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay288">
          <el-input v-model="queryForm.bomUseDay288" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay289">
          <el-input v-model="queryForm.bomUseDay289" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay290">
          <el-input v-model="queryForm.bomUseDay290" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay291">
          <el-input v-model="queryForm.bomUseDay291" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay292">
          <el-input v-model="queryForm.bomUseDay292" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay293">
          <el-input v-model="queryForm.bomUseDay293" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay294">
          <el-input v-model="queryForm.bomUseDay294" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay295">
          <el-input v-model="queryForm.bomUseDay295" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay296">
          <el-input v-model="queryForm.bomUseDay296" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay297">
          <el-input v-model="queryForm.bomUseDay297" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay298">
          <el-input v-model="queryForm.bomUseDay298" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay299">
          <el-input v-model="queryForm.bomUseDay299" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay300">
          <el-input v-model="queryForm.bomUseDay300" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay301">
          <el-input v-model="queryForm.bomUseDay301" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay302">
          <el-input v-model="queryForm.bomUseDay302" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay303">
          <el-input v-model="queryForm.bomUseDay303" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay304">
          <el-input v-model="queryForm.bomUseDay304" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay305">
          <el-input v-model="queryForm.bomUseDay305" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay306">
          <el-input v-model="queryForm.bomUseDay306" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay307">
          <el-input v-model="queryForm.bomUseDay307" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay308">
          <el-input v-model="queryForm.bomUseDay308" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay309">
          <el-input v-model="queryForm.bomUseDay309" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay310">
          <el-input v-model="queryForm.bomUseDay310" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay311">
          <el-input v-model="queryForm.bomUseDay311" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay312">
          <el-input v-model="queryForm.bomUseDay312" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay313">
          <el-input v-model="queryForm.bomUseDay313" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay314">
          <el-input v-model="queryForm.bomUseDay314" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay315">
          <el-input v-model="queryForm.bomUseDay315" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay316">
          <el-input v-model="queryForm.bomUseDay316" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay317">
          <el-input v-model="queryForm.bomUseDay317" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay318">
          <el-input v-model="queryForm.bomUseDay318" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay319">
          <el-input v-model="queryForm.bomUseDay319" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay320">
          <el-input v-model="queryForm.bomUseDay320" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay321">
          <el-input v-model="queryForm.bomUseDay321" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay322">
          <el-input v-model="queryForm.bomUseDay322" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay323">
          <el-input v-model="queryForm.bomUseDay323" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay324">
          <el-input v-model="queryForm.bomUseDay324" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay325">
          <el-input v-model="queryForm.bomUseDay325" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay326">
          <el-input v-model="queryForm.bomUseDay326" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay327">
          <el-input v-model="queryForm.bomUseDay327" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay328">
          <el-input v-model="queryForm.bomUseDay328" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay329">
          <el-input v-model="queryForm.bomUseDay329" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay330">
          <el-input v-model="queryForm.bomUseDay330" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay331">
          <el-input v-model="queryForm.bomUseDay331" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay332">
          <el-input v-model="queryForm.bomUseDay332" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay333">
          <el-input v-model="queryForm.bomUseDay333" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay334">
          <el-input v-model="queryForm.bomUseDay334" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay335">
          <el-input v-model="queryForm.bomUseDay335" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay336">
          <el-input v-model="queryForm.bomUseDay336" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay337">
          <el-input v-model="queryForm.bomUseDay337" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay338">
          <el-input v-model="queryForm.bomUseDay338" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay339">
          <el-input v-model="queryForm.bomUseDay339" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay340">
          <el-input v-model="queryForm.bomUseDay340" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay341">
          <el-input v-model="queryForm.bomUseDay341" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay342">
          <el-input v-model="queryForm.bomUseDay342" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay343">
          <el-input v-model="queryForm.bomUseDay343" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay344">
          <el-input v-model="queryForm.bomUseDay344" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay345">
          <el-input v-model="queryForm.bomUseDay345" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay346">
          <el-input v-model="queryForm.bomUseDay346" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay347">
          <el-input v-model="queryForm.bomUseDay347" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay348">
          <el-input v-model="queryForm.bomUseDay348" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay349">
          <el-input v-model="queryForm.bomUseDay349" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay350">
          <el-input v-model="queryForm.bomUseDay350" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay351">
          <el-input v-model="queryForm.bomUseDay351" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay352">
          <el-input v-model="queryForm.bomUseDay352" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay353">
          <el-input v-model="queryForm.bomUseDay353" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay354">
          <el-input v-model="queryForm.bomUseDay354" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay355">
          <el-input v-model="queryForm.bomUseDay355" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay356">
          <el-input v-model="queryForm.bomUseDay356" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay357">
          <el-input v-model="queryForm.bomUseDay357" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay358">
          <el-input v-model="queryForm.bomUseDay358" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay359">
          <el-input v-model="queryForm.bomUseDay359" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay360">
          <el-input v-model="queryForm.bomUseDay360" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay361">
          <el-input v-model="queryForm.bomUseDay361" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay362">
          <el-input v-model="queryForm.bomUseDay362" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay363">
          <el-input v-model="queryForm.bomUseDay363" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay364">
          <el-input v-model="queryForm.bomUseDay364" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay365">
          <el-input v-model="queryForm.bomUseDay365" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="使用信息 {lack:1, quantity:3}" prop="bomUseDay366">
          <el-input v-model="queryForm.bomUseDay366" clearable placeholder="请输入使用信息 {lack:1, quantity:3}" />
        </el-form-item>
        <el-form-item label="工厂" prop="factoryId">
          <el-input v-model="queryForm.factoryId" clearable placeholder="请输入工厂ID" />
        </el-form-item>
        <el-form-item label="是否关注" prop="isFollow">
          <el-input v-model="queryForm.isFollow" clearable placeholder="请输入是否关注" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="search" @click="getDataList">
            查询
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card shadow="never">
      <TableBar
        :document-title="documentTitle"
        :add-component="AddEditFormVue"
        :refresh-list="getDataList"
        :data-table-ref="dataTableRef"
        :multiple-selection="multipleSelection"
        ref="tableBarRef"
        :data-batch-delete-url="dataBatchDeleteUrl"
      />
      <ElTable ref="dataTableRef" :data="dataList" stripe @selection-change="handleSelectionChange">
        <ElTableColumn type="selection"/>
        <ElTableColumn v-for="h in headerList" :key="h.fieldName" :label="h.showName" :prop="h.fieldName" :min-width="h.width"/>
        <ElTableColumn fixed="right" label="操作" width="150px">
          <template #default="scope">
            <el-button
              type="warning"
              icon="edit"
              @click="editData(scope.row)"
            >
              编辑
            </el-button>
          </template>
        </ElTableColumn>
      </ElTable>
      <el-row class="paginationDiv">
        <el-pagination
          background
          v-model:current-page="currentPageNum"
          v-model:page-size="currentPageSize"
          layout="total, sizes, prev, pager, next"
          :total="tableTotal"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </el-row>
    </el-card>
  </div>
</template>

<style scoped lang="scss">

</style>

