package com.olivia.peanut.base.api.entity.districtCodeBoundary;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 地区边界(DistrictCodeBoundary)查询对象返回
 *
 * @author admin
 * @since 2025-08-23 17:50:17
 */
//@Accessors(chain=true)
@Getter
@Setter
//@SuppressWarnings("serial")
public class DistrictCodeBoundaryDto extends BaseEntityDto {

  /***
   *  区域编码
   */
  @NotBlank(message = "区域编码不能为空", groups = {UpdateCheck.class})
  private String districtCode;
  /***
   *  区域名称
   */
  @NotBlank(message = "区域名称不能为空", groups = {UpdateCheck.class})
  private String districtName;
  /***
   *  边界
   */
  @NotBlank(message = "边界不能为空", groups = {UpdateCheck.class})
  private String polyline;
  /***
   *  纬度
   */
  @NotBlank(message = "纬度不能为空", groups = {UpdateCheck.class})
  private String centerLat;
  /***
   *  经度
   */
  @NotBlank(message = "经度不能为空", groups = {UpdateCheck.class})
  private String centerLng;

}


