package com.olivia.sdk.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.sdk.utils.model.LambdaQuerySelectReq;
import com.olivia.sdk.utils.model.LambdaQueryUtilSelectType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.IntFunction;

/**
 * MyBatis-Plus Lambda查询构建工具类，支持动态生成查询条件 适配LambdaQueryWrapper和MPJLambdaWrapper，支持多种查询类型（eq/gt/like等）
 */

@SuppressWarnings("unchecked")
public final class LambdaQueryUtil {

  /**
   * 私有构造函数，防止工具类实例化
   */
  private LambdaQueryUtil() {
    throw new AssertionError("工具类不允许实例化");
  }

  /**
   * 为LambdaQueryWrapper添加查询条件（默认查询类型）
   *
   * @param lambdaQueryWrapper 查询包装器
   * @param obj                包含查询参数的对象
   * @param teClass            目标实体类
   * @param fieldFuncArr       字段函数数组
   * @param <T>                实体类型
   * @param <TR>               参数对象类型
   * @param <R>                字段类型
   */
  @SafeVarargs
  public static <T, TR, R> void lambdaQueryWrapper(LambdaQueryWrapper<T> lambdaQueryWrapper, TR obj, Class<T> teClass, SFunction<T, R>... fieldFuncArr) {
    if (Objects.isNull(obj) || Objects.isNull(fieldFuncArr)) {
      return;
    }
    T req = $.copy(obj, teClass);
    Arrays.stream(fieldFuncArr).forEach(fieldFunc -> addQueryCondition(lambdaQueryWrapper, req, fieldFunc, null));
  }

  /**
   * 为LambdaQueryWrapper添加指定查询类型的条件
   *
   * @param lambdaQueryWrapper 查询包装器
   * @param obj                包含查询参数的对象
   * @param teClass            目标实体类
   * @param selectType         查询类型
   * @param fieldFuncArr       字段函数数组
   * @param <T>                实体类型
   * @param <TR>               参数对象类型
   * @param <R>                字段类型
   */
  @SafeVarargs
  public static <T, TR, R> void lambdaQueryWrapper(LambdaQueryWrapper<T> lambdaQueryWrapper, TR obj, Class<T> teClass, LambdaQueryUtilSelectType selectType,
      SFunction<T, R>... fieldFuncArr) {
    if (Objects.isNull(obj) || Objects.isNull(selectType) || Objects.isNull(fieldFuncArr)) {
      return;
    }
    LambdaQuerySelectReq<T, R>[] reqList = Arrays.stream(fieldFuncArr).map(fieldFunc -> new LambdaQuerySelectReq<T, R>().setFunction(fieldFunc).setSelectType(selectType))
        .toArray((IntFunction<LambdaQuerySelectReq<T, R>[]>) value -> new LambdaQuerySelectReq[fieldFuncArr.length]);
    lambdaQueryWrapper(lambdaQueryWrapper, obj, teClass, reqList);
  }

  /**
   * 为MPJLambdaWrapper添加指定查询类型的条件
   *
   * @param lambdaQueryWrapper MPJ查询包装器
   * @param obj                包含查询参数的对象
   * @param teClass            目标实体类
   * @param selectType         查询类型
   * @param fieldFuncArr       字段函数数组
   * @param <T>                实体类型
   * @param <TR>               参数对象类型
   * @param <R>                字段类型
   */
  @SafeVarargs
  public static <T, TR, R> void lambdaQueryWrapper(MPJLambdaWrapper<T> lambdaQueryWrapper, TR obj, Class<T> teClass, LambdaQueryUtilSelectType selectType,
      SFunction<T, R>... fieldFuncArr) {
    if (Objects.isNull(obj) || Objects.isNull(selectType) || Objects.isNull(fieldFuncArr)) {
      return;
    }
    LambdaQuerySelectReq<T, R>[] reqList = Arrays.stream(fieldFuncArr).map(fieldFunc -> new LambdaQuerySelectReq<T, R>().setFunction(fieldFunc).setSelectType(selectType))
        .toArray((IntFunction<LambdaQuerySelectReq<T, R>[]>) value -> new LambdaQuerySelectReq[fieldFuncArr.length]);
    lambdaQueryWrapper(lambdaQueryWrapper, obj, teClass, reqList);
  }


  /**
   * 为LambdaQueryWrapper添加自定义查询条件（支持不同字段不同查询类型）
   *
   * @param lambdaQueryWrapper 查询包装器
   * @param obj                包含查询参数的对象
   * @param teClass            目标实体类
   * @param fieldFuncArr       包含字段函数和查询类型的请求数组
   * @param <T>                实体类型
   * @param <TR>               参数对象类型
   * @param <R>                字段类型
   */
  @SafeVarargs
  public static <T, TR, R> void lambdaQueryWrapper(LambdaQueryWrapper<T> lambdaQueryWrapper, TR obj, Class<T> teClass, LambdaQuerySelectReq<T, R>... fieldFuncArr) {
    if (Objects.isNull(obj) || Objects.isNull(fieldFuncArr)) {
      return;
    }
    T req = $.copy(obj, teClass);
    Arrays.stream(fieldFuncArr).forEach(reqItem -> addQueryCondition(lambdaQueryWrapper, req, reqItem.getFunction(), reqItem.getSelectType()));
  }

  /**
   * 为MPJLambdaWrapper添加查询条件（默认查询类型）
   *
   * @param lambdaQueryWrapper MPJ查询包装器
   * @param obj                包含查询参数的对象
   * @param tClass             目标实体类
   * @param fieldFuncArr       字段函数数组
   * @param <T>                实体类型
   * @param <TR>               参数对象类型
   * @param <R>                字段类型
   */
  @SafeVarargs
  public static <T, TR, R> void lambdaQueryWrapper(MPJLambdaWrapper<T> lambdaQueryWrapper, TR obj, Class<T> tClass, SFunction<T, R>... fieldFuncArr) {
    if (Objects.isNull(obj) || Objects.isNull(fieldFuncArr)) {
      return;
    }
    T req = $.copy(obj, tClass);
    Arrays.stream(fieldFuncArr).forEach(fieldFunc -> addQueryCondition(lambdaQueryWrapper, req, fieldFunc, null));
  }

  /**
   * 为MPJLambdaWrapper添加自定义查询条件（支持不同字段不同查询类型）
   *
   * @param lambdaQueryWrapper MPJ查询包装器
   * @param obj                包含查询参数的对象
   * @param tClass             目标实体类
   * @param fieldFuncArr       包含字段函数和查询类型的请求数组
   * @param <T>                实体类型
   * @param <TR>               参数对象类型
   * @param <R>                字段类型
   */
  @SafeVarargs
  public static <T, TR, R> void lambdaQueryWrapper(MPJLambdaWrapper<T> lambdaQueryWrapper, TR obj, Class<T> tClass, LambdaQuerySelectReq<T, R>... fieldFuncArr) {
    if (Objects.isNull(obj) || Objects.isNull(fieldFuncArr)) {
      return;
    }
    T req = $.copy(obj, tClass);
    Arrays.stream(fieldFuncArr).forEach(reqItem -> addQueryCondition(lambdaQueryWrapper, req, reqItem.getFunction(), reqItem.getSelectType()));
  }

  /**
   * 向LambdaQueryWrapper添加单个查询条件
   *
   * @param wrapper   查询包装器
   * @param entity    包含参数的实体对象
   * @param fieldFunc 字段函数
   * @param <T>       实体类型
   * @param <R>       字段类型
   */
  public static <T, R> void lambdaQueryWrapper(LambdaQueryWrapper<T> wrapper, T entity, SFunction<T, R> fieldFunc) {
    addQueryCondition(wrapper, entity, fieldFunc, null);
  }

  /**
   * 向MPJLambdaWrapper添加单个查询条件
   *
   * @param wrapper   MPJ查询包装器
   * @param entity    包含参数的实体对象
   * @param fieldFunc 字段函数
   * @param <T>       实体类型
   * @param <R>       字段类型
   */
  public static <T, R> void lambdaQueryWrapper(MPJLambdaWrapper<T> wrapper, T entity, SFunction<T, R> fieldFunc) {
    addQueryCondition(wrapper, entity, fieldFunc, null);
  }

  /**
   * 通用方法：向查询包装器添加查询条件（支持LambdaQueryWrapper）
   */
  private static <T, R> void addQueryCondition(LambdaQueryWrapper<T> wrapper, T entity, SFunction<T, R> fieldFunc, LambdaQueryUtilSelectType selectType) {
    if (isInvalidCondition(wrapper, entity, fieldFunc, selectType)) {
      return;
    }

    R value = fieldFunc.apply(entity);
    applyQueryCondition(value, selectType, () -> wrapper.eq(fieldFunc, value), () -> wrapper.gt(fieldFunc, value), () -> wrapper.lt(fieldFunc, value),
        () -> wrapper.ge(fieldFunc, value), () -> wrapper.le(fieldFunc, value), () -> wrapper.like(fieldFunc, value), () -> wrapper.in(fieldFunc, value),
        () -> wrapper.isNull(fieldFunc), () -> wrapper.ne(fieldFunc, value), () -> wrapper.likeRight(fieldFunc, value), () -> wrapper.likeLeft(fieldFunc, value));
  }

  /**
   * 通用方法：向查询包装器添加查询条件（支持MPJLambdaWrapper）
   */
  private static <T, R> void addQueryCondition(MPJLambdaWrapper<T> wrapper, T entity, SFunction<T, R> fieldFunc, LambdaQueryUtilSelectType selectType) {
    if (isInvalidCondition(wrapper, entity, fieldFunc, selectType)) {
      return;
    }

    R value = fieldFunc.apply(entity);
    applyQueryCondition(value, selectType, () -> wrapper.eq(fieldFunc, value), () -> wrapper.gt(fieldFunc, value), () -> wrapper.lt(fieldFunc, value),
        () -> wrapper.ge(fieldFunc, value), () -> wrapper.le(fieldFunc, value), () -> wrapper.like(fieldFunc, value), () -> wrapper.in(fieldFunc, value),
        () -> wrapper.isNull(fieldFunc), () -> wrapper.ne(fieldFunc, value), () -> wrapper.likeRight(fieldFunc, value), () -> wrapper.likeLeft(fieldFunc, value));
  }

  /**
   * 检查条件是否有效（空值处理）
   */
  private static <T, R> boolean isInvalidCondition(Object wrapper, T entity, SFunction<T, R> fieldFunc, LambdaQueryUtilSelectType selectType) {
    if (Objects.isNull(wrapper) || Objects.isNull(entity) || Objects.isNull(fieldFunc)) {
      return true;
    }

    R value = fieldFunc.apply(entity);
    // 字符串空值或空白检查
    if (value instanceof String strValue && StringUtils.isBlank(strValue)) {
      return true;
    }
    // 非空查询类型时的空值检查
    return Objects.isNull(value) && !LambdaQueryUtilSelectType.IS_NULL.equals(selectType);
  }

  /**
   * 应用查询条件（使用JDK21增强switch表达式）
   */
  private static <R> void applyQueryCondition(R value, LambdaQueryUtilSelectType selectType, Runnable eq, Runnable gt, Runnable lt, Runnable ge, Runnable le,
      Runnable like, Runnable in, Runnable isNull, Runnable ne, Runnable likeRight, Runnable likeLeft) {
    // JDK21增强：使用switch表达式处理查询类型，代码更简洁
    if (Objects.isNull(selectType)) {
      // 默认查询类型：字符串用likeRight，列表用in，其他用eq
      if (value instanceof String) {
        likeRight.run();
      } else if (value instanceof List<?>) {
        in.run();
      } else {
        eq.run();
      }
      return;
    }

    // JDK21增强：switch支持枚举类型直接匹配，无需显式调用equals
    switch (selectType) {
      case EQ -> eq.run();
      case GT -> gt.run();
      case LT -> lt.run();
      case GE -> ge.run();
      case LE -> le.run();
      case LIKE -> like.run();
      case IN -> in.run();
      case IS_NULL -> isNull.run();
      case NE -> ne.run();
      case LIKE_RIGHT -> likeRight.run();
      case LIKE_LEFT -> likeLeft.run();
    }
  }
}
