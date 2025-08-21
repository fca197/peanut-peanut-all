package com.olivia.sdk.ann;


import cn.hutool.core.util.DesensitizedUtil.DesensitizedType;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MaskValue {

  DesensitizedType value() default DesensitizedType.FIRST_MASK;

}
