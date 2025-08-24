package com.olivia.peanut.store.api.entity.storeBusinessDistrictLevel;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 商圈级别(StoreBusinessDistrictLevel)查询对象返回
 *
 * @author admin
 * @since 2025-08-24 21:10:17
 */
//@Accessors(chain=true)
@Getter
@Setter
//@SuppressWarnings("serial")
public class StoreBusinessDistrictLevelDto extends BaseEntityDto {

  /***
   *  商圈名称
   */
  @NotBlank(message = "商圈名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String businessDistrictLevelName;
  /***
   *  商圈描述
   */
  @NotBlank(message = "商圈描述不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String businessDistrictLevelDesc;
  /***
   *  商圈颜色
   */
  @NotBlank(message = "商圈颜色不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String businessDistrictLevelColor;

}


