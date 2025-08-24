package com.olivia.peanut.store.api.entity.storeBusinessDistrictType;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 商圈类型(StoreBusinessDistrictType)查询对象返回
 *
 * @author admin
 * @since 2025-08-24 21:10:18
 */
//@Accessors(chain=true)
@Getter
@Setter
//@SuppressWarnings("serial")
public class StoreBusinessDistrictTypeDto extends BaseEntityDto {

  /***
   *  类型名称
   */
  @NotBlank(message = "类型名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String businessDistrictTypeName;
  /***
   *  类型编码
   */
  @NotBlank(message = "类型编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String businessDistrictTypeCode;
  /***
   *  类型描述
   */
  @NotBlank(message = "类型描述不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String businessDistrictTypeDesc;

}


