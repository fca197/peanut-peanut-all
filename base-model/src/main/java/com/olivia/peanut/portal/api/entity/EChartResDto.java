package com.olivia.peanut.portal.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class EChartResDto {

  private List<Series> series;

  public EChartResDto setSeries(List<Series> series) {
    this.series = series;
    return this;
  }

  public EChartResDto setSeries(Series series) {
    this.series = List.of(series);
    return this;
  }


  @JsonProperty("xAxis")
  public XAxis xAxis;

  @JsonProperty("yAxis")
  private YAxis yAxis;

  @Setter
  @Getter
  @Accessors(chain = true)
  public static class XAxis {

    private String type = "category";
    private List<String> data;
  }

  @Getter
  public static class YAxis {
    private String type = "value";
  }

  @Setter
  @Getter
  @Accessors(chain = true)
  public static class Series {
    private List<?> data;
    private String name;
    private String type = "bar";
  }
}
