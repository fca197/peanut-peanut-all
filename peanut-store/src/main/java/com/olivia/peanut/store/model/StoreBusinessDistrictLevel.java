package com.olivia.peanut.store.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈级别(StoreBusinessDistrictLevel)表实体类
 *
 * @author admin
 * @since 2025-08-24 21:10:16
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName(value = "store_business_district_level")
public class StoreBusinessDistrictLevel extends BaseEntity<StoreBusinessDistrictLevel> {

  /***
   *  商圈名称
   */
  @TableField(value = "business_district_level_name")
  private String businessDistrictLevelName;
  /***
   *  商圈描述
   */
  @TableField(value = "business_district_level_desc")
  private String businessDistrictLevelDesc;
  /***
   *  商圈颜色
   */
  @TableField(value = "business_district_level_color")
  private String businessDistrictLevelColor;

}

