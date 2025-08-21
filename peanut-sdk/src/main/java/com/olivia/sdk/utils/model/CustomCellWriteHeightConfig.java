package com.olivia.sdk.utils.model;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;

/**
 * EasyExcel 自定义单元格样式配置类 支持自适应列宽和自定义表头、内容单元格样式
 */
public class CustomCellWriteHeightConfig extends AbstractColumnWidthStyleStrategy {

  /**
   * 最大列宽限制（Excel最大支持255字符）
   */
  private static final int MAX_COLUMN_WIDTH = 255;
  /**
   * 默认字体名称
   */
  private static final String DEFAULT_FONT_NAME = "Arial";
  /**
   * 默认表头字体大小
   */
  private static final short HEADER_FONT_SIZE = 10;
  /**
   * 默认内容字体大小
   */
  private static final short CONTENT_FONT_SIZE = 10;
  /**
   * 列宽缓存，key: sheet编号, value: 列索引->最大宽度映射
   */
  private final Map<Integer, Map<Integer, Integer>> columnWidthCache = new HashMap<>();

  /**
   * 创建默认的单元格样式策略
   *
   * @return 水平单元格样式策略
   */
  public static HorizontalCellStyleStrategy createDefaultCellStyleStrategy() {
    // 表头样式
    WriteCellStyle headerStyle = createHeaderStyle();

    // 内容样式
    WriteCellStyle contentStyle = createContentStyle();

    return new HorizontalCellStyleStrategy(headerStyle, contentStyle);
  }

  /**
   * 创建表头样式
   *
   * @return 表头单元格样式
   */
  public static WriteCellStyle createHeaderStyle() {
    WriteCellStyle headerStyle = new WriteCellStyle();

    // 设置背景色为浅蓝绿色
    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
    headerStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

    // 设置边框
    setBorderStyle(headerStyle, IndexedColors.GREY_50_PERCENT.getIndex());

    // 设置字体
    WriteFont headerFont = new WriteFont();
    headerFont.setFontName(DEFAULT_FONT_NAME);
    headerFont.setFontHeightInPoints(HEADER_FONT_SIZE);
    headerFont.setBold(true);
    headerFont.setColor(IndexedColors.BLACK.getIndex());
    headerStyle.setWriteFont(headerFont);

    // 设置对齐方式
    headerStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
    headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    headerStyle.setWrapped(false);

    return headerStyle;
  }

  /**
   * 创建内容样式
   *
   * @return 内容单元格样式
   */
  public static WriteCellStyle createContentStyle() {
    WriteCellStyle contentStyle = new WriteCellStyle();

    // 设置边框
    setBorderStyle(contentStyle, IndexedColors.GREY_50_PERCENT.getIndex());

    // 设置字体
    WriteFont contentFont = new WriteFont();
    contentFont.setFontName(DEFAULT_FONT_NAME);
    contentFont.setFontHeightInPoints(CONTENT_FONT_SIZE);
    contentFont.setBold(false);
    contentFont.setColor(IndexedColors.BLACK.getIndex());
    contentStyle.setWriteFont(contentFont);

    // 设置对齐方式
    contentStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
    contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    contentStyle.setWrapped(false);

    return contentStyle;
  }

  /**
   * 统一设置边框样式
   *
   * @param cellStyle 单元格样式
   * @param color     边框颜色
   */
  private static void setBorderStyle(WriteCellStyle cellStyle, short color) {
    cellStyle.setBorderTop(BorderStyle.THIN);
    cellStyle.setTopBorderColor(color);
    cellStyle.setBorderBottom(BorderStyle.THIN);
    cellStyle.setBottomBorderColor(color);
    cellStyle.setBorderLeft(BorderStyle.THIN);
    cellStyle.setLeftBorderColor(color);
    cellStyle.setBorderRight(BorderStyle.THIN);
    cellStyle.setRightBorderColor(color);
  }

  @Override
  protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
    // 只有表头或有单元格数据时才需要设置列宽
    if (!isHead && CollUtil.isEmpty(cellDataList)) {
      return;
    }

    // 获取当前sheet的列宽缓存
    Map<Integer, Integer> maxColumnWidthMap = columnWidthCache.computeIfAbsent(writeSheetHolder.getSheetNo(), k -> new HashMap<>());

    // 计算当前单元格内容所需宽度
    int cellWidth = calculateCellWidth(cellDataList, cell, isHead);
    if (cellWidth <= 0) {
      return;
    }

    // 限制最大宽度
    int adjustedWidth = Math.min(cellWidth, MAX_COLUMN_WIDTH);

    // 更新列宽缓存，只保留最大值
    int columnIndex = cell.getColumnIndex();
    Integer currentMaxWidth = maxColumnWidthMap.get(columnIndex);
    if (currentMaxWidth == null || adjustedWidth > currentMaxWidth) {
      maxColumnWidthMap.put(columnIndex, adjustedWidth);
      // 设置列宽（Excel列宽单位为1/256个字符宽度）
      writeSheetHolder.getSheet().setColumnWidth(columnIndex, adjustedWidth * 256);
    }
  }

  /**
   * 计算单元格内容所需宽度
   *
   * @param cellDataList 单元格数据列表
   * @param cell         单元格对象
   * @param isHead       是否为表头
   * @return 单元格所需宽度（字符数）
   */
  private Integer calculateCellWidth(List<WriteCellData<?>> cellDataList, Cell cell, Boolean isHead) {
    if (isHead) {
      // 表头单元格宽度计算
      return cell.getStringCellValue().getBytes().length + 2; // 加2个字符作为缓冲
    }

    // 内容单元格宽度计算
    if (CollUtil.isEmpty(cellDataList)) {
      return 0;
    }

    WriteCellData<?> cellData = cellDataList.getFirst();
    CellDataTypeEnum dataType = cellData.getType();
    if (dataType == null) {
      return 0;
    }

    switch (dataType) {
      case STRING:
        String value = cellData.getStringValue();
        if (value == null) {
          return 0;
        }
        // 处理换行符，取第一行长度
        int newlineIndex = value.indexOf("\n");
        int baseLength = (newlineIndex != -1) ? value.substring(0, newlineIndex).getBytes().length : value.getBytes().length;
        return baseLength + 2; // 加2个字符作为缓冲
      case BOOLEAN:
        return cellData.getBooleanValue().toString().getBytes().length + 1;
      case NUMBER:
        return cellData.getNumberValue().toString().getBytes().length + 1;
      default:
        return 0;
    }
  }
}
