export interface videoPathInfo {
  url: string
  videoItemList: videoPathItem[]
  pageList: videoPathItem[]
}

export interface videoPathItem {
  name: string
  url: string
}

export const videoPathInfo: videoPathInfo [] = [
  {
    url: "/dashboard",
    videoItemList: [
      {
        name: "首页文档",
        url: "https://solveplan.cn/upload/video/aps-home.mov"
      },
      {
        name: "首页文档2",
        url: "https://solveplan.cn/upload/video/aps-home.mov"
      }
    ],
    pageList: [
      {
        name: "",
        url: ""
      }
    ]
  }
]
