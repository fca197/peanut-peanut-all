package com.olivia.sdk.config.web.socket.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Setter
@Getter
@Accessors(chain = true)
@JsonInclude(Include.NON_NULL)
public class WebSocketMessage {

  private String messageType;
  private String messageTypeDesc;
  private String messageTitle;
  private String messageContent;
  private String messageDetailUrl;
  private String messageCallBackFunName;
  private String requestId;

  private String tmp01;
  private String tmp02;
  private String tmp03;
  private String tmp04;
  private String tmp05;
  private String tmp06;

  private LocalDateTime messageDateTime;

}
