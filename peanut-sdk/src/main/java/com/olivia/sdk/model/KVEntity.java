package com.olivia.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 键值实体类 用于存储键值对形式的数据，支持嵌套结构，适用于树形标签-值、临时键值等场景
 */
@Setter
@Getter
@Accessors(chain = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class KVEntity {

  private Long id;
  private String keyTmp;
  private String valueTmp;
  private String label;
  private String value;
  private List<KVEntity> childrenList;
  private KVEntity children;

  /**
   * 创建KVEntity实例
   *
   * @param label 标签
   * @param value 值
   * @return KVEntity实例
   */
  public static KVEntity of(String label, String value) {
    return new KVEntity().setLabel(label).setValue(value);
  }

  /**
   * 创建带ID的KVEntity实例
   *
   * @param id    ID
   * @param label 标签
   * @param value 值
   * @return KVEntity实例
   */
  public static KVEntity of(Long id, String label, String value) {
    return new KVEntity().setId(id).setLabel(label).setValue(value);
  }

  /**
   * 合并相同层级中具有相同value的KVEntity列表
   *
   * @param entities 待合并的KVEntity列表
   * @return 合并后的KVEntity列表，如果输入为空则返回空列表
   */
  public static List<KVEntity> mergeEntities(List<KVEntity> entities) {
    if (entities == null || entities.isEmpty()) {
      return Lists.newArrayList();
    }

    Map<String, KVEntity> mergedMap = Maps.newHashMap();
    for (KVEntity entity : entities) {
      // 跳过value为空的实体，避免空键问题
      if (entity == null || entity.value == null) {
        continue;
      }

      String value = entity.value;
      mergedMap.compute(value, (k, existing) -> {
        if (existing == null) {
          // 深拷贝避免修改原始对象
          return entity.copy();
        } else {
          existing.merge(entity);
          return existing;
        }
      });
    }

    return Lists.newArrayList(mergedMap.values());
  }

  /**
   * 将另一个KVEntity的信息合并到当前对象中
   *
   * @param other 待合并的KVEntity
   */
  public void merge(KVEntity other) {
    if (other == null) {
      return;
    }

    // 合并临时键值
    if (other.keyTmp != null) {
      this.keyTmp = other.keyTmp;
    }
    if (other.valueTmp != null) {
      this.valueTmp = other.valueTmp;
    }
    // 合并标签（取非空值）
    if (this.label == null && other.label != null) {
      this.label = other.label;
    }
    // 合并ID（取非空值）
    if (this.id == null && other.id != null) {
      this.id = other.id;
    }

    // 合并子列表
    mergeChildrenList(other.childrenList);

    // 递归合并子节点
    mergeChildren(other.children);
  }

  /**
   * 合并子列表
   *
   * @param otherChildren 待合并的子列表
   */
  private void mergeChildrenList(List<KVEntity> otherChildren) {
    if (otherChildren == null || otherChildren.isEmpty()) {
      return;
    }

    if (this.childrenList == null) {
      this.childrenList = Lists.newArrayList();
    }

    // 合并子列表中的元素
    for (KVEntity child : otherChildren) {
      boolean found = false;
      if (this.childrenList != null) {
        for (KVEntity existingChild : this.childrenList) {
          if (Objects.equals(existingChild.value, child.value)) {
            existingChild.merge(child);
            found = true;
            break;
          }
        }
      }
      if (!found) {
        this.childrenList.add(child.copy());
      }
    }
  }

  /**
   * 合并子节点
   *
   * @param otherChild 待合并的子节点
   */
  private void mergeChildren(KVEntity otherChild) {
    if (otherChild == null) {
      return;
    }

    if (this.children == null) {
      this.children = otherChild.copy();
    } else {
      this.children.merge(otherChild);
    }
  }

  /**
   * 创建当前对象的深拷贝
   *
   * @return 深拷贝的KVEntity对象
   */
  public KVEntity copy() {
    KVEntity copy = new KVEntity();
    copy.id = this.id;
    copy.keyTmp = this.keyTmp;
    copy.valueTmp = this.valueTmp;
    copy.label = this.label;
    copy.value = this.value;

    // 拷贝子列表（深拷贝）
    if (this.childrenList != null) {
      copy.childrenList = this.childrenList.stream().map(KVEntity::copy).toList();
    }

    // 拷贝子节点（深拷贝）
    if (this.children != null) {
      copy.children = this.children.copy();
    }

    return copy;
  }

  /**
   * 添加子节点到子列表
   *
   * @param child 子节点
   * @return 当前对象，支持链式调用
   */
  public KVEntity addChild(KVEntity child) {
    if (child == null) {
      return this;
    }

    if (this.childrenList == null) {
      this.childrenList = Lists.newArrayList();
    }

    this.childrenList.add(child);
    return this;
  }

  /**
   * 检查当前节点是否包含指定value的子节点
   *
   * @param value 子节点value
   * @return 如果包含返回true，否则返回false
   */
  public boolean hasChildWithValue(String value) {
    if (this.childrenList == null || this.childrenList.isEmpty() || value == null) {
      return false;
    }

    return this.childrenList.stream().anyMatch(child -> value.equals(child.value));
  }
}
