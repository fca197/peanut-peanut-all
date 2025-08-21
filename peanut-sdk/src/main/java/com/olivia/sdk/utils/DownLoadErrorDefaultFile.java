package com.olivia.sdk.utils;

import com.olivia.sdk.ann.MethodExt;
import com.olivia.sdk.ann.MethodExt.FileSuffix;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * 错误文件下载工具类，用于在下载失败时生成默认的错误提示文件 支持TXT和XLSX两种格式，根据注解配置自动选择
 */
@Slf4j
public final class DownLoadErrorDefaultFile {

  /**
   * 字符编码常量（UTF-8）
   */
  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  /**
   * 私有构造函数，防止工具类实例化
   */
  private DownLoadErrorDefaultFile() {
    throw new AssertionError("工具类不允许实例化");
  }

  /**
   * 下载错误文件（使用默认配置）
   */
  public static void downLoadErrorFile() {
    downLoadErrorFile(null);
  }

  /**
   * 根据方法注解配置下载错误文件
   *
   * @param methodExt 方法扩展注解，指定文件格式
   */
  public static void downLoadErrorFile(MethodExt methodExt) {
    HttpServletResponse response = ReqResUtils.getResponse();
    String defaultMessage = "下载失败，请稍后再试";

    if (Objects.nonNull(methodExt) && FileSuffix.XLSX.equals(methodExt.downLoadFileSuffix())) {
      printXlsx(response, defaultMessage, "error.xlsx");
    } else {
      printTxt(response, defaultMessage, "error.txt");
    }
  }

  /**
   * 生成TXT格式错误文件（使用默认响应对象）
   *
   * @param message  错误信息
   * @param fileName 文件名
   */
  public static void printTxt(String message, String fileName) {
    printTxt(ReqResUtils.getResponse(), message, fileName);
  }

  /**
   * 生成TXT格式错误文件
   *
   * @param response 响应对象
   * @param message  错误信息
   * @param fileName 文件名
   */
  public static void printTxt(HttpServletResponse response, String message, String fileName) {
    Objects.requireNonNull(response, "HTTP响应对象不能为空");
    Objects.requireNonNull(message, "错误信息不能为空");
    Objects.requireNonNull(fileName, "文件名不能为空");

    response.reset();
    response.setHeader("Content-Type", "application/octet-stream");
    response.setCharacterEncoding(UTF_8);

    try (OutputStream os = response.getOutputStream()) {
      response.setHeader("Pragma", "public");
      response.setHeader("Cache-Control", "max-age=30");
      response.setHeader("Access-Control-Expose-Headers", "filename,Content-Disposition");

      String encodedFileName = URLEncoder.encode(fileName, UTF_8);
      response.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);
      response.setHeader("filename", encodedFileName);

      os.write(message.getBytes(StandardCharsets.UTF_8));
      os.flush();
    } catch (Exception e) {
      log.error("TXT文件下载失败 [文件名: {}]", fileName, e);
    }
  }

  /**
   * 生成XLSX格式错误文件（使用默认响应对象）
   *
   * @param message  错误信息
   * @param fileName 文件名
   */
  public static void printXlsx(String message, String fileName) {
    printXlsx(ReqResUtils.getResponse(), message, fileName);
  }

  /**
   * 生成XLSX格式错误文件
   *
   * @param response 响应对象
   * @param message  错误信息
   * @param fileName 文件名
   */
  public static void printXlsx(HttpServletResponse response, String message, String fileName) {
    Objects.requireNonNull(response, "HTTP响应对象不能为空");
    Objects.requireNonNull(message, "错误信息不能为空");
    Objects.requireNonNull(fileName, "文件名不能为空");

    response.reset();
    // SXSSFWorkbook实现了AutoCloseable，会自动清理临时文件
    try (SXSSFWorkbook workbook = new SXSSFWorkbook();
        ServletOutputStream outputStream = response.getOutputStream()) {

      SXSSFSheet sheet = workbook.createSheet("下载失败");
      sheet.createRow(0).createCell(0).setCellValue(message);

      response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
      String encodedFileName = URLEncoder.encode(fileName, UTF_8);
      response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName);

      workbook.write(outputStream);
      outputStream.flush();
    } catch (Exception e) {
      log.error("XLSX文件下载失败 [文件名: {}]", fileName, e);
    }
  }
}
