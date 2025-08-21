package com.olivia.sdk.comment;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.olivia.sdk.utils.DynamicsPage;

/***
 *
 */
public class ServiceComment {


  public static void header(DynamicsPage<?> page, String bizKey) {
    Object service = SpringUtil.getBean("baseTableHeaderService");
    ReflectUtil.invoke(service, "listByBizKey", page, bizKey);
//    page.addHeader("createUserName","创建人",90);
//    page.addHeader("updateUserName","修改人",90);
  }


}
