package com.olivia.peanut.store.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * store poi(StorePoi)表实体类
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName(value = "store_poi")
public class StorePoi extends BaseEntity<StorePoi> {

  public static final String MAX_PARENT_CODE = "000000";

  /***
   *  上级编码
   */
  @TableField(value = "poi_parent_code")
  private String poiParentCode;
  /***
   *  poi编码
   */
  @TableField(value = "poi_code")
  private String poiCode;
  /***
   *  poi名称
   */
  @TableField(value = "poi_name")
  private String poiName;
  /***
   *  层级
   */
  @TableField(value = "poi_level")
  private Integer poiLevel;
  /***
   *  poi路径
   */
  @TableField(value = "poi_path")
  private String poiPath;

}

