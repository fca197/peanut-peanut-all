package com.olivia.sdk.utils;

import org.apache.commons.lang3.StringUtils;

/***
 *
 */
public interface Str {

  String LIMIT_1 = " limit 1";

  String ROW_TOTAL = " count(1) as row_total ";

  String TENANT_ID = "tenant_id";
  String tenantId = "tenantId";

//  String H2 = "h2";
//  String SQLITE = "sqlite";

  String DEFAULT = "default";
  String DEFAULT_ZN = "默认";
  String DISTINCT = "  DISTINCT ";

  String FACTORY_NAME = "factoryName";
  String FACTORY_ID = "factoryId";
  String OFFSET_ID = "+8";

  String UN_CHECKED = "unchecked";

  static boolean isBefore(String str1, String str2) {
    return StringUtils.isNotBlank(str1) && str1.compareTo(str2) < 0;
  }

  static boolean isAfter(String str1, String str2) {
    return StringUtils.isNotBlank(str1) && str1.compareTo(str2) > 0;
  }

  static String booleanToStr(Boolean b) {
    return Boolean.TRUE.equals(b) ? "是" : "否";
  }

  interface ReqHeader {

    String USER_CHANNEL = "x-user-channel";
    String J_TOKEN = "j-token";
  }
}
