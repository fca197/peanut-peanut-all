package com.olivia.sdk.listener;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.io.Serializable;
import java.util.List;

/**
 * 导出监听器 创建时间 2019-12-30 11:28
 *
 * @author
 */
public interface BaseExportDataPoiListener<T> extends Serializable {

  /**
   * 导出附件名称
   */
  String getFileName();

  /**
   * 导出数据
   */
  List<T> getExportData(Wrapper<T> query);
}
