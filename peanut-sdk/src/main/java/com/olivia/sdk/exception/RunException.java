package com.olivia.sdk.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 业务运行时异常类 用于封装业务逻辑中发生的可预期异常，包含错误码和附加信息
 */
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RunException extends RuntimeException {

  /**
   * 错误编码 用于前端根据编码进行不同的错误处理或展示
   */
  private final Integer errorCode;

  /**
   * 异常附加信息 可用于携带额外的错误详情数据
   */
  private final Object retObj;

  /**
   * 构造一个无消息的业务异常
   */
  public RunException() {
    this(500, null, null);
  }

  /**
   * 构造一个带消息的业务异常
   *
   * @param message 异常消息
   */
  public RunException(String message) {
    this(500, message, null);
  }

  /**
   * 构造一个带错误码和消息的业务异常
   *
   * @param errorCode 错误编码
   * @param message   异常消息
   */
  public RunException(Integer errorCode, String message) {
    this(errorCode, message, null);
  }

  /**
   * 构造一个完整的业务异常
   *
   * @param errorCode 错误编码
   * @param message   异常消息
   * @param retObj    附加信息
   */
  public RunException(Integer errorCode, String message, Object retObj) {
    super(message);
    this.errorCode = errorCode;
    this.retObj = retObj;
  }

  /**
   * 构造一个带消息和原因的业务异常
   *
   * @param message 异常消息
   * @param cause   异常原因
   */
  public RunException(String message, Throwable cause) {
    this(null, message, null, cause);
  }

  /**
   * 构造一个带消息、原因和错误码的业务异常
   *
   * @param errorCode 错误编码
   * @param message   异常消息
   * @param cause     异常原因
   */
  public RunException(Integer errorCode, String message, Throwable cause) {
    this(errorCode, message, null, cause);
  }

  /**
   * 构造一个带消息、原因和附加信息的业务异常
   *
   * @param message 异常消息
   * @param retObj  附加信息
   * @param cause   异常原因
   */
  public RunException(String message, Object retObj, Throwable cause) {
    this(null, message, retObj, cause);
  }

  /**
   * 构造一个完整的带原因的业务异常
   *
   * @param errorCode 错误编码
   * @param message   异常消息
   * @param retObj    附加信息
   * @param cause     异常原因
   */
  public RunException(Integer errorCode, String message, Object retObj, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
    this.retObj = retObj;
  }

  /**
   * 构造一个带原因的业务异常
   *
   * @param cause 异常原因
   */
  public RunException(Throwable cause) {
    this(null, cause.getMessage(), null, cause);
  }

  /**
   * 构造一个完整的业务异常（包含抑制和堆栈跟踪配置）
   *
   * @param message            异常消息
   * @param cause              异常原因
   * @param enableSuppression  是否启用抑制
   * @param writableStackTrace 是否生成可写的堆栈跟踪
   */
  protected RunException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    this(null, message, null, cause, enableSuppression, writableStackTrace);
  }

  /**
   * 构造一个全参数的业务异常（包含抑制和堆栈跟踪配置）
   *
   * @param errorCode          错误编码
   * @param message            异常消息
   * @param retObj             附加信息
   * @param cause              异常原因
   * @param enableSuppression  是否启用抑制
   * @param writableStackTrace 是否生成可写的堆栈跟踪
   */
  protected RunException(Integer errorCode, String message, Object retObj, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.errorCode = errorCode;
    this.retObj = retObj;
  }
}
