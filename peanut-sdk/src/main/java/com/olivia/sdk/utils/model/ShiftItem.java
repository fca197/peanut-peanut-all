package com.olivia.sdk.utils.model;

import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class ShiftItem {

  private LocalTime beginTime;
  private LocalTime endTime;
}
