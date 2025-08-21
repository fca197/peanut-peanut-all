import type {RouteRecordRaw} from "vue-router";

const Layouts = () => import("@/layouts/index.vue")

const allMenuList: RouteRecordRaw[] = [
    ]

export function menuList(): RouteRecordRaw[] {
  return allMenuList;
}

