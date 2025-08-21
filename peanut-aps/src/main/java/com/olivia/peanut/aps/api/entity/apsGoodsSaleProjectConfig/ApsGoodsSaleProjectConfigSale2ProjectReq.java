package com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsGoodsSaleProjectConfigSale2ProjectReq {

  @NotNull(message = "goodsId不能为空")
  private Long goodsId;
  @NotBlank(message = "销售配置不能为空")
  private String saleConfig;
  @NotNull(message = "转换数量不能为空")
  @Min(value = 1, message = "转换数量不能小于1")
  @Max(value = 20000, message = "转换数量不能大于20000")
  private Long convertCount;
  private LocalDate bizKey;
  private Long id;
}
