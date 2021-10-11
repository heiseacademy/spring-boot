package de.trion.trainingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrainingAppApplication {

   public static void main(String[] args) {
      ConfigurableApplicationContext context = SpringApplication.run(TrainingAppApplication.class, args);
      TrainingController controller = context.getBean(TrainingController.class);

      controller.add(Training.withLocation("").withTopic("Properties Demo").withInstructor("Thomas").build());

      controller.handle(args);
   }

   @ConditionalOnProperty(name="training.demo", havingValue = "true")
   @Bean
   TrainingManager devTrainingManager() {
      var manager = new InMemoryTrainingManager();
      manager.demoFromCsv();
      return manager;
   }

   @ConditionalOnMissingBean(TrainingManager.class)
   @Bean
   TrainingManager trainingManager() {
      return new InMemoryTrainingManager();
   }
}
