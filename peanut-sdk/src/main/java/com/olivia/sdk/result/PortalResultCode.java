package com.olivia.sdk.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 门户系统结果码枚举 定义门户系统相关的业务结果码和对应的描述信息 编码规则：100000-199999 范围内为门户系统专用代码
 */
@Getter
@AllArgsConstructor
@Accessors(chain = true)
public enum PortalResultCode {

  /**
   * 用户名密码错误
   */
  LOGIN_ACCOUNT_PWD_ERROR("100001", "用户名密码错误"),

  /**
   * 登录失效或已过期
   */
  LOGIN_ACCOUNT_TOKEN_ERROR("100002", "登陆失效或已过期"),

  /**
   * 账号被锁定
   */
  LOGIN_ACCOUNT_LOCKED("100003", "账号已被锁定，请联系管理员"),

  /**
   * 账号未激活
   */
  LOGIN_ACCOUNT_UNACTIVATED("100004", "账号未激活，请先激活"),

  /**
   * 登录次数超限
   */
  LOGIN_ATTEMPTS_EXCEEDED("100005", "登录失败次数过多，请稍后再试"),

  /**
   * 权限不足
   */
  PERMISSION_DENIED("100101", "没有操作权限，请联系管理员"),

  /**
   * 参数校验失败
   */
  PARAM_VALIDATION_ERROR("100201", "参数校验失败，请检查输入信息");

  /**
   * 结果码
   */
  private final String code;

  /**
   * 结果描述
   */
  private final String message;

  /**
   * 根据结果码获取对应的枚举实例
   *
   * @param code 结果码
   * @return 对应的枚举实例，如果没有匹配的则返回null
   */
  public static PortalResultCode getByCode(String code) {
    for (PortalResultCode resultCode : values()) {
      if (resultCode.code.equals(code)) {
        return resultCode;
      }
    }
    return null;
  }

  /**
   * 检查当前结果码是否为指定的结果码
   *
   * @param code 要检查的结果码
   * @return 如果匹配返回true，否则返回false
   */
  public boolean equalsCode(String code) {
    return this.code.equals(code);
  }
}
