package com.olivia.peanut.base.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 地区边界(DistrictCodeBoundary)表实体类
 *
 * @author admin
 * @since 2025-08-23 17:50:15
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName(value = "t_district_code_boundary")
public class DistrictCodeBoundary extends BaseEntity<DistrictCodeBoundary> {

  /***
   *  区域编码
   */
  @TableField(value = "district_code")
  private String districtCode;
  /***
   *  区域名称
   */
  @TableField(value = "district_name")
  private String districtName;
  /***
   *  边界
   */
  @TableField(value = "polyline")
  private String polyline;
  /***
   *  纬度
   */
  @TableField(value = "center_lat")
  private String centerLat;
  /***
   *  经度
   */
  @TableField(value = "center_lng")
  private String centerLng;

}

