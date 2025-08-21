package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.peanut.aps.api.entity.apsOrder.ApsOrderStatusEnum;
import com.olivia.sdk.ann.FieldExt;
import com.olivia.sdk.utils.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrder)表实体类
 *
 * @author peanut
 * @since 2024-04-09 13:19:35
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_order")
public class ApsOrder extends BaseEntity<ApsOrder> {

  @FieldExt(fieldName = "订单号")
  private String orderNo;
  @FieldExt(fieldName = "父级订单号")
  private String orderNoParent;
  @FieldExt(fieldName = "备注")
  private String orderRemark;
  /***
   *@see   ApsOrderStatusEnum
   */

  private Long orderStatus;


  @FieldExt(fieldName = "订单总价")
  private BigDecimal orderTotalPrice;

  @FieldExt(fieldName = "定金金额")
  private BigDecimal reserveAmount;
  @FieldExt(fieldName = "定金支付时间")
  private LocalDateTime reserveDatetime;

  @FieldExt(fieldName = "尾款金额")
  private BigDecimal finishPayedAmount;
  @FieldExt(fieldName = "尾款支付时间")
  private LocalDateTime finishPayedDatetime;

  @FieldExt(fieldName = "预计制造完成时间")
  private LocalDate expectedMakeFinishDate;

  @FieldExt(fieldName = "实际制造时间")
  private LocalDate actMakeFinishDate;

  @FieldExt(fieldName = "预计交付日期")
  private LocalDate expectedDeliveryDate;

  @FieldExt(fieldName = "实际交付日期")
  private LocalDate actDeliveryDate;

//  @FieldExt(fieldName = "交付日期")
//  private LocalDate deliveryDate;

//  @FieldExt(fieldName = "交付日期")
//  private LocalDate deliveryDate;


  private Long factoryId;
  private Long goodsId;

  @FieldExt(fieldName = "排产日期")
  private LocalDate schedulingDate;
  /**
   * 越大越紧急
   */

  @FieldExt(fieldName = "紧急度")
  private Integer urgencyLevel;

  @FieldExt(fieldName = "告警")
  private Boolean isWarning;

  @FieldExt(fieldName = "告警原因")
  private String warningReason;

  public void setOrderStatus(Long apsOrderStatus) {
    if (Objects.nonNull(apsOrderStatus)) {
      this.orderStatus = apsOrderStatus;
    }
  }

  public ApsOrder setOrderStatus(ApsOrderStatusEnum apsOrderStatusEnum) {
    if (Objects.nonNull(apsOrderStatusEnum)) {
      this.orderStatus = apsOrderStatusEnum.getCode();
    }
    return this;
  }

}

