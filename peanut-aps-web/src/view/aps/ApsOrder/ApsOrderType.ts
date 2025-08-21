import { KVEntity, postResultInfoList, Result } from "@@/utils/common-js.ts";
import {request} from "@/http/axios.ts";
import { ApsOrderUser } from "@v/aps/ApsOrderUser/ApsOrderUserType.ts";
import { ApsOrderGoods } from "@v/aps/ApsOrderGoods/ApsOrderGoodsType.ts"
import {
  ApsOrderFieldShowTemplate
} from "@v/aps/ApsOrderFieldShowTemplate/ApsOrderFieldShowTemplateType.ts";

export interface ApsOrder {
  orderNo: string | undefined
  orderRemark: string | undefined
  orderStatus: string | undefined
  orderTotalPrice: string | undefined
  goodsId: string | undefined
  reserveAmount: string | undefined
  reserveDatetime: string | undefined
  finishPayedAmount: string | undefined
  finishPayedDatetime: string | undefined
  makeFinishDate: string | undefined
  actMakeFinishDate: string | undefined
  deliveryDate: string | undefined
  factoryId: string | undefined
  urgencyLevel: number | undefined
  schedulingDate: string | undefined
  orderNoParent: string | undefined
  id: string | undefined
  orderUser?: ApsOrderUser
  goodsList?: ApsOrderGoods[]
  goodsBom?: any
  orderTemplate?: ApsOrderFieldShowTemplate
}

export interface OrderStatusRes {
  dataList: OrderStatus []
}

export interface OrderStatus {
  code: string
  desc: string
}

export function queryOrderStatusList(): Promise<KVEntity[]> {
  return request<Result<OrderStatusRes>>(
    {
      url: "/apsOrder/statusList",
      method: "post",
      data: {}
    }
  ).then((t) => {
    return t.data.dataList.map<KVEntity>((tt) => {
      const ttt: KVEntity = {
        label: tt.desc,
        value: tt.code
      }
      return ttt
    })
  })
}

export function queryOrderFieldList() {
  return postResultInfoList("/apsOrder/orderFieldList", {}) as Promise<KVEntity []>
}
