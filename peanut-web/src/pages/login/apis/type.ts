export interface LoginRequestData {
  /** admin 或 editor */
  loginPhone: string
  /** 密码 */
  pwd: string
}

export type CaptchaResponseData = ApiResponseData<string>

export type LoginResponseData = ApiResponseData<{ token: string }>
