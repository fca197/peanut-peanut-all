package com.olivia.sdk.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import com.google.common.collect.Lists;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.dto.ExcelErrorMsg;
import com.olivia.sdk.exception.RunException;
import com.olivia.sdk.utils.CheckObjectFieldValueUtils;
import java.util.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Excel导入抽象监听器 基于EasyExcel的AnalysisEventListener，提供通用的Excel导入数据校验和错误处理功能
 *
 * @param <T> 导入数据对应的DTO类型，需继承BaseEntityDto
 */
@Slf4j
public abstract class AbstractImportListener<T extends BaseEntityDto> extends AnalysisEventListener<T> {

  /**
   * 存储导入过程中产生的错误信息
   */
  private final List<ExcelErrorMsg> errorMsgList = Lists.newArrayList();

  /**
   * 检查导入数据的合法性
   *
   * @param data            导入的数据对象
   * @param analysisContext EasyExcel分析上下文
   */
  public void checkData(T data, AnalysisContext analysisContext) {
    ReadSheetHolder readHolder = analysisContext.readSheetHolder();
    Integer rowIndex = readHolder.getRowIndex();

    // 设置行索引，便于错误定位
    data.setRowIndex(rowIndex);

    // 校验数据并收集错误信息
    List<ExcelErrorMsg> fieldErrors = CheckObjectFieldValueUtils.check(data);
    if (!fieldErrors.isEmpty()) {
      fieldErrors.forEach(error -> {
        error.setRowIndex(rowIndex);
        error.setSheetName(readHolder.getSheetName());
        errorMsgList.add(error);
      });
    }
  }

  /**
   * 所有数据解析完成后执行的操作 对错误信息进行排序，并在存在错误时抛出异常
   *
   * @param analysisContext EasyExcel分析上下文
   */
  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    // 按行索引和错误信息排序，便于前端展示
    errorMsgList.sort(Comparator.comparing(ExcelErrorMsg::getRowIndex).thenComparing(ExcelErrorMsg::getColumnIndex).thenComparing(ExcelErrorMsg::getErrMsg));

    // 如果存在错误信息，抛出异常
    if (!errorMsgList.isEmpty()) {
      throw new RunException(200, "文件上传失败，存在数据校验错误", errorMsgList);
    }

    // 执行子类的后置处理逻辑
    afterAllAnalysed(analysisContext);
  }

  /**
   * 添加Excel错误信息
   *
   * @param excelErrorMsg 错误信息对象
   */
  protected void addExcelErrorMsg(ExcelErrorMsg excelErrorMsg) {
    if (Objects.nonNull(excelErrorMsg)) {
      this.errorMsgList.add(excelErrorMsg);
    }
  }

  /**
   * 异常处理 捕获并处理解析过程中发生的异常，转换为自定义错误信息
   *
   * @param exception 发生的异常
   * @param context   EasyExcel分析上下文
   * @throws Exception 处理后的异常
   */
  @Override
  public void onException(Exception exception, AnalysisContext context) throws Exception {
    ReadSheetHolder readHolder = context.readSheetHolder();
    Integer rowIndex = readHolder.getRowIndex();

    // 创建异常对应的错误信息
    ExcelErrorMsg errorMsg = new ExcelErrorMsg(readHolder.getSheetName(), rowIndex, null, "系统列", "解析错误: " + exception.getMessage());
    errorMsgList.add(errorMsg);

    // 记录异常日志
    if (log.isErrorEnabled()) {
      log.error("Excel解析异常 - 工作表: {}, 行: {}", readHolder.getSheetName(), rowIndex, exception);
    }
  }

  /**
   * 处理表头信息 子类可重写此方法实现自定义表头校验逻辑
   *
   * @param headMap 表头映射（列索引 -> 表头名称）
   * @param context EasyExcel分析上下文
   */
  @Override
  public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    // 执行表头校验逻辑（子类可重写）
    validateHeadMap(headMap, context);
    super.invokeHeadMap(headMap, context);
  }

  /**
   * 获取错误信息列表
   *
   * @return 错误信息列表，不会为null
   */
  public List<ExcelErrorMsg> getErrorMsgList() {
    return Lists.newArrayList(errorMsgList); // 返回不可修改的副本，防止外部修改
  }

  /**
   * 所有数据解析完成后的钩子方法 子类可重写此方法实现自定义后置处理逻辑
   *
   * @param analysisContext EasyExcel分析上下文
   */
  protected void afterAllAnalysed(AnalysisContext analysisContext) {
    // 子类实现具体逻辑
  }

  /**
   * 表头校验方法 子类可重写此方法实现自定义表头校验
   *
   * @param headMap 表头映射（列索引 -> 表头名称）
   * @param context EasyExcel分析上下文
   */
  protected void validateHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    // 子类实现具体校验逻辑
  }
}
