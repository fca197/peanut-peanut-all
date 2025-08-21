import {KVEntity} from "@@/utils/common-js.ts";
import {ApsProcessPathRoom} from "@v/aps/ApsProcessPathRoom/ApsProcessPathRoomType.ts";

export interface ApsProcessPath {
  processPathCode: string | undefined
  processPathName: string | undefined
  processPathRemark: string | undefined
  isDefault: boolean | undefined
  factoryId: string | undefined
  id: string | undefined
  pathRoomList: ApsProcessPathRoom []
}

export const isDefaultList: KVEntity[] = [
  {label: "是", value: true},
  {label: "否", value: false}
]
