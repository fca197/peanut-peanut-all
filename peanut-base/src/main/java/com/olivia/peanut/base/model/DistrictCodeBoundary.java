package com.olivia.peanut.base.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.mybatis.type.impl.ListStringTypeHandler;
import com.olivia.sdk.utils.BaseEntity;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 地区边界(DistrictCodeBoundary)表实体类
 *
 * @author admin
 * @since 2025-08-22 13:33:38
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName(value = "t_district_code_boundary",autoResultMap = true)
public class DistrictCodeBoundary extends BaseEntity<DistrictCodeBoundary> {

  /***
   *  区域编码
   */
  @TableField(value = "district_code")
  private String districtCode;
  /***
   *  经度（Longitude）-180～180
   */
  @TableField(value = "lng_list", typeHandler = ListStringTypeHandler.class)
  private List<String> lngList;
  /***
   *  纬度（Latitude）0～90
   */
  @TableField(value = "lat_list", typeHandler = ListStringTypeHandler.class)
  private List<String> latList;
  /***
   *  中心纬度
   */
  @TableField(value = "center_lat")
  private BigDecimal centerLat;
  /***
   *  中心经度
   */
  @TableField(value = "center_lon")
  private BigDecimal centerLon;

}

