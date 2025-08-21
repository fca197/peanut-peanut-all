package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 排程版本表(ApsSchedulingDayConfig)表实体类
 *
 * @author peanut
 * @since 2024-07-19 19:19:49
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_day_config")
public class ApsSchedulingDayConfig extends BaseEntity<ApsSchedulingDayConfig> {

  /***
   *  工厂ID
   */
  private Long factoryId;

  private String schedulingType;
  /***
   *  工艺路径ID
   */
  private Long processId;


  /****
   * 制造路径ID
   */
  private Long makeProcessId;

  /***
   *  排程版本号
   */
  private String schedulingDayNo;
  /***
   *  排程版本名称
   */
  private String schedulingDayName;
  /***
   *  是否默认
   */
  private Boolean isDefault;

}

