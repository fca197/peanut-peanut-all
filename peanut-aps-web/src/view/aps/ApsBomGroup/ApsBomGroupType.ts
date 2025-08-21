import {request} from "@/http/axios"

import {ResultInfo, ResultPageInfo} from "@@/utils/common-js.ts"
import {list2tree} from "@@/utils/listUtils.ts"

export interface ApsBomGroup {
  groupCode: string
  groupName: string
  parentId: undefined
  pathId: string
  id: string
}

export function queryApsBomGroupTree() {

  return request<ResultInfo<ResultPageInfo<ApsBomGroup>>>({
    url: "/apsBomGroup/queryPageList",
    method: "post",
    data: {
      queryPage: false
    }
  }).then((r) => {
    const dataList = r.data.dataList
    dataList.push({
      groupName: "顶级",
      parentId: undefined,
      id: "0"
    })

    return list2tree(dataList, undefined) as ApsBomGroup []
  })
}

export const apsGroupDefaultProps = {
  children: "children",
  label: "groupName",
  id: "id"
}
