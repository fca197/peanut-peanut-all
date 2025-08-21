package com.olivia.sdk.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统退出异常类 用于处理需要导致应用程序终止的严重错误场景 该异常一旦实例化将立即触发系统退出，应谨慎使用
 */
@Slf4j
public class SystemExitException extends RunException {

  /**
   * 私有构造函数，防止无消息的系统退出
   */
  private SystemExitException() {
    super();
  }

  /**
   * 构造一个带消息和原因的系统退出异常 实例化后将立即记录错误日志并终止应用
   *
   * @param message 异常消息
   * @param cause   异常原因
   */
  public SystemExitException(String message, Throwable cause) {
    super(message, cause);
    log.error("严重错误，将终止应用: {}", message, cause);
    exit(1);
  }

  /**
   * 构造一个带原因的系统退出异常 实例化后将立即记录错误日志并终止应用
   *
   * @param cause 异常原因
   */
  public SystemExitException(Throwable cause) {
    super(cause);
    log.error("严重错误，将终止应用", cause);
    exit(1);
  }

  /**
   * 构造一个带错误码、消息和原因的系统退出异常 实例化后将立即记录错误日志并终止应用
   *
   * @param errorCode 错误编码
   * @param message   异常消息
   * @param cause     异常原因
   */
  public SystemExitException(Integer errorCode, String message, Throwable cause) {
    super(errorCode, message, cause);
    log.error("严重错误[错误码: {}]，将终止应用: {}", errorCode, message, cause);
    exit(errorCode);
  }

  /**
   * 执行系统退出操作
   *
   * @param status 退出状态码，非0表示异常退出
   */
  private void exit(int status) {
    // 在退出前可以添加必要的清理操作
    log.info("应用程序将以状态码 {} 退出", status);
    System.exit(status);
  }
}
