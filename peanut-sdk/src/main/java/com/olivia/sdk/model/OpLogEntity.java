package com.olivia.sdk.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 操作日志实体类 记录系统中各类业务操作的详细日志信息，用于审计和追踪
 */
@Setter
@Getter
@Accessors(chain = true)
@TableName("base_oplog")
public class OpLogEntity extends BaseEntity<OpLogEntity> {

  /**
   * 操作耗时(毫秒)
   */
  private Long useTime;

  /**
   * 业务主键
   */
  private String businessKey;

  /**
   * 操作内容描述
   */
  private String content;

  /**
   * 调用方法名
   */
  private String methodName;

  /**
   * 业务类型
   */
  private String businessType;

  /**
   * 业务类型扩展字段1
   */
  @TableField("business_type0")
  private String businessTypeExt1;

  /**
   * 业务类型扩展字段2
   */
  @TableField("business_type1")
  private String businessTypeExt2;

  /**
   * 业务类型扩展字段3
   */
  @TableField("business_type2")
  private String businessTypeExt3;

  /**
   * 业务类型扩展字段4
   */
  @TableField("business_type3")
  private String businessTypeExt4;

  /**
   * 业务类型扩展字段5
   */
  @TableField("business_type4")
  private String businessTypeExt5;

  /**
   * 业务类型扩展字段6
   */
  @TableField("business_type5")
  private String businessTypeExt6;

  /**
   * 请求URL
   */
  private String url;

  /**
   * 操作渠道
   */
  private String channel;

  /**
   * 备注信息
   */
  private String remark;

  /**
   * 参数名称
   */
  private String paramName;

  /**
   * 请求体内容
   */
  private String reqBody;

  /**
   * 响应结果
   */
  private String resultStr;

  /**
   * 追踪ID
   */
  private String traceId;

  /**
   * 创建人姓名
   */
  private String createUserName;

  /**
   * 登录手机号
   */
  private String loginPhone;

  /**
   * 租户ID
   */
  private Long tenantId;

  /**
   * 是否成功
   */
  private Boolean isSuccess;

  /**
   * 判断操作是否失败
   *
   * @return 如果操作失败返回true，否则返回false
   */
  public boolean isFailed() {
    return Boolean.FALSE.equals(isSuccess);
  }

  /**
   * 设置操作开始时间（基于BaseEntity中的createTime）
   *
   * @param startTime 操作开始时间
   * @return 当前对象，支持链式调用
   */
  public OpLogEntity setStartTime(LocalDateTime startTime) {
    if (startTime != null && this.getCreateTime() != null) {
      this.useTime = java.time.Duration.between(startTime, this.getCreateTime()).toMillis();
    }
    return this;
  }
}
