package com.olivia.sdk.utils;

import java.lang.reflect.Method;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class SpelUtils {

  public static String parseKey(String expression, Method method, Object[] args) {
    if (StringUtils.isBlank(expression)) {
      return null;
    }
    ExpressionParser parser = new SpelExpressionParser();
    StandardReflectionParameterNameDiscoverer discoverer = new StandardReflectionParameterNameDiscoverer();
    String[] paramArr = discoverer.getParameterNames(method);
    if (paramArr == null || paramArr.length == 0) {
      return "";
    }
    EvaluationContext context = new StandardEvaluationContext();
    for (int len = 0; len < paramArr.length; len++) {
      context.setVariable(paramArr[len], args[len]);
    }
    return String.valueOf(parser.parseExpression(expression).getValue(context));
  }
}
