package de.trion.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@EnableJpaAuditing
@SpringBootApplication
public class TrainingAppApplication {

   public static void main(String[] args) {
      SpringApplication.run(TrainingAppApplication.class, args);
   }

   @Bean
   RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
      return restTemplateBuilder
            .setConnectTimeout(Duration.ofMillis(1_500))
            .setReadTimeout(Duration.ofSeconds(2))
            .build();
   }
}
