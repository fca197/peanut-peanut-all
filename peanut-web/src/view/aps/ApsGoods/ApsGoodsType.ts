import {request} from "@/http/axios.ts";
import {Result, ResultPageInfo} from "@@/utils/common-js.ts"

export interface ApsGoods {
  goodsName?: string | undefined
  goodsRemark?: string | undefined
  supplierStatus?: string | undefined
  // 工厂ID
  factoryId?: string | undefined
  // 工艺路线
  processPathId?: string | undefined
  // 制造流水线ID produceProcess
  produceProcessId?: string | undefined | null
  id?: string | undefined
}

export function queryGoodsList(): Promise<ApsGoods[]> {
  return queryGoodsListByFactoryId(undefined)
}

export function queryGoodsListByFactoryId(factoryId: string | undefined): Promise<ApsGoods[]> {
  return request<Result<ResultPageInfo<ApsGoods>>>(
    {
      url: "/apsGoods/queryPageList",
      method: "post",
      data: {
        queryPage: false,
        data: {
          factoryId
        }
      }
    }
  ).then((r) => {
    return r.data.dataList
  })
}
