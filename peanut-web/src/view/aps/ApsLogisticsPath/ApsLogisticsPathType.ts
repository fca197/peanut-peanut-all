import {KVEntity} from "@@/utils/common-js.ts";
import {ApsLogisticsPathItem} from "@v/aps/ApsLogisticsPathItem/ApsLogisticsPathItemType.ts";

export interface ApsLogisticsPath {
  logisticsPathCode: string
  logisticsPathName: string
  logisticsPathRemark: string
  isDefault: number
  factoryId: string
  id: string
  apsLogisticsPathItemList: ApsLogisticsPathItem []
}

export const isDefaultList: KVEntity [] = [
  {label: "是", value: 1},
  {label: "否", value: 0}
]
