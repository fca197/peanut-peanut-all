import { KVEntity } from "@@/utils/common-js.ts"

export interface ApsBomSupplier {
  bomSupplierName: string | undefined
  bomSupplierCode: string | undefined
  bomSupplierPhone: string | undefined
  bomSupplierTel: string | undefined
  bomSupplierEmail: string | undefined
  provinceCode: string | undefined
  cityCode: string | undefined
  areaCode: string | undefined
  bomSupplierAddr: string | undefined
  bomSupplierRemark: string | undefined
  supplierStatus: number | undefined
  id: string | undefined
}

export const supplierStatusList: KVEntity[] = [
  {
    label: "启用",
    value: 1
  },
  {
    label: "禁用",
    value: 0
  }
]
