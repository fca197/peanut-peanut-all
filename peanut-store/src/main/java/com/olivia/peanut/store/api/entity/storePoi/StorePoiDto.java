package com.olivia.peanut.store.api.entity.storePoi;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * store poi(StorePoi)查询对象返回
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
//@Accessors(chain=true)
@Getter
@Setter
//@SuppressWarnings("serial")
public class StorePoiDto extends BaseEntityDto {

  /***
   *  上级编码
   */
  @NotBlank(message = "上级编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String poiParentCode;
  /***
   *  poi编码
   */
  @NotBlank(message = "poi编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String poiCode;
  /***
   *  poi名称
   */
  @NotBlank(message = "poi名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String poiName;
  /***
   *  层级
   */
  @NotNull(message = "层级不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer poiLevel;
  /***
   *  poi路径
   */
  @NotBlank(message = "poi路径不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String poiPath;

  private List<StorePoiDto> childPoiDtoList;
}


