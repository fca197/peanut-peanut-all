package com.olivia.sdk.config;

import com.google.common.base.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 重复键持有器 用于在当前线程中存储和获取重复键信息（如数据库唯一键冲突时的键名） 采用ThreadLocal确保线程安全
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE) // 私有构造器，防止实例化
public class DuplicateKeyHolder {

  /**
   * 线程本地变量，存储当前线程的键名 使用JDK 21的ThreadLocal，支持更优的内存管理
   */
  private static final ThreadLocal<String> KEY_HOLDER = new ThreadLocal<>();

  /**
   * 默认键名，当未设置键名或键名为空时使用
   */
  private static final String DEFAULT_KEY = "CODE";

  /**
   * 设置当前线程的键名
   *
   * @param key 键名，可以为null（会被视为未设置）
   */
  public static void set(String key) {
    // 仅在键不为空时设置，避免存储空值
    if (Strings.isNullOrEmpty(key)) {
      KEY_HOLDER.remove();
    } else {
      KEY_HOLDER.set(key);
    }
  }

  /**
   * 获取当前线程的键名，若未设置则返回默认值
   *
   * @return 当前线程的键名或默认值
   */
  public static String get() {
    return get(null);
  }

  /**
   * 获取当前线程的键名，支持指定默认值 优先级：线程中存储的值 > 自定义默认值 > 全局默认值
   *
   * @param defaultValue 自定义默认值，可为null
   * @return 解析后的键名
   */
  public static String get(String defaultValue) {
    String key = KEY_HOLDER.get();

    // 使用Google Guava的Strings工具类处理空值
    if (Strings.isNullOrEmpty(key)) {
      return Strings.isNullOrEmpty(defaultValue) ? DEFAULT_KEY : defaultValue;
    }

    return key;
  }

  /**
   * 清除当前线程的键名，避免线程复用导致的内存泄漏 建议在try-finally块中使用，确保资源释放
   */
  public static void clear() {
    KEY_HOLDER.remove();
  }
}
