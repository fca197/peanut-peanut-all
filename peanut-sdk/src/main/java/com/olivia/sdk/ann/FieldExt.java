package com.olivia.sdk.ann;


import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldExt {

  String fieldName() default "";
}
