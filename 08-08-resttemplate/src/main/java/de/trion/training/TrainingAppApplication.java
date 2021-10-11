package de.trion.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@EnableJpaAuditing
@SpringBootApplication
public class TrainingAppApplication {

   public static void main(String[] args) {
      SpringApplication.run(TrainingAppApplication.class, args);
   }

   @Bean
   RestTemplate restTemplate() {
      return new RestTemplate();
   }
}
