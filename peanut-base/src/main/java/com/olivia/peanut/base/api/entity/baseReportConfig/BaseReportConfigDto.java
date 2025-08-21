package com.olivia.peanut.base.api.entity.baseReportConfig;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
//import com.alibaba.fastjson2.annotation.JSONField;

/**
 * 报表配置(BaseReportConfig)查询对象返回
 *
 * @author makejava
 * @since 2025-03-29 12:32:13
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseReportConfigDto extends BaseEntityDto {

  /***
   *  报表名称
   */
  @NotBlank(message = "报表名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //  @JSONField(label = "reportName")

  private String reportName;
  /***
   *  报表路径
   */
  @NotBlank(message = "报表路径不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //  @JSONField(label = "reportUrl")

  private String reportUrl;

}


