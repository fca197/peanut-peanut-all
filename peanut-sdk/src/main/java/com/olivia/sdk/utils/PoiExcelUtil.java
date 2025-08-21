package com.olivia.sdk.utils;

import cn.hutool.core.util.RadixUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.olivia.sdk.exception.RunException;
import com.olivia.sdk.utils.EasyExcelUtilExportMultipleData.SheetData;
import com.olivia.sdk.utils.EasyExcelUtilExportMultipleData.SheetHeader;
import com.olivia.sdk.utils.model.CustomCellWriteHeightConfig;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Excel处理工具类，基于POI和EasyExcel实现Excel的导入、导出及格式转换功能 支持单sheet导出、多sheet导出、Excel读取及单元格坐标转换
 */
@Slf4j
public final class PoiExcelUtil {

  /**
   * Excel列索引对应的字母序列（A-Z）
   */
  private static final String EXCEL_INDEX = String.join(",", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
      "W", "X", "Y", "Z");

  /**
   * 私有构造函数，防止工具类实例化
   */
  private PoiExcelUtil() {
    throw new AssertionError("工具类不允许实例化");
  }

  /**
   * 将Excel列字母（如"A"、"B"）转换为十进制列号
   *
   * @param columnAddress 列字母地址（如"A"表示第0列，"B"表示第1列）
   * @return 对应的十进制列号
   */
  public static Long getCellColumnNumber(String columnAddress) {
    Objects.requireNonNull(columnAddress, () -> "列地址不能为空");
    return RadixUtil.decode(columnAddress, EXCEL_INDEX);
  }

  /**
   * 将十进制列号转换为Excel列字母（如0→"A"、1→"B"）
   *
   * @param columnNumber 十进制列号
   * @return 对应的列字母
   */
  public static String getCellColumnName(Long columnNumber) {
    Objects.requireNonNull(columnNumber, () -> "列号不能为空");
    return RadixUtil.encode(EXCEL_INDEX, columnNumber);
  }

  /**
   * 将十进制列号转换为Excel列字母（重载方法）
   *
   * @param columnNumber 十进制列号
   * @return 对应的列字母
   */
  public static String getCellColumnName(Integer columnNumber) {
    Objects.requireNonNull(columnNumber, () -> "列号不能为空");
    return RadixUtil.encode(EXCEL_INDEX, columnNumber.longValue());
  }

  /**
   * 导出Excel文件（单sheet）
   *
   * @param clazz     数据模型类
   * @param data      导出数据列表
   * @param sheetName 工作表名称
   * @param <T>       数据模型类型
   */
  public static <T> void export(Class<T> clazz, List<T> data, String sheetName) {
    // 参数校验（JDK21增强：使用Objects.requireNonNull）
    Objects.requireNonNull(clazz, () -> "数据模型类不能为空");
    Objects.requireNonNull(data, () -> "导出数据不能为空");
    Objects.requireNonNull(sheetName, () -> "工作表名称不能为空");

    HttpServletResponse response = ReqResUtils.getResponse();

    try (ServletOutputStream outputStream = response.getOutputStream()) {
      // 设置响应头（JDK21优化：使用StandardCharsets统一字符集）
      String encodedFileName = URLEncoder.encode(sheetName + ".XLSX", StandardCharsets.UTF_8);
      response.addHeader("Content-Disposition", "attachment;filename=" + encodedFileName);
      response.setContentType("application/vnd.ms-excel;charset=UTF-8");

      // 写入Excel数据
      EasyExcel.write(outputStream, clazz).registerWriteHandler(CustomCellWriteHeightConfig.createDefaultCellStyleStrategy())
          .registerWriteHandler(new CustomCellWriteHeightConfig()).sheet(sheetName).doWrite(data);

    } catch (Exception e) {
      log.error("Excel导出失败 [sheet名称: {}]", sheetName, e);
      throw new RunException("Excel导出失败: " + e.getMessage(), e);
    }
  }

  /**
   * 导出多sheet的Excel文件
   *
   * @param req 多sheet导出请求参数
   */
  public static void exportMultipleData(EasyExcelUtilExportMultipleData req) {
    Objects.requireNonNull(req, () -> "多sheet导出参数不能为空");
    req.checkParam(); // 校验请求参数

    HttpServletResponse response = ReqResUtils.getResponse();

    // JDK21优化：try-with-resources管理多个资源，自动关闭
    try (SXSSFWorkbook workbook = new SXSSFWorkbook(); ServletOutputStream outputStream = response.getOutputStream()) {

      workbook.setCompressTempFiles(true); // 压缩临时文件，优化内存
      Map<CellStyleEnum, CellStyle> styleMap = createStyles(workbook);
      CellStyle headerCellStyle = styleMap.get(CellStyleEnum.HEADER);
      CellStyle bodyCellStyle = styleMap.get(CellStyleEnum.BODY);
      List<SheetData> sheetDataList = req.getSheetDataList();

      // 遍历处理每个sheet
      for (SheetData sheetData : sheetDataList) {
        SXSSFSheet sheet = workbook.createSheet(sheetData.getSheetName());
        List<SheetHeader> headerList = sheetData.getHeaderList();

        // 创建表头行
        SXSSFRow headerRow = sheet.createRow(0);
        IntStream.range(0, headerList.size()).forEach(colIndex -> {
          SXSSFCell cell = headerRow.createCell(colIndex);
          SheetHeader header = headerList.get(colIndex);
          sheet.setColumnWidth(colIndex, header.getWidth() * 25); // 设置列宽
          cell.setCellStyle(headerCellStyle);
          cell.setCellValue(header.getShowName());
        });

        // 处理表体数据
        List<?> dataList = sheetData.getDataList();
        if (Objects.isNull(dataList) || dataList.isEmpty()) {
          continue;
        }

        // 遍历数据行（JDK21优化：增强for循环可读性）
        for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
          SXSSFRow dataRow = sheet.createRow(rowIndex + 1); // 跳过表头行
          Object rowData = dataList.get(rowIndex);

          // 遍历列
          IntStream.range(0, headerList.size()).forEach(colIndex -> {
            SXSSFCell cell = dataRow.createCell(colIndex);
            cell.setCellStyle(bodyCellStyle);

            // 获取单元格值（支持Map和JavaBean）
            String cellValue = objectToCellValue(rowData, headerList.get(colIndex).getFieldName());
            cell.setCellValue(cellValue);
          });
        }
      }

      // 设置响应信息
      response.reset();
      response.setContentType("application/ms-excel;charset=UTF-8");
      String encodedFileName = URLEncoder.encode(req.getFileName() + ".xls", StandardCharsets.UTF_8);
      response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName);

      // 写入输出流
      workbook.write(outputStream);
      outputStream.flush();

    } catch (Exception e) {
      log.error("多sheet Excel导出失败 [文件名: {}]", req.getFileName(), e);
      throw new RunException("多sheet Excel导出失败: " + e.getMessage(), e);
    }
  }

  /**
   * 将对象转换为单元格字符串值
   *
   * @param rowData   行数据（Map或JavaBean）
   * @param fieldName 字段名
   * @return 单元格字符串值
   */
  private static String objectToCellValue(Object rowData, String fieldName) {
    if (rowData instanceof Map<?, ?> mapData) {
      return ValueUtils.value2Str(mapData.get(fieldName));
    } else {
      return ValueUtils.value2Str(ReflectUtil.getFieldValue(rowData, fieldName));
    }
  }

  /**
   * 读取Excel文件数据
   *
   * @param file     上传的Excel文件
   * @param listener 解析监听器
   * @param clazz    数据模型类
   * @param <T>      数据模型类型
   * @return 解析后的数据集
   */
  public static <T> List<T> readData(MultipartFile file, AnalysisEventListener<T> listener, Class<T> clazz) {
    // 参数校验（JDK21增强：使用Objects.requireNonNull）
    Objects.requireNonNull(file, () -> "上传文件不能为空");
    Objects.requireNonNull(listener, () -> "解析监听器不能为空");
    Objects.requireNonNull(clazz, () -> "数据模型类不能为空");

    try {
      return EasyExcelFactory.read(file.getInputStream()).registerReadListener(listener).head(clazz).excelType(ExcelTypeEnum.XLSX).charset(StandardCharsets.UTF_8)
          .sheet(0).autoTrim(true).doReadSync();
    } catch (RunException e) {
      throw e; // 业务异常直接抛出
    } catch (Exception e) {
      log.error("Excel读取失败 [文件名: {}]", file.getOriginalFilename(), e);
      throw new RunException("Excel读取失败: " + e.getMessage(), e);
    }
  }

  /**
   * 创建Excel单元格样式映射
   *
   * @param workbook 工作簿对象
   * @return 样式枚举与样式对象的映射
   */
  public static Map<CellStyleEnum, CellStyle> createStyles(Workbook workbook) {
    return createStyles(workbook, true);
  }

  /**
   * 创建Excel单元格样式映射（带边框配置）
   *
   * @param workbook     工作簿对象
   * @param borderIsThin 是否使用细边框
   * @return 样式枚举与样式对象的映射
   */
  public static Map<CellStyleEnum, CellStyle> createStyles(Workbook workbook, boolean borderIsThin) {
    Objects.requireNonNull(workbook, () -> "工作簿对象不能为空");

    Map<CellStyleEnum, CellStyle> styles = new HashMap<>(2);

    // 创建表体样式
    CellStyle bodyStyle = createBaseCellStyle(workbook, borderIsThin, false);
    styles.put(CellStyleEnum.BODY, bodyStyle);

    // 创建表头样式（继承表体样式，增加背景色和粗体）
    CellStyle headerStyle = createBaseCellStyle(workbook, borderIsThin, true);
    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    styles.put(CellStyleEnum.HEADER, headerStyle);

    return styles;
  }

  /**
   * 创建基础单元格样式
   *
   * @param workbook     工作簿对象
   * @param borderIsThin 是否使用细边框
   * @param isBold       是否粗体
   * @return 基础单元格样式
   */
  private static CellStyle createBaseCellStyle(Workbook workbook, boolean borderIsThin, boolean isBold) {
    CellStyle style = workbook.createCellStyle();
    style.setAlignment(HorizontalAlignment.LEFT);
    style.setVerticalAlignment(VerticalAlignment.CENTER);

    // 设置边框
    if (borderIsThin) {
      BorderStyle borderStyle = BorderStyle.THIN;
      short borderColor = IndexedColors.GREY_50_PERCENT.getIndex();

      style.setBorderRight(borderStyle);
      style.setRightBorderColor(borderColor);
      style.setBorderLeft(borderStyle);
      style.setLeftBorderColor(borderColor);
      style.setBorderTop(borderStyle);
      style.setTopBorderColor(borderColor);
      style.setBorderBottom(borderStyle);
      style.setBottomBorderColor(borderColor);
    }

    // 设置字体
    Font font = workbook.createFont();
    font.setFontName("Arial");
    font.setFontHeightInPoints((short) 10);
    font.setBold(isBold);
    style.setFont(font);

    return style;
  }

  /**
   * 单元格样式枚举
   */
  public enum CellStyleEnum {
    HEADER,  // 表头样式
    BODY     // 表体样式
  }
}
