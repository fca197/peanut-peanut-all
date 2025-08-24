package com.olivia.peanut.store.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈类型(StoreBusinessDistrictType)表实体类
 *
 * @author admin
 * @since 2025-08-24 21:10:18
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName(value = "store_business_district_type")
public class StoreBusinessDistrictType extends BaseEntity<StoreBusinessDistrictType> {

  /***
   *  类型名称
   */
  @TableField(value = "business_district_type_name")
  private String businessDistrictTypeName;
  /***
   *  类型编码
   */
  @TableField(value = "business_district_type_code")
  private String businessDistrictTypeCode;
  /***
   *  类型描述
   */
  @TableField(value = "business_district_type_desc")
  private String businessDistrictTypeDesc;

}

