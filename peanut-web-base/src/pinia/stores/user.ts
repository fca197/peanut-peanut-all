import {pinia} from "@/pinia"
import {resetRouter} from "@/router"
import {getCurrentUserApi, getCurrentUserResourceList} from "@@/apis/users"
import {getToken, removeToken, setKeyValue, setToken as _setToken} from "@@/utils/cache/cookies"
import {useSettingsStore} from "./settings"
import {useTagsViewStore} from "./tags-view"

export const useUserStore = defineStore("user", () => {
  const token = ref<string>(getToken() || "")
  const roles = ref<string[]>([])
  const username = ref<string>("")
  const loginPhone = ref<string>("")

  const tagsViewStore = useTagsViewStore()
  const settingsStore = useSettingsStore()

  // 设置 Token
  const setToken = (value: string) => {
    _setToken(value)
    token.value = value
  }

  // 获取用户详情
  const getUserInfo = async () => {
    const {data} = await getCurrentUserApi()
    username.value = data.userName;
    setKeyValue("userName", username.value);
    loginPhone.value = data.loginPhone
    // 验证返回的 roles 是否为一个非空数组，否则塞入一个没有任何作用的默认角色，防止路由守卫逻辑进入无限循环
    // roles.value = routerConfig.defaultRoles
  }

  const getMenuPathList = async () => {
    const {data} = await getCurrentUserResourceList();
    // console.info("getMenuPathList data",data)
    roles.value = data.dataList.map(tt => tt.resourceUrl)
    roles.value.push("")
  }

  // 模拟角色变化
  const changeRoles = (role: string) => {
    const newToken = `token-${role}`
    token.value = newToken
    _setToken(newToken)
    // 用刷新页面代替重新登录
    location.reload()
  }

  // 登出
  const logout = () => {
    removeToken()
    token.value = ""
    roles.value = []
    resetRouter()
    resetTagsView()
  }

  // 重置 Token
  const resetToken = () => {
    removeToken()
    token.value = ""
    roles.value = []
  }

  // 重置 Visited Views 和 Cached Views
  const resetTagsView = () => {
    if (!settingsStore.cacheTagsView) {
      tagsViewStore.delAllVisitedViews()
      tagsViewStore.delAllCachedViews()
    }
  }

  return {
    token,
    roles,
    username,
    setToken,
    getUserInfo,
    changeRoles,
    logout,
    resetToken,
    getMenuPathList
  }
})

/**
 * @description 在 SPA 应用中可用于在 pinia 实例被激活前使用 store
 * @description 在 SSR 应用中可用于在 setup 外使用 store
 */
export function useUserStoreOutside() {
  return useUserStore(pinia)
}
