package com.olivia.peanut.store.api.entity.storePoi;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * store poi(StorePoi)根据ID删除多个入参
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
@Accessors(chain = true)
@Getter
@Setter
public class StorePoiDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;

}

