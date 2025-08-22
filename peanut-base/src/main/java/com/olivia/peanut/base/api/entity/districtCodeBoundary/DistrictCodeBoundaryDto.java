package com.olivia.peanut.base.api.entity.districtCodeBoundary;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
//import com.alibaba.fastjson2.annotation.JSONField;

/**
 * 地区边界(DistrictCodeBoundary)查询对象返回
 *
 * @author admin
 * @since 2025-08-22 13:33:38
 */
//@Accessors(chain=true)
@Getter
@Setter
//@SuppressWarnings("serial")
public class DistrictCodeBoundaryDto extends BaseEntityDto {

  /***
   *  区域编码
   */
  @NotBlank(message = "区域编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //  @JSONField(label = "districtCode")
  private String districtCode;
  /***
   *  经度（Longitude）-180～180
   */
  @NotBlank(message = "经度（Longitude）-180～180不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //  @JSONField(label = "lngList")
  private List<String> lngList;
  /***
   *  纬度（Latitude）0～90
   */
  @NotBlank(message = "纬度（Latitude）0～90不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //  @JSONField(label = "latList")
  private List<String> latList;
  /***
   *  中心纬度
   */
  //  @JSONField(label = "centerLat")
  @NotNull(message = "中心纬度不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal centerLat;
  /***
   *  中心经度
   */
  //  @JSONField(label = "centerLon")
  @NotNull(message = "中心经度不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal centerLon;

}


