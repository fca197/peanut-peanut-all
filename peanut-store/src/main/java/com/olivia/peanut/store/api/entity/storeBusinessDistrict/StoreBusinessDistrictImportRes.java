package com.olivia.peanut.store.api.entity.storeBusinessDistrict;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈(StoreBusinessDistrict)保存返回
 *
 * @author admin
 * @since 2025-08-24 21:01:55
 */
@Accessors(chain = true)
@Getter
@Setter
public class StoreBusinessDistrictImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

