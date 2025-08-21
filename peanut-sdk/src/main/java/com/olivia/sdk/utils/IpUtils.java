package com.olivia.sdk.utils;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * IP地址工具类，用于从HTTP请求中获取客户端真实IP地址 支持解析多种代理头信息，适配不同的代理服务器环境
 */
public final class IpUtils {

  /**
   * 私有构造函数，防止工具类实例化
   */
  private IpUtils() {
    throw new AssertionError("工具类不允许实例化");
  }

  /**
   * 从HTTP请求中获取客户端真实IP地址 优先解析代理相关头信息，最后使用请求的远程地址
   *
   * @param request HTTP请求对象
   * @return 客户端真实IP地址
   * @throws NullPointerException 如果request为null
   */
  public static String getClientIp(HttpServletRequest request) {
    // JDK21优化：增强参数校验，避免null值
    Objects.requireNonNull(request, "HTTP请求对象不能为空");

    // 1. 优先从X-Forwarded-For头获取（可能包含多个IP，取第一个）
    String xffHeader = request.getHeader("X-Forwarded-For");
    if (isValidIp(xffHeader)) {
      int index = xffHeader.indexOf(',');
      return (index != -1) ? xffHeader.substring(0, index).trim() : xffHeader.trim();
    }

    // 2. 其次检查X-Real-IP头
    String realIp = request.getHeader("X-Real-IP");
    if (isValidIp(realIp)) {
      return realIp;
    }

    // 3. 检查Proxy-Client-IP头（Apache代理）
    String proxyClientIp = request.getHeader("Proxy-Client-IP");
    if (isValidIp(proxyClientIp)) {
      return proxyClientIp;
    }

    // 4. 检查WL-Proxy-Client-IP头（WebLogic代理）
    String wlProxyIp = request.getHeader("WL-Proxy-Client-IP");
    if (isValidIp(wlProxyIp)) {
      return wlProxyIp;
    }

    // 5. 最后使用请求的远程地址
    return request.getRemoteAddr();
  }

  /**
   * 判断IP地址字符串是否有效 有效规则：非null、非空白、不等于"unknown"（不区分大小写）
   *
   * @param ip IP地址字符串
   * @return 如果有效则返回true，否则返回false
   */
  private static boolean isValidIp(String ip) {
    // JDK21优化：使用String.isBlank()替代第三方工具类，减少依赖
    if (ip == null || ip.isBlank()) {
      return false;
    }
    // 检查是否为"unknown"（不区分大小写）
    return !"unknown".equalsIgnoreCase(ip.trim());
  }
}
