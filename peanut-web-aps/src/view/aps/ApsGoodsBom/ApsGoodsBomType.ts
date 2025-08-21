import { KVEntity, Result } from "@@/utils/common-js.ts";
import { request } from "@/http/axios.ts";

export interface ApsGoodsBom {
  goodsId?: string | undefined
  groupId?: string | undefined
  bomId?: string | undefined
  bomCode?: string | undefined
  bomName?: string | undefined
  bomUsage?: string | undefined
  bomUnit?: string | undefined
  bomCostPrice?: string | undefined
  bomCostPriceUnit?: string | undefined
  bomUseWorkStation?: string | undefined
  bomUseExpression?: string | undefined
  bomInventory?: string | undefined
  isFollow?: false | undefined
  factoryId?: string | undefined
  id?: string | undefined
}

export const isFollowList: KVEntity [] = [
  {
    label: "是",
    value: true
  },
  {
    label: "否",
    value: false
  }
]

interface CheckRes {
  isSuccess: boolean
}

export function checkBomUseExpressionFun(bomUseExpression: string): Promise<boolean> {
  return request<Result<CheckRes>>({
    url: "/apsGoodsBom/check/bomUseExpression",
    method: "post",
    data: {
      bomUseExpression
    }
  }).then((r) => {
    console.info("bomUseExpression ", bomUseExpression, r)
    return r.data.isSuccess
  })
}
