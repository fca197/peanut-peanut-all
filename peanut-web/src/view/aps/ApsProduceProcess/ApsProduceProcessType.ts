import {KVEntity} from "@@/utils/common-js.ts";
import {ApsProduceProcessItem} from "@v/aps/ApsProduceProcessItem/ApsProduceProcessItemType.ts";

export interface ApsProduceProcess {
  produceProcessNo?: string | undefined
  produceProcessName?: string | undefined
  factoryId?: string | undefined
  isDefault?: boolean | undefined
  id?: string | undefined
  produceProcessItemDtoList: ApsProduceProcessItem []
}

export const isDefaultKVList: KVEntity[] = [
  {
    label: "是",
    value: true
  },
  {
    label: "否",
    value: false
  }
]
