package com.olivia.sdk.utils;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 * HTTP请求响应工具类，提供请求/响应对象获取、文件下载流处理和请求参数过滤功能 基于JDK21特性优化，增强空值安全和异常处理
 */
@Slf4j
public final class ReqResUtils {

  /**
   * 私有构造函数，防止工具类实例化
   */
  private ReqResUtils() {
    throw new AssertionError("工具类不允许实例化");
  }

  /**
   * 获取当前请求的HttpServletRequest对象 JDK21优化：使用增强的空值检查，避免AssertionError
   *
   * @return 当前请求的HttpServletRequest对象
   * @throws IllegalStateException 如果无法获取请求属性
   */
  public static HttpServletRequest getRequest() {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (attributes == null) {
      throw new IllegalStateException("无法获取当前请求属性，可能不在Web请求上下文中");
    }
    return attributes.getRequest();
  }

  /**
   * 获取当前请求的HttpServletResponse对象 JDK21优化：使用增强的空值检查，避免AssertionError
   *
   * @return 当前请求的HttpServletResponse对象
   * @throws IllegalStateException 如果无法获取请求属性
   */
  public static HttpServletResponse getResponse() {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (attributes == null) {
      throw new IllegalStateException("无法获取当前请求属性，可能不在Web请求上下文中");
    }
    return attributes.getResponse();
  }

  /**
   * 获取用于文件下载的ServletOutputStream JDK21优化：使用StandardCharsets指定字符集，增强编码安全性
   *
   * @param fileName 下载的文件名
   * @return 用于文件下载的输出流
   * @throws RuntimeException 如果获取输出流失败
   */
  public static ServletOutputStream getOutputStream4downLoad(String fileName) {
    try {
      HttpServletResponse response = getResponse();
      response.setContentType("application/ms-excel;charset=UTF-8");

      // JDK21优化：明确指定字符集，使用try-with-resources思想确保编码正确
      String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
      response.setHeader("Content-Disposition",
          "attachment;filename=\"" + encodedFileName + "\";filename*=UTF-8''" + encodedFileName);

      return response.getOutputStream();
    } catch (Exception e) {
      log.error("获取文件下载输出流失败，文件名: {}", fileName, e);
      throw new RuntimeException("获取下载输出流失败", e);
    }
  }

  /**
   * 过滤请求参数，排除Servlet相关对象 JDK21优化：使用Stream API增强和空集合处理
   *
   * @param args 原始参数数组
   * @return 过滤后的参数列表，不包含HttpServletRequest、HttpServletResponse和MultipartFile类型
   */
  public static List<Object> filterReqArgs(Object[] args) {
    // JDK21优化：使用List.of()返回不可变空列表，避免创建新集合
    if (args == null || args.length == 0) {
      return List.of();
    }

    // JDK21优化：增强的Stream过滤逻辑，类型判断更清晰
    return Arrays.stream(args)
        .filter(arg -> arg != null
            && !(arg instanceof HttpServletRequest)
            && !(arg instanceof HttpServletResponse)
            && !(arg instanceof MultipartFile))
        .toList(); // 返回不可变列表，减少内存占用
  }
}
