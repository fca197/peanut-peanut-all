package com.olivia.sdk.filter;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 登录用户上下文类 用于在当前线程中存储和获取登录用户信息及相关配置 基于ThreadLocal实现线程隔离，确保多线程环境下的数据安全性
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE) // 私有构造函数，防止实例化
public final class LoginUserContext {

  /**
   * 存储当前线程的登录用户信息
   */
  private static final ThreadLocal<LoginUser> USER_CONTEXT = ThreadLocal.withInitial(LoginUser::new);

  /**
   * 标记是否忽略租户ID查询条件
   */
  private static final ThreadLocal<Boolean> IGNORE_TENANT_ID = ThreadLocal.withInitial(() -> Boolean.FALSE);

  /**
   * 标记是否忽略店铺ID查询条件
   */
  private static final ThreadLocal<Boolean> IGNORE_STORE_ID = ThreadLocal.withInitial(() -> Boolean.FALSE);

  /**
   * 获取当前线程的登录用户信息
   *
   * @return 登录用户信息，永远不为null
   */
  public static LoginUser getLoginUser() {
    return USER_CONTEXT.get();
  }

  /**
   * 设置当前线程的登录用户信息
   *
   * @param loginUser 登录用户信息，若为null则初始化空用户
   */
  public static void setLoginUser(LoginUser loginUser) {
    USER_CONTEXT.set(Objects.requireNonNullElseGet(loginUser, LoginUser::new));
  }

  /**
   * 标记忽略租户ID查询条件
   */
  public static void ignoreTenantId() {
    IGNORE_TENANT_ID.set(Boolean.TRUE);
  }

  /**
   * 判断是否需要忽略租户ID查询条件
   *
   * @return true-忽略，false-不忽略
   */
  public static boolean isIgnoreTenantId() {
    return IGNORE_TENANT_ID.get();
  }

  /**
   * 设置是否忽略租户ID查询条件
   *
   * @param ignore 是否忽略
   */
  public static void setIgnoreTenantId(boolean ignore) {
    IGNORE_TENANT_ID.set(ignore);
  }

  /**
   * 判断是否需要忽略店铺ID查询条件
   *
   * @return true-忽略，false-不忽略
   */
  public static boolean isIgnoreStoreId() {
    return IGNORE_STORE_ID.get();
  }

  /**
   * 设置是否忽略店铺ID查询条件
   *
   * @param ignore 是否忽略
   */
  public static void setIgnoreStoreId(boolean ignore) {
    IGNORE_STORE_ID.set(ignore);
  }

  /**
   * 清除当前线程的所有上下文信息 必须在请求处理结束时调用，防止内存泄漏
   */
  public static void clear() {
    USER_CONTEXT.remove();
    IGNORE_TENANT_ID.remove();
    IGNORE_STORE_ID.remove();
  }
}
