package com.olivia.peanut.store.api.entity.storePoi;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * store poi(StorePoi)查询对象入参
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class StorePoiQueryByIdListReq {

  private List<Long> idList;

}

