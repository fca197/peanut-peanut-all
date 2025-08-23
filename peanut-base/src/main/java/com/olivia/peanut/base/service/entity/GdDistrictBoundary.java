package com.olivia.peanut.base.service.entity;

import java.util.List;
import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GdDistrictBoundary {

  private String status;
  private String info;
  private String infocode;
  private String count;
  private List<DistrictsDTO> districts;


  @NoArgsConstructor
  @Data
  public static class DistrictsDTO {

    private String adcode;
    private String name;
    private String polyline;
    private String center;
  }

  public boolean isFail() {
    return !Objects.equals("1", status);
  }
}
