package com.olivia.sdk.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 可忽略异常类 用于标识可以被业务逻辑忽略或特殊处理的异常，不中断主要流程 通常用于非致命性错误场景，如次要数据验证失败等
 */
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CanIgnoreException extends RuntimeException {

  /**
   * 异常编码 可用于前端根据编码进行不同的UI展示处理
   */
  private final String code;

  /**
   * 构造一个无消息的可忽略异常
   */
  public CanIgnoreException() {
    this("");
  }

  /**
   * 构造一个带消息的可忽略异常
   *
   * @param message 异常消息
   */
  public CanIgnoreException(String message) {
    this(message, "IGNORE");
  }

  /**
   * 构造一个带消息和原因的可忽略异常
   *
   * @param message 异常消息
   * @param cause   异常原因
   */
  public CanIgnoreException(String message, Throwable cause) {
    this(message, cause, "IGNORE");
  }

  /**
   * 构造一个带原因的可忽略异常
   *
   * @param cause 异常原因
   */
  public CanIgnoreException(Throwable cause) {
    this(cause.getMessage(), cause, "IGNORE");
  }

  /**
   * 构造一个带消息、原因和抑制标志的可忽略异常
   *
   * @param message            异常消息
   * @param cause              异常原因
   * @param enableSuppression  是否启用抑制
   * @param writableStackTrace 是否生成可写的堆栈跟踪
   */
  protected CanIgnoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    this(message, cause, enableSuppression, writableStackTrace, "IGNORE");
  }

  /**
   * 构造一个带消息和编码的可忽略异常
   *
   * @param message 异常消息
   * @param code    异常编码
   */
  public CanIgnoreException(String message, String code) {
    super(message);
    this.code = code;
  }

  /**
   * 构造一个带消息、原因和编码的可忽略异常
   *
   * @param message 异常消息
   * @param cause   异常原因
   * @param code    异常编码
   */
  public CanIgnoreException(String message, Throwable cause, String code) {
    super(message, cause);
    this.code = code;
  }

  /**
   * 构造一个完整的可忽略异常
   *
   * @param message            异常消息
   * @param cause              异常原因
   * @param enableSuppression  是否启用抑制
   * @param writableStackTrace 是否生成可写的堆栈跟踪
   * @param code               异常编码
   */
  protected CanIgnoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.code = code;
  }
}
