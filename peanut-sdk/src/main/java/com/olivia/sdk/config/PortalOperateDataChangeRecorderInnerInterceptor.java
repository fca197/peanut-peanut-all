//package com.olivia.sdk.config;
//
//import com.baomidou.mybatisplus.extension.plugins.inner.DataChangeRecorderInnerInterceptor;
//import java.util.Objects;
//import lombok.extern.slf4j.Slf4j;
//
/// **
// * 门户操作数据变更记录拦截器 扩展MyBatis-Plus的数据变更记录功能，支持通过ThreadLocal控制是否记录操作日志
// */
//@Slf4j
//public class PortalOperateDataChangeRecorderInnerInterceptor extends DataChangeRecorderInnerInterceptor {
//
//  /**
//   * 线程本地变量，用于控制当前线程是否保存操作日志 默认值为true，即默认记录操作日志
//   */
//  private static final ThreadLocal<Boolean> SAVE_OP_LOG = ThreadLocal.withInitial(() -> Boolean.TRUE);
//
//  /**
//   * 判断当前线程是否需要保存操作日志
//   *
//   * @return 如果需要保存返回true，否则返回false
//   */
//  public static boolean isSaveOpLog() {
//    return Boolean.TRUE.equals(SAVE_OP_LOG.get());
//  }
//
//  /**
//   * 设置当前线程是否保存操作日志
//   *
//   * @param save 是否保存操作日志，null值将被视为false
//   */
//  public static void setSaveOpLog(Boolean save) {
//    SAVE_OP_LOG.set(Boolean.TRUE.equals(save));
//  }
//
//  /**
//   * 清除当前线程的日志记录开关状态 建议在请求处理结束时调用，避免线程复用导致的状态污染
//   */
//  public static void clearSaveOpLog() {
//    SAVE_OP_LOG.remove();
//  }
//
//  /**
//   * 处理操作结果，记录数据变更信息 仅当日志记录开关开启时才进行处理
//   *
//   * @param operationResult 操作结果对象，包含操作类型、变更数据和表名等信息
//   */
//  @Override
//  protected void dealOperationResult(OperationResult operationResult) {
//    // 检查是否需要记录日志
//    if (!isSaveOpLog()) {
//      if (log.isTraceEnabled()) {
//        log.trace("操作日志记录已关闭，忽略记录: {}", operationResult.getTableName());
//      }
//      return;
//    }
//
//    // 验证操作结果对象有效性
//    if (Objects.isNull(operationResult)) {
//      log.warn("处理操作结果失败: OperationResult为null");
//      return;
//    }
//
//    // 提取操作信息
//    String operation = operationResult.getOperation();
//    String changedData = operationResult.getChangedData();
//    String tableName = operationResult.getTableName();
//
//    // 日志输出
//    if (log.isDebugEnabled()) {
//      log.debug("记录数据变更 - 操作: {}, 表名: {}, 变更数据: {}", operation, tableName, changedData);
//    }
//
//    // 可以在这里添加自定义的日志持久化逻辑
//    // 例如：保存到数据库、发送到消息队列等
//  }
//}
