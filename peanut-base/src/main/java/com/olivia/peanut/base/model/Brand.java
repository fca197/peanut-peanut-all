package com.olivia.peanut.base.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 品牌表(Brand)表实体类
 *
 * @author admin
 * @since 2025-08-25 15:03:19
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName(value = "t_brand")
public class Brand extends BaseEntity<Brand> {

  /***
   *  工厂ID
   */
  @TableField(value = "factory_id")
  private Long factoryId;
  @TableField(value = "brand_code")
  private String brandCode;
  @TableField(value = "brand_name")
  private String brandName;
  @TableField(value = "brand_status")
  private String brandStatus;
  @TableField(value = "is_used")
  private Integer isUsed;

}

