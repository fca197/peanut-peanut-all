import {request} from "@/http/axios.ts";

export interface TBrand {
  id?: string | undefined
  tenantId?: string | undefined
  factoryId?: string | undefined
  brandCode?: string | undefined
  brandName?: string | undefined
  brandStatus?: string | undefined
  isDelete?: string | undefined
  createTime?: string | undefined
  createBy?: string | undefined
  updateTime?: string | undefined
  updateBy?: string | undefined
  traceId?: string | undefined
  isUsed?: string | undefined
  versionNum?: string | undefined
  createUserName?: string | undefined
  updateUserName?: string | undefined
}

export function queryBrandList() {
  return request({
    method: "POST",
    url: "/brand/queryList",
    data: {}
  }).then(res => res.data.dataList)
}
