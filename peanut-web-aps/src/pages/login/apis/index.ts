import type * as Auth from "./type"
import { request } from "@/http/axios"

/** 获取登录验证码 */
export function getCaptchaApi() {

}

/** 登录并返回 Token */
export function loginApi(data: Auth.LoginRequestData) {
  return request<Auth.LoginResponseData>({
    url: "/loginAccount/login/phone/pwd",
    method: "post",
    data
  })
}
