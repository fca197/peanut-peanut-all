package com.olivia.sdk.utils.model;

import cn.hutool.core.collection.CollUtil;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class CallBackRunnable implements Runnable {

  List<Exception> exceptionList;

  public boolean isSuccess() {
    return CollUtil.isEmpty(exceptionList);
  }
}
