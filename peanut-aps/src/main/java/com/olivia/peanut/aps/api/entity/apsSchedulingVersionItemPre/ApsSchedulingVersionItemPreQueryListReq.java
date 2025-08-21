package com.olivia.peanut.aps.api.entity.apsSchedulingVersionItemPre;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 排产下发订单预览(ApsSchedulingVersionItemPre)查询对象入参
 *
 * @author makejava
 * @since 2025-04-06 14:16:39
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionItemPreQueryListReq {

  private ApsSchedulingVersionItemPreDto data;
}

