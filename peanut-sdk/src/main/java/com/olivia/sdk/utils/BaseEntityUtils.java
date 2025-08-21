package com.olivia.sdk.utils;

import java.util.Objects;

public class BaseEntityUtils {

  public static <T extends BaseEntity<T>> void clearCreateInfo(BaseEntity<T> baseEntity) {
    if (Objects.isNull(baseEntity)) {
      return;
    }
    baseEntity.setCreateTime(null);
    baseEntity.setCreateBy(null);
    clearTraceId(baseEntity);
  }

  public static <T extends BaseEntity<T>> void clearUpdateInfo(BaseEntity<T> baseEntity) {
    if (Objects.isNull(baseEntity)) {
      return;
    }
    baseEntity.setUpdateBy(null);
    baseEntity.setUpdateTime(null);
    clearTraceId(baseEntity);
  }

  private static <T extends BaseEntity<T>> void clearTraceId(BaseEntity<T> baseEntity) {
    baseEntity.setTraceId(null);
  }

  public static <T extends BaseEntity<T>> void clearAllInfo(BaseEntity<T> baseEntity) {
    clearCreateInfo(baseEntity);
    clearUpdateInfo(baseEntity);
  }
}
