<script setup lang="ts">
import {ref, onMounted} from "vue"
import AddEditFormVue from "./ApsGoodsForecastMakeProjectDataAddEditForm.vue"
import TableBar from "@/layouts/components/TableBar/index.vue"
import { ElTable } from "element-plus";
import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts"
import {type ApsGoodsForecastMakeProjectData} from "./ApsGoodsForecastMakeProjectDataType.ts"

const dtoUrl = ref<string>("/apsGoodsForecastMakeProjectData")
const documentTitle = ref<string>("预测数据")
const dataBatchDeleteUrl = ref<string>(`${dtoUrl.value}/deleteByIdList`)

// 查询表格
const queryForm = ref<ApsGoodsForecastMakeProjectData>({
  goodsId: undefined,
  makeMonthId: undefined,
  projectConfigCode: undefined,
  year: undefined,
  dayNum1: undefined,
  dayNum2: undefined,
  dayNum3: undefined,
  dayNum4: undefined,
  dayNum5: undefined,
  dayNum6: undefined,
  dayNum7: undefined,
  dayNum8: undefined,
  dayNum9: undefined,
  dayNum10: undefined,
  dayNum11: undefined,
  dayNum12: undefined,
  dayNum13: undefined,
  dayNum14: undefined,
  dayNum15: undefined,
  dayNum16: undefined,
  dayNum17: undefined,
  dayNum18: undefined,
  dayNum19: undefined,
  dayNum20: undefined,
  dayNum21: undefined,
  dayNum22: undefined,
  dayNum23: undefined,
  dayNum24: undefined,
  dayNum25: undefined,
  dayNum26: undefined,
  dayNum27: undefined,
  dayNum28: undefined,
  dayNum29: undefined,
  dayNum30: undefined,
  dayNum31: undefined,
  dayNum32: undefined,
  dayNum33: undefined,
  dayNum34: undefined,
  dayNum35: undefined,
  dayNum36: undefined,
  dayNum37: undefined,
  dayNum38: undefined,
  dayNum39: undefined,
  dayNum40: undefined,
  dayNum41: undefined,
  dayNum42: undefined,
  dayNum43: undefined,
  dayNum44: undefined,
  dayNum45: undefined,
  dayNum46: undefined,
  dayNum47: undefined,
  dayNum48: undefined,
  dayNum49: undefined,
  dayNum50: undefined,
  dayNum51: undefined,
  dayNum52: undefined,
  dayNum53: undefined,
  dayNum54: undefined,
  dayNum55: undefined,
  dayNum56: undefined,
  dayNum57: undefined,
  dayNum58: undefined,
  dayNum59: undefined,
  dayNum60: undefined,
  dayNum61: undefined,
  dayNum62: undefined,
  dayNum63: undefined,
  dayNum64: undefined,
  dayNum65: undefined,
  dayNum66: undefined,
  dayNum67: undefined,
  dayNum68: undefined,
  dayNum69: undefined,
  dayNum70: undefined,
  dayNum71: undefined,
  dayNum72: undefined,
  dayNum73: undefined,
  dayNum74: undefined,
  dayNum75: undefined,
  dayNum76: undefined,
  dayNum77: undefined,
  dayNum78: undefined,
  dayNum79: undefined,
  dayNum80: undefined,
  dayNum81: undefined,
  dayNum82: undefined,
  dayNum83: undefined,
  dayNum84: undefined,
  dayNum85: undefined,
  dayNum86: undefined,
  dayNum87: undefined,
  dayNum88: undefined,
  dayNum89: undefined,
  dayNum90: undefined,
  dayNum91: undefined,
  dayNum92: undefined,
  dayNum93: undefined,
  dayNum94: undefined,
  dayNum95: undefined,
  dayNum96: undefined,
  dayNum97: undefined,
  dayNum98: undefined,
  dayNum99: undefined,
  dayNum100: undefined,
  dayNum101: undefined,
  dayNum102: undefined,
  dayNum103: undefined,
  dayNum104: undefined,
  dayNum105: undefined,
  dayNum106: undefined,
  dayNum107: undefined,
  dayNum108: undefined,
  dayNum109: undefined,
  dayNum110: undefined,
  dayNum111: undefined,
  dayNum112: undefined,
  dayNum113: undefined,
  dayNum114: undefined,
  dayNum115: undefined,
  dayNum116: undefined,
  dayNum117: undefined,
  dayNum118: undefined,
  dayNum119: undefined,
  dayNum120: undefined,
  dayNum121: undefined,
  dayNum122: undefined,
  dayNum123: undefined,
  dayNum124: undefined,
  dayNum125: undefined,
  dayNum126: undefined,
  dayNum127: undefined,
  dayNum128: undefined,
  dayNum129: undefined,
  dayNum130: undefined,
  dayNum131: undefined,
  dayNum132: undefined,
  dayNum133: undefined,
  dayNum134: undefined,
  dayNum135: undefined,
  dayNum136: undefined,
  dayNum137: undefined,
  dayNum138: undefined,
  dayNum139: undefined,
  dayNum140: undefined,
  dayNum141: undefined,
  dayNum142: undefined,
  dayNum143: undefined,
  dayNum144: undefined,
  dayNum145: undefined,
  dayNum146: undefined,
  dayNum147: undefined,
  dayNum148: undefined,
  dayNum149: undefined,
  dayNum150: undefined,
  dayNum151: undefined,
  dayNum152: undefined,
  dayNum153: undefined,
  dayNum154: undefined,
  dayNum155: undefined,
  dayNum156: undefined,
  dayNum157: undefined,
  dayNum158: undefined,
  dayNum159: undefined,
  dayNum160: undefined,
  dayNum161: undefined,
  dayNum162: undefined,
  dayNum163: undefined,
  dayNum164: undefined,
  dayNum165: undefined,
  dayNum166: undefined,
  dayNum167: undefined,
  dayNum168: undefined,
  dayNum169: undefined,
  dayNum170: undefined,
  dayNum171: undefined,
  dayNum172: undefined,
  dayNum173: undefined,
  dayNum174: undefined,
  dayNum175: undefined,
  dayNum176: undefined,
  dayNum177: undefined,
  dayNum178: undefined,
  dayNum179: undefined,
  dayNum180: undefined,
  dayNum181: undefined,
  dayNum182: undefined,
  dayNum183: undefined,
  dayNum184: undefined,
  dayNum185: undefined,
  dayNum186: undefined,
  dayNum187: undefined,
  dayNum188: undefined,
  dayNum189: undefined,
  dayNum190: undefined,
  dayNum191: undefined,
  dayNum192: undefined,
  dayNum193: undefined,
  dayNum194: undefined,
  dayNum195: undefined,
  dayNum196: undefined,
  dayNum197: undefined,
  dayNum198: undefined,
  dayNum199: undefined,
  dayNum200: undefined,
  dayNum201: undefined,
  dayNum202: undefined,
  dayNum203: undefined,
  dayNum204: undefined,
  dayNum205: undefined,
  dayNum206: undefined,
  dayNum207: undefined,
  dayNum208: undefined,
  dayNum209: undefined,
  dayNum210: undefined,
  dayNum211: undefined,
  dayNum212: undefined,
  dayNum213: undefined,
  dayNum214: undefined,
  dayNum215: undefined,
  dayNum216: undefined,
  dayNum217: undefined,
  dayNum218: undefined,
  dayNum219: undefined,
  dayNum220: undefined,
  dayNum221: undefined,
  dayNum222: undefined,
  dayNum223: undefined,
  dayNum224: undefined,
  dayNum225: undefined,
  dayNum226: undefined,
  dayNum227: undefined,
  dayNum228: undefined,
  dayNum229: undefined,
  dayNum230: undefined,
  dayNum231: undefined,
  dayNum232: undefined,
  dayNum233: undefined,
  dayNum234: undefined,
  dayNum235: undefined,
  dayNum236: undefined,
  dayNum237: undefined,
  dayNum238: undefined,
  dayNum239: undefined,
  dayNum240: undefined,
  dayNum241: undefined,
  dayNum242: undefined,
  dayNum243: undefined,
  dayNum244: undefined,
  dayNum245: undefined,
  dayNum246: undefined,
  dayNum247: undefined,
  dayNum248: undefined,
  dayNum249: undefined,
  dayNum250: undefined,
  dayNum251: undefined,
  dayNum252: undefined,
  dayNum253: undefined,
  dayNum254: undefined,
  dayNum255: undefined,
  dayNum256: undefined,
  dayNum257: undefined,
  dayNum258: undefined,
  dayNum259: undefined,
  dayNum260: undefined,
  dayNum261: undefined,
  dayNum262: undefined,
  dayNum263: undefined,
  dayNum264: undefined,
  dayNum265: undefined,
  dayNum266: undefined,
  dayNum267: undefined,
  dayNum268: undefined,
  dayNum269: undefined,
  dayNum270: undefined,
  dayNum271: undefined,
  dayNum272: undefined,
  dayNum273: undefined,
  dayNum274: undefined,
  dayNum275: undefined,
  dayNum276: undefined,
  dayNum277: undefined,
  dayNum278: undefined,
  dayNum279: undefined,
  dayNum280: undefined,
  dayNum281: undefined,
  dayNum282: undefined,
  dayNum283: undefined,
  dayNum284: undefined,
  dayNum285: undefined,
  dayNum286: undefined,
  dayNum287: undefined,
  dayNum288: undefined,
  dayNum289: undefined,
  dayNum290: undefined,
  dayNum291: undefined,
  dayNum292: undefined,
  dayNum293: undefined,
  dayNum294: undefined,
  dayNum295: undefined,
  dayNum296: undefined,
  dayNum297: undefined,
  dayNum298: undefined,
  dayNum299: undefined,
  dayNum300: undefined,
  dayNum301: undefined,
  dayNum302: undefined,
  dayNum303: undefined,
  dayNum304: undefined,
  dayNum305: undefined,
  dayNum306: undefined,
  dayNum307: undefined,
  dayNum308: undefined,
  dayNum309: undefined,
  dayNum310: undefined,
  dayNum311: undefined,
  dayNum312: undefined,
  dayNum313: undefined,
  dayNum314: undefined,
  dayNum315: undefined,
  dayNum316: undefined,
  dayNum317: undefined,
  dayNum318: undefined,
  dayNum319: undefined,
  dayNum320: undefined,
  dayNum321: undefined,
  dayNum322: undefined,
  dayNum323: undefined,
  dayNum324: undefined,
  dayNum325: undefined,
  dayNum326: undefined,
  dayNum327: undefined,
  dayNum328: undefined,
  dayNum329: undefined,
  dayNum330: undefined,
  dayNum331: undefined,
  dayNum332: undefined,
  dayNum333: undefined,
  dayNum334: undefined,
  dayNum335: undefined,
  dayNum336: undefined,
  dayNum337: undefined,
  dayNum338: undefined,
  dayNum339: undefined,
  dayNum340: undefined,
  dayNum341: undefined,
  dayNum342: undefined,
  dayNum343: undefined,
  dayNum344: undefined,
  dayNum345: undefined,
  dayNum346: undefined,
  dayNum347: undefined,
  dayNum348: undefined,
  dayNum349: undefined,
  dayNum350: undefined,
  dayNum351: undefined,
  dayNum352: undefined,
  dayNum353: undefined,
  dayNum354: undefined,
  dayNum355: undefined,
  dayNum356: undefined,
  dayNum357: undefined,
  dayNum358: undefined,
  dayNum359: undefined,
  dayNum360: undefined,
  dayNum361: undefined,
  dayNum362: undefined,
  dayNum363: undefined,
  dayNum364: undefined,
  dayNum365: undefined,
  dayNum366: undefined,
  factoryId: undefined,
  makeSaleConfigId: undefined,
  id: undefined
})

// 表格选中的id
const multipleSelection = ref<(string | undefined)[]>([])

// 表格
// const dataTableRef = ref<InstanceType<typeof ElTable> | null>(null)
const dataTableRef = ref({})
// 表格操作头
const tableBarRef = ref<InstanceType<typeof TableBar> | null>(null)
// 表格相关
const dataList = ref<ApsGoodsForecastMakeProjectData[] >([])
const currentPageNum = ref<number>(1)
const currentPageSize = ref<number>(10)
const tableTotal = ref<number>(0)
const headerList = ref<HeaderInfo[]>([
 {fieldName: "id", showName: "序号"},
  { fieldName: "goodsId", showName: "商品" },
  { fieldName: "makeMonthId", showName: "" },
  { fieldName: "projectConfigCode", showName: "" },
  { fieldName: "year", showName: "" },
  { fieldName: "dayNum1", showName: "制造数" },
  { fieldName: "dayNum2", showName: "制造数" },
  { fieldName: "dayNum3", showName: "制造数" },
  { fieldName: "dayNum4", showName: "制造数" },
  { fieldName: "dayNum5", showName: "制造数" },
  { fieldName: "dayNum6", showName: "制造数" },
  { fieldName: "dayNum7", showName: "制造数" },
  { fieldName: "dayNum8", showName: "制造数" },
  { fieldName: "dayNum9", showName: "制造数" },
  { fieldName: "dayNum10", showName: "制造数" },
  { fieldName: "dayNum11", showName: "制造数" },
  { fieldName: "dayNum12", showName: "制造数" },
  { fieldName: "dayNum13", showName: "制造数" },
  { fieldName: "dayNum14", showName: "制造数" },
  { fieldName: "dayNum15", showName: "制造数" },
  { fieldName: "dayNum16", showName: "制造数" },
  { fieldName: "dayNum17", showName: "制造数" },
  { fieldName: "dayNum18", showName: "制造数" },
  { fieldName: "dayNum19", showName: "制造数" },
  { fieldName: "dayNum20", showName: "制造数" },
  { fieldName: "dayNum21", showName: "制造数" },
  { fieldName: "dayNum22", showName: "制造数" },
  { fieldName: "dayNum23", showName: "制造数" },
  { fieldName: "dayNum24", showName: "制造数" },
  { fieldName: "dayNum25", showName: "制造数" },
  { fieldName: "dayNum26", showName: "制造数" },
  { fieldName: "dayNum27", showName: "制造数" },
  { fieldName: "dayNum28", showName: "制造数" },
  { fieldName: "dayNum29", showName: "制造数" },
  { fieldName: "dayNum30", showName: "制造数" },
  { fieldName: "dayNum31", showName: "制造数" },
  { fieldName: "dayNum32", showName: "制造数" },
  { fieldName: "dayNum33", showName: "制造数" },
  { fieldName: "dayNum34", showName: "制造数" },
  { fieldName: "dayNum35", showName: "制造数" },
  { fieldName: "dayNum36", showName: "制造数" },
  { fieldName: "dayNum37", showName: "制造数" },
  { fieldName: "dayNum38", showName: "制造数" },
  { fieldName: "dayNum39", showName: "制造数" },
  { fieldName: "dayNum40", showName: "制造数" },
  { fieldName: "dayNum41", showName: "制造数" },
  { fieldName: "dayNum42", showName: "制造数" },
  { fieldName: "dayNum43", showName: "制造数" },
  { fieldName: "dayNum44", showName: "制造数" },
  { fieldName: "dayNum45", showName: "制造数" },
  { fieldName: "dayNum46", showName: "制造数" },
  { fieldName: "dayNum47", showName: "制造数" },
  { fieldName: "dayNum48", showName: "制造数" },
  { fieldName: "dayNum49", showName: "制造数" },
  { fieldName: "dayNum50", showName: "制造数" },
  { fieldName: "dayNum51", showName: "制造数" },
  { fieldName: "dayNum52", showName: "制造数" },
  { fieldName: "dayNum53", showName: "制造数" },
  { fieldName: "dayNum54", showName: "制造数" },
  { fieldName: "dayNum55", showName: "制造数" },
  { fieldName: "dayNum56", showName: "制造数" },
  { fieldName: "dayNum57", showName: "制造数" },
  { fieldName: "dayNum58", showName: "制造数" },
  { fieldName: "dayNum59", showName: "制造数" },
  { fieldName: "dayNum60", showName: "制造数" },
  { fieldName: "dayNum61", showName: "制造数" },
  { fieldName: "dayNum62", showName: "制造数" },
  { fieldName: "dayNum63", showName: "制造数" },
  { fieldName: "dayNum64", showName: "制造数" },
  { fieldName: "dayNum65", showName: "制造数" },
  { fieldName: "dayNum66", showName: "制造数" },
  { fieldName: "dayNum67", showName: "制造数" },
  { fieldName: "dayNum68", showName: "制造数" },
  { fieldName: "dayNum69", showName: "制造数" },
  { fieldName: "dayNum70", showName: "制造数" },
  { fieldName: "dayNum71", showName: "制造数" },
  { fieldName: "dayNum72", showName: "制造数" },
  { fieldName: "dayNum73", showName: "制造数" },
  { fieldName: "dayNum74", showName: "制造数" },
  { fieldName: "dayNum75", showName: "制造数" },
  { fieldName: "dayNum76", showName: "制造数" },
  { fieldName: "dayNum77", showName: "制造数" },
  { fieldName: "dayNum78", showName: "制造数" },
  { fieldName: "dayNum79", showName: "制造数" },
  { fieldName: "dayNum80", showName: "制造数" },
  { fieldName: "dayNum81", showName: "制造数" },
  { fieldName: "dayNum82", showName: "制造数" },
  { fieldName: "dayNum83", showName: "制造数" },
  { fieldName: "dayNum84", showName: "制造数" },
  { fieldName: "dayNum85", showName: "制造数" },
  { fieldName: "dayNum86", showName: "制造数" },
  { fieldName: "dayNum87", showName: "制造数" },
  { fieldName: "dayNum88", showName: "制造数" },
  { fieldName: "dayNum89", showName: "制造数" },
  { fieldName: "dayNum90", showName: "制造数" },
  { fieldName: "dayNum91", showName: "制造数" },
  { fieldName: "dayNum92", showName: "制造数" },
  { fieldName: "dayNum93", showName: "制造数" },
  { fieldName: "dayNum94", showName: "制造数" },
  { fieldName: "dayNum95", showName: "制造数" },
  { fieldName: "dayNum96", showName: "制造数" },
  { fieldName: "dayNum97", showName: "制造数" },
  { fieldName: "dayNum98", showName: "制造数" },
  { fieldName: "dayNum99", showName: "制造数" },
  { fieldName: "dayNum100", showName: "制造数" },
  { fieldName: "dayNum101", showName: "制造数" },
  { fieldName: "dayNum102", showName: "制造数" },
  { fieldName: "dayNum103", showName: "制造数" },
  { fieldName: "dayNum104", showName: "制造数" },
  { fieldName: "dayNum105", showName: "制造数" },
  { fieldName: "dayNum106", showName: "制造数" },
  { fieldName: "dayNum107", showName: "制造数" },
  { fieldName: "dayNum108", showName: "制造数" },
  { fieldName: "dayNum109", showName: "制造数" },
  { fieldName: "dayNum110", showName: "制造数" },
  { fieldName: "dayNum111", showName: "制造数" },
  { fieldName: "dayNum112", showName: "制造数" },
  { fieldName: "dayNum113", showName: "制造数" },
  { fieldName: "dayNum114", showName: "制造数" },
  { fieldName: "dayNum115", showName: "制造数" },
  { fieldName: "dayNum116", showName: "制造数" },
  { fieldName: "dayNum117", showName: "制造数" },
  { fieldName: "dayNum118", showName: "制造数" },
  { fieldName: "dayNum119", showName: "制造数" },
  { fieldName: "dayNum120", showName: "制造数" },
  { fieldName: "dayNum121", showName: "制造数" },
  { fieldName: "dayNum122", showName: "制造数" },
  { fieldName: "dayNum123", showName: "制造数" },
  { fieldName: "dayNum124", showName: "制造数" },
  { fieldName: "dayNum125", showName: "制造数" },
  { fieldName: "dayNum126", showName: "制造数" },
  { fieldName: "dayNum127", showName: "制造数" },
  { fieldName: "dayNum128", showName: "制造数" },
  { fieldName: "dayNum129", showName: "制造数" },
  { fieldName: "dayNum130", showName: "制造数" },
  { fieldName: "dayNum131", showName: "制造数" },
  { fieldName: "dayNum132", showName: "制造数" },
  { fieldName: "dayNum133", showName: "制造数" },
  { fieldName: "dayNum134", showName: "制造数" },
  { fieldName: "dayNum135", showName: "制造数" },
  { fieldName: "dayNum136", showName: "制造数" },
  { fieldName: "dayNum137", showName: "制造数" },
  { fieldName: "dayNum138", showName: "制造数" },
  { fieldName: "dayNum139", showName: "制造数" },
  { fieldName: "dayNum140", showName: "制造数" },
  { fieldName: "dayNum141", showName: "制造数" },
  { fieldName: "dayNum142", showName: "制造数" },
  { fieldName: "dayNum143", showName: "制造数" },
  { fieldName: "dayNum144", showName: "制造数" },
  { fieldName: "dayNum145", showName: "制造数" },
  { fieldName: "dayNum146", showName: "制造数" },
  { fieldName: "dayNum147", showName: "制造数" },
  { fieldName: "dayNum148", showName: "制造数" },
  { fieldName: "dayNum149", showName: "制造数" },
  { fieldName: "dayNum150", showName: "制造数" },
  { fieldName: "dayNum151", showName: "制造数" },
  { fieldName: "dayNum152", showName: "制造数" },
  { fieldName: "dayNum153", showName: "制造数" },
  { fieldName: "dayNum154", showName: "制造数" },
  { fieldName: "dayNum155", showName: "制造数" },
  { fieldName: "dayNum156", showName: "制造数" },
  { fieldName: "dayNum157", showName: "制造数" },
  { fieldName: "dayNum158", showName: "制造数" },
  { fieldName: "dayNum159", showName: "制造数" },
  { fieldName: "dayNum160", showName: "制造数" },
  { fieldName: "dayNum161", showName: "制造数" },
  { fieldName: "dayNum162", showName: "制造数" },
  { fieldName: "dayNum163", showName: "制造数" },
  { fieldName: "dayNum164", showName: "制造数" },
  { fieldName: "dayNum165", showName: "制造数" },
  { fieldName: "dayNum166", showName: "制造数" },
  { fieldName: "dayNum167", showName: "制造数" },
  { fieldName: "dayNum168", showName: "制造数" },
  { fieldName: "dayNum169", showName: "制造数" },
  { fieldName: "dayNum170", showName: "制造数" },
  { fieldName: "dayNum171", showName: "制造数" },
  { fieldName: "dayNum172", showName: "制造数" },
  { fieldName: "dayNum173", showName: "制造数" },
  { fieldName: "dayNum174", showName: "制造数" },
  { fieldName: "dayNum175", showName: "制造数" },
  { fieldName: "dayNum176", showName: "制造数" },
  { fieldName: "dayNum177", showName: "制造数" },
  { fieldName: "dayNum178", showName: "制造数" },
  { fieldName: "dayNum179", showName: "制造数" },
  { fieldName: "dayNum180", showName: "制造数" },
  { fieldName: "dayNum181", showName: "制造数" },
  { fieldName: "dayNum182", showName: "制造数" },
  { fieldName: "dayNum183", showName: "制造数" },
  { fieldName: "dayNum184", showName: "制造数" },
  { fieldName: "dayNum185", showName: "制造数" },
  { fieldName: "dayNum186", showName: "制造数" },
  { fieldName: "dayNum187", showName: "制造数" },
  { fieldName: "dayNum188", showName: "制造数" },
  { fieldName: "dayNum189", showName: "制造数" },
  { fieldName: "dayNum190", showName: "制造数" },
  { fieldName: "dayNum191", showName: "制造数" },
  { fieldName: "dayNum192", showName: "制造数" },
  { fieldName: "dayNum193", showName: "制造数" },
  { fieldName: "dayNum194", showName: "制造数" },
  { fieldName: "dayNum195", showName: "制造数" },
  { fieldName: "dayNum196", showName: "制造数" },
  { fieldName: "dayNum197", showName: "制造数" },
  { fieldName: "dayNum198", showName: "制造数" },
  { fieldName: "dayNum199", showName: "制造数" },
  { fieldName: "dayNum200", showName: "制造数" },
  { fieldName: "dayNum201", showName: "制造数" },
  { fieldName: "dayNum202", showName: "制造数" },
  { fieldName: "dayNum203", showName: "制造数" },
  { fieldName: "dayNum204", showName: "制造数" },
  { fieldName: "dayNum205", showName: "制造数" },
  { fieldName: "dayNum206", showName: "制造数" },
  { fieldName: "dayNum207", showName: "制造数" },
  { fieldName: "dayNum208", showName: "制造数" },
  { fieldName: "dayNum209", showName: "制造数" },
  { fieldName: "dayNum210", showName: "制造数" },
  { fieldName: "dayNum211", showName: "制造数" },
  { fieldName: "dayNum212", showName: "制造数" },
  { fieldName: "dayNum213", showName: "制造数" },
  { fieldName: "dayNum214", showName: "制造数" },
  { fieldName: "dayNum215", showName: "制造数" },
  { fieldName: "dayNum216", showName: "制造数" },
  { fieldName: "dayNum217", showName: "制造数" },
  { fieldName: "dayNum218", showName: "制造数" },
  { fieldName: "dayNum219", showName: "制造数" },
  { fieldName: "dayNum220", showName: "制造数" },
  { fieldName: "dayNum221", showName: "制造数" },
  { fieldName: "dayNum222", showName: "制造数" },
  { fieldName: "dayNum223", showName: "制造数" },
  { fieldName: "dayNum224", showName: "制造数" },
  { fieldName: "dayNum225", showName: "制造数" },
  { fieldName: "dayNum226", showName: "制造数" },
  { fieldName: "dayNum227", showName: "制造数" },
  { fieldName: "dayNum228", showName: "制造数" },
  { fieldName: "dayNum229", showName: "制造数" },
  { fieldName: "dayNum230", showName: "制造数" },
  { fieldName: "dayNum231", showName: "制造数" },
  { fieldName: "dayNum232", showName: "制造数" },
  { fieldName: "dayNum233", showName: "制造数" },
  { fieldName: "dayNum234", showName: "制造数" },
  { fieldName: "dayNum235", showName: "制造数" },
  { fieldName: "dayNum236", showName: "制造数" },
  { fieldName: "dayNum237", showName: "制造数" },
  { fieldName: "dayNum238", showName: "制造数" },
  { fieldName: "dayNum239", showName: "制造数" },
  { fieldName: "dayNum240", showName: "制造数" },
  { fieldName: "dayNum241", showName: "制造数" },
  { fieldName: "dayNum242", showName: "制造数" },
  { fieldName: "dayNum243", showName: "制造数" },
  { fieldName: "dayNum244", showName: "制造数" },
  { fieldName: "dayNum245", showName: "制造数" },
  { fieldName: "dayNum246", showName: "制造数" },
  { fieldName: "dayNum247", showName: "制造数" },
  { fieldName: "dayNum248", showName: "制造数" },
  { fieldName: "dayNum249", showName: "制造数" },
  { fieldName: "dayNum250", showName: "制造数" },
  { fieldName: "dayNum251", showName: "制造数" },
  { fieldName: "dayNum252", showName: "制造数" },
  { fieldName: "dayNum253", showName: "制造数" },
  { fieldName: "dayNum254", showName: "制造数" },
  { fieldName: "dayNum255", showName: "制造数" },
  { fieldName: "dayNum256", showName: "制造数" },
  { fieldName: "dayNum257", showName: "制造数" },
  { fieldName: "dayNum258", showName: "制造数" },
  { fieldName: "dayNum259", showName: "制造数" },
  { fieldName: "dayNum260", showName: "制造数" },
  { fieldName: "dayNum261", showName: "制造数" },
  { fieldName: "dayNum262", showName: "制造数" },
  { fieldName: "dayNum263", showName: "制造数" },
  { fieldName: "dayNum264", showName: "制造数" },
  { fieldName: "dayNum265", showName: "制造数" },
  { fieldName: "dayNum266", showName: "制造数" },
  { fieldName: "dayNum267", showName: "制造数" },
  { fieldName: "dayNum268", showName: "制造数" },
  { fieldName: "dayNum269", showName: "制造数" },
  { fieldName: "dayNum270", showName: "制造数" },
  { fieldName: "dayNum271", showName: "制造数" },
  { fieldName: "dayNum272", showName: "制造数" },
  { fieldName: "dayNum273", showName: "制造数" },
  { fieldName: "dayNum274", showName: "制造数" },
  { fieldName: "dayNum275", showName: "制造数" },
  { fieldName: "dayNum276", showName: "制造数" },
  { fieldName: "dayNum277", showName: "制造数" },
  { fieldName: "dayNum278", showName: "制造数" },
  { fieldName: "dayNum279", showName: "制造数" },
  { fieldName: "dayNum280", showName: "制造数" },
  { fieldName: "dayNum281", showName: "制造数" },
  { fieldName: "dayNum282", showName: "制造数" },
  { fieldName: "dayNum283", showName: "制造数" },
  { fieldName: "dayNum284", showName: "制造数" },
  { fieldName: "dayNum285", showName: "制造数" },
  { fieldName: "dayNum286", showName: "制造数" },
  { fieldName: "dayNum287", showName: "制造数" },
  { fieldName: "dayNum288", showName: "制造数" },
  { fieldName: "dayNum289", showName: "制造数" },
  { fieldName: "dayNum290", showName: "制造数" },
  { fieldName: "dayNum291", showName: "制造数" },
  { fieldName: "dayNum292", showName: "制造数" },
  { fieldName: "dayNum293", showName: "制造数" },
  { fieldName: "dayNum294", showName: "制造数" },
  { fieldName: "dayNum295", showName: "制造数" },
  { fieldName: "dayNum296", showName: "制造数" },
  { fieldName: "dayNum297", showName: "制造数" },
  { fieldName: "dayNum298", showName: "制造数" },
  { fieldName: "dayNum299", showName: "制造数" },
  { fieldName: "dayNum300", showName: "制造数" },
  { fieldName: "dayNum301", showName: "制造数" },
  { fieldName: "dayNum302", showName: "制造数" },
  { fieldName: "dayNum303", showName: "制造数" },
  { fieldName: "dayNum304", showName: "制造数" },
  { fieldName: "dayNum305", showName: "制造数" },
  { fieldName: "dayNum306", showName: "制造数" },
  { fieldName: "dayNum307", showName: "制造数" },
  { fieldName: "dayNum308", showName: "制造数" },
  { fieldName: "dayNum309", showName: "制造数" },
  { fieldName: "dayNum310", showName: "制造数" },
  { fieldName: "dayNum311", showName: "制造数" },
  { fieldName: "dayNum312", showName: "制造数" },
  { fieldName: "dayNum313", showName: "制造数" },
  { fieldName: "dayNum314", showName: "制造数" },
  { fieldName: "dayNum315", showName: "制造数" },
  { fieldName: "dayNum316", showName: "制造数" },
  { fieldName: "dayNum317", showName: "制造数" },
  { fieldName: "dayNum318", showName: "制造数" },
  { fieldName: "dayNum319", showName: "制造数" },
  { fieldName: "dayNum320", showName: "制造数" },
  { fieldName: "dayNum321", showName: "制造数" },
  { fieldName: "dayNum322", showName: "制造数" },
  { fieldName: "dayNum323", showName: "制造数" },
  { fieldName: "dayNum324", showName: "制造数" },
  { fieldName: "dayNum325", showName: "制造数" },
  { fieldName: "dayNum326", showName: "制造数" },
  { fieldName: "dayNum327", showName: "制造数" },
  { fieldName: "dayNum328", showName: "制造数" },
  { fieldName: "dayNum329", showName: "制造数" },
  { fieldName: "dayNum330", showName: "制造数" },
  { fieldName: "dayNum331", showName: "制造数" },
  { fieldName: "dayNum332", showName: "制造数" },
  { fieldName: "dayNum333", showName: "制造数" },
  { fieldName: "dayNum334", showName: "制造数" },
  { fieldName: "dayNum335", showName: "制造数" },
  { fieldName: "dayNum336", showName: "制造数" },
  { fieldName: "dayNum337", showName: "制造数" },
  { fieldName: "dayNum338", showName: "制造数" },
  { fieldName: "dayNum339", showName: "制造数" },
  { fieldName: "dayNum340", showName: "制造数" },
  { fieldName: "dayNum341", showName: "制造数" },
  { fieldName: "dayNum342", showName: "制造数" },
  { fieldName: "dayNum343", showName: "制造数" },
  { fieldName: "dayNum344", showName: "制造数" },
  { fieldName: "dayNum345", showName: "制造数" },
  { fieldName: "dayNum346", showName: "制造数" },
  { fieldName: "dayNum347", showName: "制造数" },
  { fieldName: "dayNum348", showName: "制造数" },
  { fieldName: "dayNum349", showName: "制造数" },
  { fieldName: "dayNum350", showName: "制造数" },
  { fieldName: "dayNum351", showName: "制造数" },
  { fieldName: "dayNum352", showName: "制造数" },
  { fieldName: "dayNum353", showName: "制造数" },
  { fieldName: "dayNum354", showName: "制造数" },
  { fieldName: "dayNum355", showName: "制造数" },
  { fieldName: "dayNum356", showName: "制造数" },
  { fieldName: "dayNum357", showName: "制造数" },
  { fieldName: "dayNum358", showName: "制造数" },
  { fieldName: "dayNum359", showName: "制造数" },
  { fieldName: "dayNum360", showName: "制造数" },
  { fieldName: "dayNum361", showName: "制造数" },
  { fieldName: "dayNum362", showName: "制造数" },
  { fieldName: "dayNum363", showName: "制造数" },
  { fieldName: "dayNum364", showName: "制造数" },
  { fieldName: "dayNum365", showName: "制造数" },
  { fieldName: "dayNum366", showName: "制造数" },
  { fieldName: "factoryId", showName: "工厂" },
  { fieldName: "makeSaleConfigId", showName: "" },
])


// 获取表格内数据
const getDataList = () => {
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
const editData = (data: any) => {
  // console.info("data ", data)
  tableBarRef.value?.showEditDialog(data.id)
}
// 页面条数变更事件
const handleSizeChange = (val: number) => {
  currentPageSize.value = val
  getDataList()
}
// 页面变更事件
const handleCurrentChange = (val: number) => {
  currentPageNum.value = val
  getDataList()
}
// 表格选中事件
const handleSelectionChange = (val: ApsGoodsForecastMakeProjectData[]) => {
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
        <el-form-item label="商品" prop="goodsId">
          <el-input v-model="queryForm.goodsId" clearable placeholder="请输入商品ID" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="makeMonthId">
          <el-input v-model="queryForm.makeMonthId" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="projectConfigCode">
          <el-input v-model="queryForm.projectConfigCode" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="year">
          <el-input v-model="queryForm.year" clearable placeholder="请输入${column.comment}" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum1">
          <el-input v-model="queryForm.dayNum1" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum2">
          <el-input v-model="queryForm.dayNum2" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum3">
          <el-input v-model="queryForm.dayNum3" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum4">
          <el-input v-model="queryForm.dayNum4" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum5">
          <el-input v-model="queryForm.dayNum5" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum6">
          <el-input v-model="queryForm.dayNum6" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum7">
          <el-input v-model="queryForm.dayNum7" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum8">
          <el-input v-model="queryForm.dayNum8" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum9">
          <el-input v-model="queryForm.dayNum9" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum10">
          <el-input v-model="queryForm.dayNum10" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum11">
          <el-input v-model="queryForm.dayNum11" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum12">
          <el-input v-model="queryForm.dayNum12" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum13">
          <el-input v-model="queryForm.dayNum13" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum14">
          <el-input v-model="queryForm.dayNum14" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum15">
          <el-input v-model="queryForm.dayNum15" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum16">
          <el-input v-model="queryForm.dayNum16" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum17">
          <el-input v-model="queryForm.dayNum17" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum18">
          <el-input v-model="queryForm.dayNum18" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum19">
          <el-input v-model="queryForm.dayNum19" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum20">
          <el-input v-model="queryForm.dayNum20" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum21">
          <el-input v-model="queryForm.dayNum21" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum22">
          <el-input v-model="queryForm.dayNum22" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum23">
          <el-input v-model="queryForm.dayNum23" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum24">
          <el-input v-model="queryForm.dayNum24" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum25">
          <el-input v-model="queryForm.dayNum25" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum26">
          <el-input v-model="queryForm.dayNum26" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum27">
          <el-input v-model="queryForm.dayNum27" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum28">
          <el-input v-model="queryForm.dayNum28" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum29">
          <el-input v-model="queryForm.dayNum29" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum30">
          <el-input v-model="queryForm.dayNum30" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum31">
          <el-input v-model="queryForm.dayNum31" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum32">
          <el-input v-model="queryForm.dayNum32" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum33">
          <el-input v-model="queryForm.dayNum33" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum34">
          <el-input v-model="queryForm.dayNum34" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum35">
          <el-input v-model="queryForm.dayNum35" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum36">
          <el-input v-model="queryForm.dayNum36" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum37">
          <el-input v-model="queryForm.dayNum37" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum38">
          <el-input v-model="queryForm.dayNum38" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum39">
          <el-input v-model="queryForm.dayNum39" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum40">
          <el-input v-model="queryForm.dayNum40" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum41">
          <el-input v-model="queryForm.dayNum41" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum42">
          <el-input v-model="queryForm.dayNum42" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum43">
          <el-input v-model="queryForm.dayNum43" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum44">
          <el-input v-model="queryForm.dayNum44" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum45">
          <el-input v-model="queryForm.dayNum45" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum46">
          <el-input v-model="queryForm.dayNum46" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum47">
          <el-input v-model="queryForm.dayNum47" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum48">
          <el-input v-model="queryForm.dayNum48" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum49">
          <el-input v-model="queryForm.dayNum49" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum50">
          <el-input v-model="queryForm.dayNum50" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum51">
          <el-input v-model="queryForm.dayNum51" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum52">
          <el-input v-model="queryForm.dayNum52" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum53">
          <el-input v-model="queryForm.dayNum53" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum54">
          <el-input v-model="queryForm.dayNum54" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum55">
          <el-input v-model="queryForm.dayNum55" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum56">
          <el-input v-model="queryForm.dayNum56" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum57">
          <el-input v-model="queryForm.dayNum57" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum58">
          <el-input v-model="queryForm.dayNum58" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum59">
          <el-input v-model="queryForm.dayNum59" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum60">
          <el-input v-model="queryForm.dayNum60" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum61">
          <el-input v-model="queryForm.dayNum61" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum62">
          <el-input v-model="queryForm.dayNum62" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum63">
          <el-input v-model="queryForm.dayNum63" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum64">
          <el-input v-model="queryForm.dayNum64" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum65">
          <el-input v-model="queryForm.dayNum65" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum66">
          <el-input v-model="queryForm.dayNum66" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum67">
          <el-input v-model="queryForm.dayNum67" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum68">
          <el-input v-model="queryForm.dayNum68" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum69">
          <el-input v-model="queryForm.dayNum69" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum70">
          <el-input v-model="queryForm.dayNum70" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum71">
          <el-input v-model="queryForm.dayNum71" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum72">
          <el-input v-model="queryForm.dayNum72" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum73">
          <el-input v-model="queryForm.dayNum73" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum74">
          <el-input v-model="queryForm.dayNum74" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum75">
          <el-input v-model="queryForm.dayNum75" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum76">
          <el-input v-model="queryForm.dayNum76" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum77">
          <el-input v-model="queryForm.dayNum77" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum78">
          <el-input v-model="queryForm.dayNum78" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum79">
          <el-input v-model="queryForm.dayNum79" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum80">
          <el-input v-model="queryForm.dayNum80" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum81">
          <el-input v-model="queryForm.dayNum81" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum82">
          <el-input v-model="queryForm.dayNum82" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum83">
          <el-input v-model="queryForm.dayNum83" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum84">
          <el-input v-model="queryForm.dayNum84" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum85">
          <el-input v-model="queryForm.dayNum85" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum86">
          <el-input v-model="queryForm.dayNum86" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum87">
          <el-input v-model="queryForm.dayNum87" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum88">
          <el-input v-model="queryForm.dayNum88" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum89">
          <el-input v-model="queryForm.dayNum89" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum90">
          <el-input v-model="queryForm.dayNum90" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum91">
          <el-input v-model="queryForm.dayNum91" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum92">
          <el-input v-model="queryForm.dayNum92" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum93">
          <el-input v-model="queryForm.dayNum93" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum94">
          <el-input v-model="queryForm.dayNum94" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum95">
          <el-input v-model="queryForm.dayNum95" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum96">
          <el-input v-model="queryForm.dayNum96" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum97">
          <el-input v-model="queryForm.dayNum97" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum98">
          <el-input v-model="queryForm.dayNum98" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum99">
          <el-input v-model="queryForm.dayNum99" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum100">
          <el-input v-model="queryForm.dayNum100" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum101">
          <el-input v-model="queryForm.dayNum101" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum102">
          <el-input v-model="queryForm.dayNum102" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum103">
          <el-input v-model="queryForm.dayNum103" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum104">
          <el-input v-model="queryForm.dayNum104" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum105">
          <el-input v-model="queryForm.dayNum105" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum106">
          <el-input v-model="queryForm.dayNum106" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum107">
          <el-input v-model="queryForm.dayNum107" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum108">
          <el-input v-model="queryForm.dayNum108" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum109">
          <el-input v-model="queryForm.dayNum109" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum110">
          <el-input v-model="queryForm.dayNum110" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum111">
          <el-input v-model="queryForm.dayNum111" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum112">
          <el-input v-model="queryForm.dayNum112" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum113">
          <el-input v-model="queryForm.dayNum113" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum114">
          <el-input v-model="queryForm.dayNum114" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum115">
          <el-input v-model="queryForm.dayNum115" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum116">
          <el-input v-model="queryForm.dayNum116" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum117">
          <el-input v-model="queryForm.dayNum117" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum118">
          <el-input v-model="queryForm.dayNum118" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum119">
          <el-input v-model="queryForm.dayNum119" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum120">
          <el-input v-model="queryForm.dayNum120" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum121">
          <el-input v-model="queryForm.dayNum121" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum122">
          <el-input v-model="queryForm.dayNum122" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum123">
          <el-input v-model="queryForm.dayNum123" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum124">
          <el-input v-model="queryForm.dayNum124" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum125">
          <el-input v-model="queryForm.dayNum125" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum126">
          <el-input v-model="queryForm.dayNum126" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum127">
          <el-input v-model="queryForm.dayNum127" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum128">
          <el-input v-model="queryForm.dayNum128" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum129">
          <el-input v-model="queryForm.dayNum129" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum130">
          <el-input v-model="queryForm.dayNum130" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum131">
          <el-input v-model="queryForm.dayNum131" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum132">
          <el-input v-model="queryForm.dayNum132" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum133">
          <el-input v-model="queryForm.dayNum133" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum134">
          <el-input v-model="queryForm.dayNum134" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum135">
          <el-input v-model="queryForm.dayNum135" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum136">
          <el-input v-model="queryForm.dayNum136" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum137">
          <el-input v-model="queryForm.dayNum137" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum138">
          <el-input v-model="queryForm.dayNum138" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum139">
          <el-input v-model="queryForm.dayNum139" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum140">
          <el-input v-model="queryForm.dayNum140" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum141">
          <el-input v-model="queryForm.dayNum141" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum142">
          <el-input v-model="queryForm.dayNum142" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum143">
          <el-input v-model="queryForm.dayNum143" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum144">
          <el-input v-model="queryForm.dayNum144" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum145">
          <el-input v-model="queryForm.dayNum145" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum146">
          <el-input v-model="queryForm.dayNum146" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum147">
          <el-input v-model="queryForm.dayNum147" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum148">
          <el-input v-model="queryForm.dayNum148" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum149">
          <el-input v-model="queryForm.dayNum149" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum150">
          <el-input v-model="queryForm.dayNum150" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum151">
          <el-input v-model="queryForm.dayNum151" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum152">
          <el-input v-model="queryForm.dayNum152" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum153">
          <el-input v-model="queryForm.dayNum153" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum154">
          <el-input v-model="queryForm.dayNum154" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum155">
          <el-input v-model="queryForm.dayNum155" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum156">
          <el-input v-model="queryForm.dayNum156" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum157">
          <el-input v-model="queryForm.dayNum157" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum158">
          <el-input v-model="queryForm.dayNum158" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum159">
          <el-input v-model="queryForm.dayNum159" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum160">
          <el-input v-model="queryForm.dayNum160" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum161">
          <el-input v-model="queryForm.dayNum161" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum162">
          <el-input v-model="queryForm.dayNum162" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum163">
          <el-input v-model="queryForm.dayNum163" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum164">
          <el-input v-model="queryForm.dayNum164" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum165">
          <el-input v-model="queryForm.dayNum165" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum166">
          <el-input v-model="queryForm.dayNum166" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum167">
          <el-input v-model="queryForm.dayNum167" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum168">
          <el-input v-model="queryForm.dayNum168" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum169">
          <el-input v-model="queryForm.dayNum169" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum170">
          <el-input v-model="queryForm.dayNum170" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum171">
          <el-input v-model="queryForm.dayNum171" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum172">
          <el-input v-model="queryForm.dayNum172" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum173">
          <el-input v-model="queryForm.dayNum173" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum174">
          <el-input v-model="queryForm.dayNum174" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum175">
          <el-input v-model="queryForm.dayNum175" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum176">
          <el-input v-model="queryForm.dayNum176" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum177">
          <el-input v-model="queryForm.dayNum177" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum178">
          <el-input v-model="queryForm.dayNum178" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum179">
          <el-input v-model="queryForm.dayNum179" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum180">
          <el-input v-model="queryForm.dayNum180" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum181">
          <el-input v-model="queryForm.dayNum181" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum182">
          <el-input v-model="queryForm.dayNum182" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum183">
          <el-input v-model="queryForm.dayNum183" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum184">
          <el-input v-model="queryForm.dayNum184" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum185">
          <el-input v-model="queryForm.dayNum185" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum186">
          <el-input v-model="queryForm.dayNum186" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum187">
          <el-input v-model="queryForm.dayNum187" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum188">
          <el-input v-model="queryForm.dayNum188" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum189">
          <el-input v-model="queryForm.dayNum189" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum190">
          <el-input v-model="queryForm.dayNum190" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum191">
          <el-input v-model="queryForm.dayNum191" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum192">
          <el-input v-model="queryForm.dayNum192" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum193">
          <el-input v-model="queryForm.dayNum193" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum194">
          <el-input v-model="queryForm.dayNum194" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum195">
          <el-input v-model="queryForm.dayNum195" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum196">
          <el-input v-model="queryForm.dayNum196" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum197">
          <el-input v-model="queryForm.dayNum197" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum198">
          <el-input v-model="queryForm.dayNum198" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum199">
          <el-input v-model="queryForm.dayNum199" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum200">
          <el-input v-model="queryForm.dayNum200" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum201">
          <el-input v-model="queryForm.dayNum201" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum202">
          <el-input v-model="queryForm.dayNum202" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum203">
          <el-input v-model="queryForm.dayNum203" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum204">
          <el-input v-model="queryForm.dayNum204" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum205">
          <el-input v-model="queryForm.dayNum205" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum206">
          <el-input v-model="queryForm.dayNum206" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum207">
          <el-input v-model="queryForm.dayNum207" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum208">
          <el-input v-model="queryForm.dayNum208" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum209">
          <el-input v-model="queryForm.dayNum209" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum210">
          <el-input v-model="queryForm.dayNum210" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum211">
          <el-input v-model="queryForm.dayNum211" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum212">
          <el-input v-model="queryForm.dayNum212" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum213">
          <el-input v-model="queryForm.dayNum213" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum214">
          <el-input v-model="queryForm.dayNum214" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum215">
          <el-input v-model="queryForm.dayNum215" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum216">
          <el-input v-model="queryForm.dayNum216" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum217">
          <el-input v-model="queryForm.dayNum217" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum218">
          <el-input v-model="queryForm.dayNum218" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum219">
          <el-input v-model="queryForm.dayNum219" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum220">
          <el-input v-model="queryForm.dayNum220" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum221">
          <el-input v-model="queryForm.dayNum221" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum222">
          <el-input v-model="queryForm.dayNum222" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum223">
          <el-input v-model="queryForm.dayNum223" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum224">
          <el-input v-model="queryForm.dayNum224" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum225">
          <el-input v-model="queryForm.dayNum225" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum226">
          <el-input v-model="queryForm.dayNum226" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum227">
          <el-input v-model="queryForm.dayNum227" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum228">
          <el-input v-model="queryForm.dayNum228" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum229">
          <el-input v-model="queryForm.dayNum229" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum230">
          <el-input v-model="queryForm.dayNum230" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum231">
          <el-input v-model="queryForm.dayNum231" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum232">
          <el-input v-model="queryForm.dayNum232" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum233">
          <el-input v-model="queryForm.dayNum233" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum234">
          <el-input v-model="queryForm.dayNum234" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum235">
          <el-input v-model="queryForm.dayNum235" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum236">
          <el-input v-model="queryForm.dayNum236" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum237">
          <el-input v-model="queryForm.dayNum237" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum238">
          <el-input v-model="queryForm.dayNum238" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum239">
          <el-input v-model="queryForm.dayNum239" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum240">
          <el-input v-model="queryForm.dayNum240" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum241">
          <el-input v-model="queryForm.dayNum241" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum242">
          <el-input v-model="queryForm.dayNum242" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum243">
          <el-input v-model="queryForm.dayNum243" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum244">
          <el-input v-model="queryForm.dayNum244" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum245">
          <el-input v-model="queryForm.dayNum245" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum246">
          <el-input v-model="queryForm.dayNum246" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum247">
          <el-input v-model="queryForm.dayNum247" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum248">
          <el-input v-model="queryForm.dayNum248" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum249">
          <el-input v-model="queryForm.dayNum249" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum250">
          <el-input v-model="queryForm.dayNum250" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum251">
          <el-input v-model="queryForm.dayNum251" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum252">
          <el-input v-model="queryForm.dayNum252" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum253">
          <el-input v-model="queryForm.dayNum253" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum254">
          <el-input v-model="queryForm.dayNum254" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum255">
          <el-input v-model="queryForm.dayNum255" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum256">
          <el-input v-model="queryForm.dayNum256" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum257">
          <el-input v-model="queryForm.dayNum257" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum258">
          <el-input v-model="queryForm.dayNum258" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum259">
          <el-input v-model="queryForm.dayNum259" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum260">
          <el-input v-model="queryForm.dayNum260" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum261">
          <el-input v-model="queryForm.dayNum261" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum262">
          <el-input v-model="queryForm.dayNum262" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum263">
          <el-input v-model="queryForm.dayNum263" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum264">
          <el-input v-model="queryForm.dayNum264" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum265">
          <el-input v-model="queryForm.dayNum265" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum266">
          <el-input v-model="queryForm.dayNum266" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum267">
          <el-input v-model="queryForm.dayNum267" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum268">
          <el-input v-model="queryForm.dayNum268" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum269">
          <el-input v-model="queryForm.dayNum269" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum270">
          <el-input v-model="queryForm.dayNum270" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum271">
          <el-input v-model="queryForm.dayNum271" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum272">
          <el-input v-model="queryForm.dayNum272" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum273">
          <el-input v-model="queryForm.dayNum273" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum274">
          <el-input v-model="queryForm.dayNum274" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum275">
          <el-input v-model="queryForm.dayNum275" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum276">
          <el-input v-model="queryForm.dayNum276" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum277">
          <el-input v-model="queryForm.dayNum277" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum278">
          <el-input v-model="queryForm.dayNum278" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum279">
          <el-input v-model="queryForm.dayNum279" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum280">
          <el-input v-model="queryForm.dayNum280" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum281">
          <el-input v-model="queryForm.dayNum281" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum282">
          <el-input v-model="queryForm.dayNum282" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum283">
          <el-input v-model="queryForm.dayNum283" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum284">
          <el-input v-model="queryForm.dayNum284" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum285">
          <el-input v-model="queryForm.dayNum285" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum286">
          <el-input v-model="queryForm.dayNum286" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum287">
          <el-input v-model="queryForm.dayNum287" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum288">
          <el-input v-model="queryForm.dayNum288" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum289">
          <el-input v-model="queryForm.dayNum289" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum290">
          <el-input v-model="queryForm.dayNum290" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum291">
          <el-input v-model="queryForm.dayNum291" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum292">
          <el-input v-model="queryForm.dayNum292" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum293">
          <el-input v-model="queryForm.dayNum293" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum294">
          <el-input v-model="queryForm.dayNum294" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum295">
          <el-input v-model="queryForm.dayNum295" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum296">
          <el-input v-model="queryForm.dayNum296" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum297">
          <el-input v-model="queryForm.dayNum297" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum298">
          <el-input v-model="queryForm.dayNum298" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum299">
          <el-input v-model="queryForm.dayNum299" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum300">
          <el-input v-model="queryForm.dayNum300" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum301">
          <el-input v-model="queryForm.dayNum301" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum302">
          <el-input v-model="queryForm.dayNum302" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum303">
          <el-input v-model="queryForm.dayNum303" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum304">
          <el-input v-model="queryForm.dayNum304" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum305">
          <el-input v-model="queryForm.dayNum305" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum306">
          <el-input v-model="queryForm.dayNum306" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum307">
          <el-input v-model="queryForm.dayNum307" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum308">
          <el-input v-model="queryForm.dayNum308" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum309">
          <el-input v-model="queryForm.dayNum309" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum310">
          <el-input v-model="queryForm.dayNum310" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum311">
          <el-input v-model="queryForm.dayNum311" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum312">
          <el-input v-model="queryForm.dayNum312" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum313">
          <el-input v-model="queryForm.dayNum313" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum314">
          <el-input v-model="queryForm.dayNum314" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum315">
          <el-input v-model="queryForm.dayNum315" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum316">
          <el-input v-model="queryForm.dayNum316" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum317">
          <el-input v-model="queryForm.dayNum317" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum318">
          <el-input v-model="queryForm.dayNum318" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum319">
          <el-input v-model="queryForm.dayNum319" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum320">
          <el-input v-model="queryForm.dayNum320" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum321">
          <el-input v-model="queryForm.dayNum321" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum322">
          <el-input v-model="queryForm.dayNum322" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum323">
          <el-input v-model="queryForm.dayNum323" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum324">
          <el-input v-model="queryForm.dayNum324" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum325">
          <el-input v-model="queryForm.dayNum325" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum326">
          <el-input v-model="queryForm.dayNum326" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum327">
          <el-input v-model="queryForm.dayNum327" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum328">
          <el-input v-model="queryForm.dayNum328" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum329">
          <el-input v-model="queryForm.dayNum329" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum330">
          <el-input v-model="queryForm.dayNum330" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum331">
          <el-input v-model="queryForm.dayNum331" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum332">
          <el-input v-model="queryForm.dayNum332" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum333">
          <el-input v-model="queryForm.dayNum333" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum334">
          <el-input v-model="queryForm.dayNum334" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum335">
          <el-input v-model="queryForm.dayNum335" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum336">
          <el-input v-model="queryForm.dayNum336" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum337">
          <el-input v-model="queryForm.dayNum337" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum338">
          <el-input v-model="queryForm.dayNum338" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum339">
          <el-input v-model="queryForm.dayNum339" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum340">
          <el-input v-model="queryForm.dayNum340" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum341">
          <el-input v-model="queryForm.dayNum341" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum342">
          <el-input v-model="queryForm.dayNum342" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum343">
          <el-input v-model="queryForm.dayNum343" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum344">
          <el-input v-model="queryForm.dayNum344" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum345">
          <el-input v-model="queryForm.dayNum345" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum346">
          <el-input v-model="queryForm.dayNum346" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum347">
          <el-input v-model="queryForm.dayNum347" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum348">
          <el-input v-model="queryForm.dayNum348" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum349">
          <el-input v-model="queryForm.dayNum349" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum350">
          <el-input v-model="queryForm.dayNum350" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum351">
          <el-input v-model="queryForm.dayNum351" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum352">
          <el-input v-model="queryForm.dayNum352" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum353">
          <el-input v-model="queryForm.dayNum353" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum354">
          <el-input v-model="queryForm.dayNum354" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum355">
          <el-input v-model="queryForm.dayNum355" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum356">
          <el-input v-model="queryForm.dayNum356" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum357">
          <el-input v-model="queryForm.dayNum357" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum358">
          <el-input v-model="queryForm.dayNum358" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum359">
          <el-input v-model="queryForm.dayNum359" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum360">
          <el-input v-model="queryForm.dayNum360" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum361">
          <el-input v-model="queryForm.dayNum361" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum362">
          <el-input v-model="queryForm.dayNum362" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum363">
          <el-input v-model="queryForm.dayNum363" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum364">
          <el-input v-model="queryForm.dayNum364" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum365">
          <el-input v-model="queryForm.dayNum365" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="制造数" prop="dayNum366">
          <el-input v-model="queryForm.dayNum366" clearable placeholder="请输入制造数" />
        </el-form-item>
        <el-form-item label="工厂" prop="factoryId">
          <el-input v-model="queryForm.factoryId" clearable placeholder="请输入工厂ID" />
        </el-form-item>
        <el-form-item label="${column.comment}" prop="makeSaleConfigId">
          <el-input v-model="queryForm.makeSaleConfigId" clearable placeholder="请输入${column.comment}" />
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

