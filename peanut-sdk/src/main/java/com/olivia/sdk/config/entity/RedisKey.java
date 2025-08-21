package com.olivia.sdk.config.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class RedisKey {

  private String userToken;
  private String loginLock;
}
