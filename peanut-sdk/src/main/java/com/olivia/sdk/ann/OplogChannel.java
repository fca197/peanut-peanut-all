package com.olivia.sdk.ann;

/**
 * 操作日志渠道枚举，用于标识产生操作日志的终端类型。
 */
public enum OplogChannel {

  /**
   * PC端（桌面端应用）
   */
  PC,

  /**
   * H5端（移动端网页）
   */
  H5,

  /**
   * 默认渠道（未明确指定时使用）
   */
  DEFAULT
}
