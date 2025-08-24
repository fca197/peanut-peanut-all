package com.olivia.peanut.store.api.entity.storeBusinessDistrictType;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈类型(StoreBusinessDistrictType)保存返回
 *
 * @author admin
 * @since 2025-08-24 21:10:17
 */
@Accessors(chain = true)
@Getter
@Setter
public class StoreBusinessDistrictTypeImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

