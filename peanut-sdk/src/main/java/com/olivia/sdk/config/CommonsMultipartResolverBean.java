package com.olivia.sdk.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
@Component
public class CommonsMultipartResolverBean {

  @Autowired
  PeanutProperties peanutProperties;


}
