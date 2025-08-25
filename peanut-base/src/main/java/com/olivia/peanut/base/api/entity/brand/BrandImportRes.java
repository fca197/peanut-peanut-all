package com.olivia.peanut.base.api.entity.brand;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 品牌表(Brand)保存返回
 *
 * @author admin
 * @since 2025-08-25 15:03:18
 */
@Accessors(chain = true)
@Getter
@Setter
public class BrandImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

