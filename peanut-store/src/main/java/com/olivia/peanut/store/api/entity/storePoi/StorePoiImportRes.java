package com.olivia.peanut.store.api.entity.storePoi;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * store poi(StorePoi)保存返回
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
@Accessors(chain = true)
@Getter
@Setter
public class StorePoiImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

