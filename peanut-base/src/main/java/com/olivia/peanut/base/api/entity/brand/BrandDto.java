package com.olivia.peanut.base.api.entity.brand;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 品牌表(Brand)查询对象返回
 *
 * @author admin
 * @since 2025-08-25 15:03:19
 */
//@Accessors(chain=true)
@Getter
@Setter
//@SuppressWarnings("serial")
public class BrandDto extends BaseEntityDto {

  /***
   *  工厂ID
   */
  private Long factoryId;
  @NotBlank(message = "编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String brandCode;
  @NotBlank(message = "名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String brandName;

}


