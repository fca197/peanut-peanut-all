package com.olivia.peanut.aps.api.entity.apsMachineWorkstation;

import com.olivia.peanut.aps.api.entity.apsMachineWorkstationItem.ApsMachineWorkstationItemDto;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
//import com.alibaba.fastjson2.annotation.JSONField;

/**
 * aps 生产机器 工作站(ApsMachineWorkstation)查询对象返回
 *
 * @author admin
 * @since 2025-07-23 13:20:06
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMachineWorkstationDto extends BaseEntityDto {

  /***
   *  工作站编号
   */
  @NotBlank(message = "工作站编号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //  @JSONField(label = "machineWorkstationNo")
  private String machineWorkstationNo;

  /***
   *  工作站名称
   */
  @NotBlank(message = "工作站名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //  @JSONField(label = "machineWorkstationName")
  private String machineWorkstationName;

  /***
   *  最小功率
   */
  //  @JSONField(label = "minPower")
  // @NotNull(message = "最小功率不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //private BigDecimal minPower;

  /***
   *  最大功率
   */
  //  @JSONField(label = "maxPower")
  // @NotNull(message = "最大功率不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer maxPower;

  /***
   *  工厂ID
   */
  //  @JSONField(label = "factoryId")
  @NotNull(message = "工厂ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;

  /***
   *  排序索引
   */
  //  @JSONField(label = "sortIndex")
  //  @NotNull(message = "排序索引不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long sortIndex;


  @Size(min = 1, groups = {InsertCheck.class, UpdateCheck.class})
  @Valid
  private List<ApsMachineWorkstationItemDto> machineWorkstationItemDtoList;

  private String factoryName;

  @NotNull(groups = {InsertCheck.class, UpdateCheck.class}, message = "耗时不能为空")
  private Long useTime;
}


