import {
  ApsMachineWorkstationItem
} from "@v/aps/ApsMachineWorkstationItem/ApsMachineWorkstationItemType.ts";
import {postResultInfoList} from "@@/utils/common-js.ts";

export interface ApsMachineWorkstation {
  machineWorkstationNo?: string | undefined
  machineWorkstationName?: string | undefined
  // minPower?: string | undefined
  maxPower?: string | undefined
  factoryId?: string | undefined
  sortIndex?: number | undefined
  id?: string | undefined
  machineWorkstationItemDtoList?: ApsMachineWorkstationItem []
  useTime?: number | undefined
}

export function queryApsMachineWorkstationList(factoryId: string): Promise<ApsMachineWorkstation []> {

  return postResultInfoList("/apsMachineWorkstation/queryPageList",{queryPage: false, data: {factoryId: factoryId}});
}
