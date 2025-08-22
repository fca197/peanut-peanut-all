package com.olivia.peanut.store.api.entity.storePoi;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class StorePoiSelectTreeRes {

  private List<StorePoiDto> dataList;
}
