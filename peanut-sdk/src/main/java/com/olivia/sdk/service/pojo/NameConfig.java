package com.olivia.sdk.service.pojo;

import cn.hutool.core.collection.CollUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 名称配置类 用于定义ID字段与名称字段的映射关系，支持一个ID字段对应多个名称字段
 */
@Setter
@Getter
@Accessors(chain = true)
public class NameConfig {

  /**
   * ID字段名
   */
  private String idField;

  /**
   * 名称字段列表
   */
  private List<String> nameFieldList;

  public NameConfig() {
    nameFieldList = new ArrayList<>();
  }

  /**
   * 构造函数
   *
   * @param idField ID字段名
   */
  public NameConfig(String idField) {
    this.idField = idField;
    this.nameFieldList = new ArrayList<>();
  }

  public NameConfig(String idField, String nameField) {
    this(idField);
    this.nameFieldList.add(nameField);
  }

  /**
   * 构造函数
   *
   * @param idField    ID字段名
   * @param nameFields 名称字段数组
   */
  public NameConfig(String idField, String... nameFields) {
    this(idField);
    if (nameFields != null && nameFields.length > 0) {
      CollUtil.addAll(this.nameFieldList, nameFields);
    }
  }

  /**
   * 添加单个名称字段
   *
   * @param nameField 名称字段名
   * @return 当前对象，支持链式调用
   */
  public NameConfig addNameField(String nameField) {
    if (this.nameFieldList == null) {
      this.nameFieldList = new ArrayList<>();
    }
    this.nameFieldList.add(nameField);
    return this;
  }

  /**
   * 设置单个名称字段（替换现有列表）
   *
   * @param nameField 名称字段名
   * @return 当前对象，支持链式调用
   */
  public NameConfig setNameField(String nameField) {
    this.nameFieldList = CollUtil.newArrayList(nameField);
    return this;
  }

  /**
   * 设置多个名称字段（替换现有列表）
   *
   * @param nameFields 名称字段列表
   * @return 当前对象，支持链式调用
   */
  public NameConfig setNameFields(List<String> nameFields) {
    this.nameFieldList = CollUtil.isEmpty(nameFields) ? new ArrayList<>() : new ArrayList<>(nameFields);
    return this;
  }

  /**
   * 检查配置是否有效
   *
   * @return 有效返回true，否则返回false
   */
  public boolean isValid() {
    return idField != null && !idField.trim().isEmpty() && CollUtil.isNotEmpty(nameFieldList);
  }
}
