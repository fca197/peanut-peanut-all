import type {RouteRecordRaw} from "vue-router";

const Layouts = () => import("@/layouts/index.vue")

const allMenuList: RouteRecordRaw[] = [
  {
    path: "/StrategicPlanningCenter",
    component: Layouts,
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

    ]
  }
]

export function menuList(): RouteRecordRaw[] {
  return allMenuList;
}
