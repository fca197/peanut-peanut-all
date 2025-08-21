import {postResultInfoList} from "@@/utils/common-js.ts";

export interface ApsMachine {
  machineNo: string
  machineName: string
  factoryId: string
  sortIndex: number
  // minPower: number | undefined
  maxPower: number | undefined
  id: string
  machineId?: string | undefined
}

export function queryApsMachineList(factoryId: string) {
  return postResultInfoList("/apsMachine/queryPageList", {
    queryPage: false,
    data: {
      factoryId
    }
  }) as Promise<ApsMachine[]>
}
