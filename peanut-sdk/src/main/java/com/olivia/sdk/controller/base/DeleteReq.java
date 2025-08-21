package com.olivia.sdk.controller.base;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 删除请求参数实体类 用于接收批量删除操作的ID列表参数，并提供参数合法性校验
 */
@Getter
@Setter
@Accessors(chain = true)
public class DeleteReq {

  /**
   * 需要删除的数据ID列表 不能为空且长度限制在1-40之间，防止一次性删除过多数据导致性能问题
   */
  @NotEmpty(message = "删除的ID列表不能为空")
  @Size(min = 1, max = 40, message = "删除的ID数量必须在{min}到{max}之间")
  private List<Long> idList;
}
