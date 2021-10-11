package de.trion.training;

import de.trion.training.common.SeatService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class SeatTestConfig {
   @Bean
   @Primary
   SeatService testSeatService() {
      return new TestSeatService();
   }
}
