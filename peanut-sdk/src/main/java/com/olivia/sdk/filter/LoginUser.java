package com.olivia.sdk.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 登录用户信息类 存储当前登录用户的基本信息和权限标识
 */
@Getter
@Setter
@Accessors(chain = true)
public class LoginUser {

  /**
   * 租户ID
   */
  private Long tenantId;

  /**
   * 店铺ID
   */
  private Long storeId;

  /**
   * 用户ID
   */
  private Long id;

  /**
   * 登录手机号
   */
  private String loginPhone;

  /**
   * 是否需要掩码显示
   */
  private Boolean isMaskValue;

  /**
   * 用户名
   */
  private String userName;

  /**
   * 是否为管理员
   */
  private Boolean isAdmin;


  /**
   * IP地址
   */
  private String ipAddress;

  /**
   * 设备ID
   */
  private String deviceId;

  /**
   * 登录令牌
   */
  private String token;

  /**
   * 判断当前用户是否为管理员 处理null值情况，默认非管理员
   *
   * @return 如果是管理员返回true，否则返回false
   */
  public boolean isAdmin() {
    return Boolean.TRUE.equals(isAdmin);
  }

  /**
   * 判断是否需要掩码显示 处理null值情况，默认不需要掩码
   *
   * @return 如果需要掩码返回true，否则返回false
   */
  public boolean isMaskValue() {
    return Boolean.TRUE.equals(isMaskValue);
  }

  /**
   * 获取用户ID的字符串表示 处理null值情况，避免NPE
   *
   * @return 用户ID的字符串形式，若ID为null则返回空字符串
   */
  public String getIdStr() {
    return id != null ? id.toString() : "";
  }

  /**
   * 检查用户是否已登录（令牌是否存在）
   *
   * @return 如果已登录返回true，否则返回false
   */
  public boolean isLoggedIn() {
    return token != null && !token.isEmpty();
  }

  /**
   * 获取掩码处理后的手机号 中间4位替换为*
   *
   * @return 掩码后的手机号，若手机号为空则返回空字符串
   */
  public String getMaskedPhone() {
    if (loginPhone == null || loginPhone.length() != 11) {
      return loginPhone == null ? "" : loginPhone;
    }
    return loginPhone.substring(0, 3) + "****" + loginPhone.substring(7);
  }
}
