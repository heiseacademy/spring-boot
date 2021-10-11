package de.trion.trainingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class SecondRunner implements ApplicationRunner {
   private Logger logger = LoggerFactory.getLogger(getClass());
   @Override
   public void run(ApplicationArguments args) throws Exception {

      logger.info("Second runner ist aufgerufen!");
   }
}
