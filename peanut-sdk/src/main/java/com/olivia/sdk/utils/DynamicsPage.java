package com.olivia.sdk.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.*;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class DynamicsPage<T> extends Page<T> {

  //  long current;
  //  long size;
  List<T> dataList;

  private List<Header> headerList = new ArrayList<>();

  public static <R> DynamicsPage<R> init(DynamicsPage<?> page, List<R> data) {
    DynamicsPage<R> dynamicsPage = new DynamicsPage<R>().setDataList(data).setCurrent(page.getCurrent()).setHeaderList(page.getHeaderList());
    dynamicsPage.setTotal(page.getTotal()).setSize(page.getSize());
    return dynamicsPage;
  }

  public static <R> DynamicsPage<R> init(Page<?> page, List<Header> headerList, List<R> data) {
    DynamicsPage<R> dynamicsPage = new DynamicsPage<R>().setDataList(data).setCurrent(page.getCurrent()).setHeaderList(headerList);
    dynamicsPage.setTotal(page.getTotal()).setSize(page.getSize());
    return dynamicsPage;
  }

  public static <R> DynamicsPage<R> init(Page<R> page) {
    DynamicsPage<R> dynamicsPage = new DynamicsPage<R>().setDataList(page.getRecords()).setCurrent(page.getCurrent()).setHeaderList(List.of());
    dynamicsPage.setTotal(page.getTotal()).setSize(page.getSize());
    return dynamicsPage;
  }

  @Override
  public DynamicsPage<T> setSize(long size) {
    super.setSize(size);
    return this;
  }

  public DynamicsPage<T> setCurrent(long current) {
    super.setCurrent(current);
    return this;
  }

  public DynamicsPage<T> addHeader(String field, String showName) {
    return addHeader(field, showName, 100);
  }

  public DynamicsPage<T> addHeader(String field, String showName, Integer width) {
    headerList.add(new Header().setFieldName(field).setShowName(showName).setWidth(width));
    return this;
  }

  @Setter
  @Getter
  @Accessors(chain = true)
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Header {

    String fieldName;
    String showName;
    int width = 100;

    private String sortValue;

    public Header setWidth(Integer width) {
      if (Objects.nonNull(width)) {
        this.width = width;
      }
      return this;
    }
  }
}
