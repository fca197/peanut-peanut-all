package com.olivia.peanut.store.api.entity.storeBusinessDistrict;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈(StoreBusinessDistrict)根据ID删除多个入参
 *
 * @author admin
 * @since 2025-08-24 21:01:54
 */
@Accessors(chain = true)
@Getter
@Setter
public class StoreBusinessDistrictDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;

}

