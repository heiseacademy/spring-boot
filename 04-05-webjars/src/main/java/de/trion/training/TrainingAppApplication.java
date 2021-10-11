package de.trion.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class TrainingAppApplication {

   public static void main(String[] args) {
      SpringApplication.run(TrainingAppApplication.class, args);
   }

   @ConditionalOnProperty(name="training.demo", havingValue = "true")
   @Bean
   TrainingManager devTrainingManager(AppProperties appProperties, Environment environment) {
      var manager = new InMemoryTrainingManager(appProperties, environment);
      manager.demoFromCsv();
      return manager;
   }

   @ConditionalOnMissingBean(TrainingManager.class)
   @Bean
   TrainingManager trainingManager(AppProperties appProperties, Environment environment) {
      return new InMemoryTrainingManager(appProperties, environment);
   }
}
