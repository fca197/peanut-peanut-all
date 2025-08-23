package com.olivia.peanut.base.api.entity.districtCode;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * (DistrictCode)查询对象返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:07
 */
//@Accessors(chain=true)
@Getter
@Setter
//@SuppressWarnings("serial")
public class DistrictCodeDto extends BaseEntityDto {

  @NotBlank(message = "${column.comment}不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String code;
  /***
   *  城市编码
   */
  @NotBlank(message = "城市编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String cityCode;
  @NotBlank(message = "${column.comment}不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String name;
  @NotBlank(message = "${column.comment}不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String parentCode;
  /***
   *  路径
   */
  @NotBlank(message = "路径不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String path;
  @NotNull(message = "${column.comment}不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer level;
  /***
   *  经度
   */
  @NotNull(message = "经度不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal centerLng;
  /***
   *  纬度
   */
  @NotNull(message = "纬度不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal centerLat;
  /***
   *  纬度
   */
  @NotBlank(message = "纬度不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String pathName;

  private List<DistrictCodeDto> children;
}


