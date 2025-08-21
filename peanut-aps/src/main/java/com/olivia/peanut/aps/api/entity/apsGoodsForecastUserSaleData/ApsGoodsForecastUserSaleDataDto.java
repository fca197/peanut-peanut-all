package com.olivia.peanut.aps.api.entity.apsGoodsForecastUserSaleData;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
//import com.alibaba.fastjson2.annotation.JSONField;

/**
 * 预测数据(ApsGoodsForecastUserSaleData)查询对象返回
 *
 * @author admin
 * @since 2025-06-22 18:31:33
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastUserSaleDataDto extends BaseEntityDto {

  /***
   *  预测ID
   */
  //  @JSONField(label = "forecastId")
  @NotNull(message = "预测ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long forecastId;
  /***
   *  销售组Id
   */
  //  @JSONField(label = "saleConfigParentId")
  @NotNull(message = "销售组Id不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long saleConfigParentId;
  /***
   *  销售配置
   */
  @NotBlank(message = "销售配置不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //  @JSONField(label = "saleConfigId")

  private Long saleConfigId;
  @NotBlank(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //  @JSONField(label = "year")

  private Integer year;
  //  @JSONField(label = "month01")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal month01;
  //  @JSONField(label = "month02")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal month02;
  //  @JSONField(label = "month03")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal month03;
  //  @JSONField(label = "month04")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal month04;
  //  @JSONField(label = "month05")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal month05;
  //  @JSONField(label = "month06")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal month06;
  //  @JSONField(label = "month07")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal month07;
  //  @JSONField(label = "month08")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal month08;
  //  @JSONField(label = "month09")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal month09;
  //  @JSONField(label = "month10")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal month10;
  //  @JSONField(label = "month11")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal month11;
  //  @JSONField(label = "month12")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal month12;
  //  @JSONField(label = "monthResult01")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthResult01;
  //  @JSONField(label = "monthResult02")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthResult02;
  //  @JSONField(label = "monthResult03")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthResult03;
  //  @JSONField(label = "monthResult04")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthResult04;
  //  @JSONField(label = "monthResult05")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthResult05;
  //  @JSONField(label = "monthResult06")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthResult06;
  //  @JSONField(label = "monthResult07")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthResult07;
  //  @JSONField(label = "monthResult08")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthResult08;
  //  @JSONField(label = "monthResult09")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthResult09;
  //  @JSONField(label = "monthResult10")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthResult10;
  //  @JSONField(label = "monthResult11")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthResult11;
  //  @JSONField(label = "monthResult12")
  @NotNull(message = "不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthResult12;

}


