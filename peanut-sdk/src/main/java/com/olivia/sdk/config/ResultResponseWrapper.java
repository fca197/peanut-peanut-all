package com.olivia.sdk.config;

import com.olivia.sdk.result.Result;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局响应结果包装器 统一API接口的响应格式，将所有接口返回值包装为Result对象 特殊处理Feign调用场景，保持服务间调用的兼容性
 */
@Slf4j
@RestControllerAdvice
public class ResultResponseWrapper implements ResponseBodyAdvice<Object> {

  /**
   * Feign调用标识头
   */
  private static final String FEIGN_HEADER_KEY = "X-REQ_METHOD";

  /**
   * Feign调用标识值
   */
  private static final String FEIGN_HEADER_VALUE = "FEIGN";

  /**
   * 判断当前响应是否需要被包装 始终返回true，表示对所有接口进行包装处理
   *
   * @param returnType    方法返回类型
   * @param converterType 消息转换器类型
   * @return 始终返回true
   */
  @Override
  public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  /**
   * 响应体写出前的处理 将返回值统一包装为Result对象，特殊处理Feign调用场景
   *
   * @param body                  原始响应体
   * @param returnType            方法返回类型
   * @param selectedContentType   选中的媒体类型
   * @param selectedConverterType 选中的消息转换器类型
   * @param request               服务器请求对象
   * @param response              服务器响应对象
   * @return 包装后的响应体
   */
  @Override
  public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType,
      @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
    // 处理Feign调用场景
    if (isFeignRequest(request)) {
      return handleFeignResponse(body, response);
    }
    // 统一包装响应结果
    return wrapResponse(body);
  }

  /**
   * 处理Feign调用响应 当Feign调用且响应为失败结果时，设置HTTP状态码为417
   *
   * @param body     原始响应体
   * @param response 服务器响应对象
   * @return 处理后的响应体
   */
  private Object handleFeignResponse(Object body, ServerHttpResponse response) {
    // 如果是失败的Result对象，设置HTTP状态码
    if (body instanceof Result<?> result && !result.isSuccess()) {
      response.setStatusCode(HttpStatus.EXPECTATION_FAILED);
      log.trace("Feign调用返回失败结果，设置HTTP状态码为417");
    }
    return body;
  }

  /**
   * 包装响应结果为Result对象 如果已经是Result对象则直接返回，否则包装为成功结果
   *
   * @param body 原始响应体
   * @return 包装后的Result对象
   */
  private Object wrapResponse(Object body) {
    // 已经是Result对象则直接返回
    if (body instanceof Result<?>) {
      return body;
    }
    // 包装为成功结果
    return Result.success(body);
  }

  /**
   * 判断是否为Feign调用 检查请求头中是否包含Feign调用标识
   *
   * @param request 服务器请求对象
   * @return 如果是Feign调用返回true，否则返回false
   */
  private boolean isFeignRequest(ServerHttpRequest request) {
    // 获取请求头的值
    List<String> headerValues = request.getHeaders().get(FEIGN_HEADER_KEY);

    // 如果请求头不存在或为空，直接返回false
    if (CollectionUtils.isEmpty(headerValues)) {
      return false;
    }

    // 检查是否包含Feign标识值（忽略大小写）
    return headerValues.stream().anyMatch(value -> FEIGN_HEADER_VALUE.equalsIgnoreCase(value.trim()));
  }
}
