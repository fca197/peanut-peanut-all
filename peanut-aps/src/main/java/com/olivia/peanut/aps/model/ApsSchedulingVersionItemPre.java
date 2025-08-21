package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.mybatis.type.impl.MapTypeHandler;
import com.olivia.sdk.mybatis.type.model.MapSub;
import com.olivia.sdk.utils.BaseEntity;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 排产下发订单预览(ApsSchedulingVersionItemPre)表实体类
 *
 * @author makejava
 * @since 2025-04-06 14:16:40
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName(value = "aps_scheduling_version_item_pre", autoResultMap = true)
public class ApsSchedulingVersionItemPre extends BaseEntity<ApsSchedulingVersionItemPre> {

  /***
   *  排产版本ID
   */
  private Long schedulingVersionId;
  /***
   *  当前日期
   */
  private LocalDate currentDay;
  /***
   *  订单ID
   */
  private Long orderId;
  /***
   *  商品ID
   */
  private Long goodsId;
  /***
   *  生产序号
   */
  private Integer numberIndex;
  /***
   *  工厂id
   */
  private Long factoryId;
  /***
   *  显示字段
   */
  @TableField(typeHandler = MapTypeHandler.class)
  private MapSub showField;
  /***
   *  订单号
   */
  private String orderNo;

  /***
   * 是否遗留
   */
  private Boolean legacyOrder;

  private LocalDate oldScheduleDate;

  private String goodsName;
  private String factoryName;



  private Integer urgencyLevel;


}

