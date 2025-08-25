package com.olivia.peanut.base.api.entity.brand;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 品牌表(Brand)根据ID删除多个入参
 *
 * @author admin
 * @since 2025-08-25 15:03:18
 */
@Accessors(chain = true)
@Getter
@Setter
public class BrandDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;

}

