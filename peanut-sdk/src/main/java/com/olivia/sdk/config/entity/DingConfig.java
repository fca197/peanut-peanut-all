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
public class DingConfig {

  private String clientId;
  private String clientSecret;
  private Long agentId;
  private String corpId;
  private String robotCode;
  private String dingCode;
  private String dingName;
  private Boolean useStream;
  private String userIdColumnName;
}
