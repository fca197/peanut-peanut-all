import { KVEntity, postResultInfoList } from "@@/utils/common-js.ts";

export interface ApsOrderUser {
  orderId?: string | undefined
  userName?: string | undefined
  userPhone?: string | undefined
  userSex?: string | undefined
  countryCode?: string | undefined
  provinceCode?: string | undefined
  cityCode?: string | undefined
  areaCode?: string | undefined
  userAddress?: string | undefined
  userRemark?: string | undefined
  factoryId?: string | undefined
  id?: string | undefined
}

export function queryOrderUserFieldList() {
  return postResultInfoList("/apsOrderUser/orderUserFieldList", {}) as Promise<KVEntity []>
}
