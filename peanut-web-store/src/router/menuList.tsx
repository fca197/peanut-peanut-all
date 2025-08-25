import type {RouteRecordRaw} from "vue-router";

const Layouts = () => import("@/layouts/index.vue")

const allMenuList: RouteRecordRaw[] = [
  {
    path: "/StrategicPlanningCenterMain",
    component: Layouts,
    meta: {
      title: "战略中心",
      elIcon: "DataLine"
    },
    children: [
      {
        name: "战略中心",
        path: "/StrategicPlanningCenter",
        component: () => import("@v/store/StrategicPlanningCenter/StrategicPlanningCenter.vue"),
        meta: {
          title: "战略中心",
          elIcon: "DataLine"
        }
      },
      {
        name: "品牌管理",
        path: "/Brand",
        component: () => import("@v/base/TBrand/TBrandIndex.vue"),
        meta: {
          title: "品牌管理",
          elIcon: "Postcard"
        }
      },
    ]
  },
  {
    path: "/StoreBusinessDistrictMain",
    component: Layouts,
    name: "商区配置管理",
    meta: {
      title: "商区管理",
      elIcon: "Crop"
    },
    children: [
      {
        name: "商区管理",
        path: "/StoreBusinessDistrict",
        component: () => import("@v/store/StoreBusinessDistrict/StoreBusinessDistrictIndex.vue"),
        meta: {
          title: "商区管理",
          elIcon: "Crop"
        }
      },
      {
        name: "商区级别管理",
        path: "/StoreBusinessDistrictLevel",
        component: () => import("@v/store/StoreBusinessDistrictLevel/StoreBusinessDistrictLevelIndex.vue"),
        meta: {
          title: "商区级别管理",
          elIcon: "Histogram"
        }
      },
      {
        name: "商区类别管理",
        path: "/StoreBusinessDistrictType",
        component: () => import("@v/store/StoreBusinessDistrictType/StoreBusinessDistrictTypeIndex.vue"),
        meta: {
          title: "商区类别管理",
          elIcon: "Operation"
        }
      },
    ]
  }
]

export function menuList(): RouteRecordRaw[] {
  return allMenuList;
}
