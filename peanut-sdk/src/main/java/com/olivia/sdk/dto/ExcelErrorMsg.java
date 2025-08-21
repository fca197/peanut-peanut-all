package com.olivia.sdk.dto;

import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Excel错误信息数据传输对象 用于记录Excel导入/导出过程中发生的错误信息，包括错误位置和具体原因
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ExcelErrorMsg {

  /**
   * 工作表名称
   */
  private String sheetName;
  /**
   * 列索引（从0开始）
   */
  private Integer columnIndex;
  /**
   * 列名称
   */
  private String columnName;
  /**
   * 行索引（从0开始）
   */
  private Integer rowIndex;
  /**
   * 错误信息描述
   */
  private String errMsg;

  /**
   * 构造方法，快速创建错误信息对象
   *
   * @param sheetName   工作表名称
   * @param rowIndex    行索引
   * @param columnIndex 列索引
   * @param columnName  列名称
   * @param errMsg      错误信息
   */
  public ExcelErrorMsg(String sheetName, Integer rowIndex, Integer columnIndex, String columnName, String errMsg) {
    this.sheetName = sheetName;
    this.rowIndex = rowIndex;
    this.columnIndex = columnIndex;
    this.columnName = columnName;
    this.errMsg = errMsg;
  }

  /**
   * 获取Excel列字母标识（如A、B、C...）
   *
   * @return 列字母标识，列索引为null时返回null
   */
  public String getColumnStr() {
    return Objects.isNull(columnIndex) ? null : PoiExcelUtil.getCellColumnName(columnIndex);
  }

  /**
   * 获取用户友好的行号（从1开始）
   *
   * @return 行号，行索引为null时返回null
   */
  public Integer getRowNum() {
    return Objects.isNull(rowIndex) ? null : rowIndex + 1;
  }

  /**
   * 重写toString方法，便于日志输出
   *
   * @return 包含关键错误信息的字符串
   */
  @Override
  public String toString() {
    return String.format("工作表[%s] 行[%s] 列[%s(%s)]: %s", sheetName, getRowNum(), getColumnStr(), columnName, errMsg);
  }
}
