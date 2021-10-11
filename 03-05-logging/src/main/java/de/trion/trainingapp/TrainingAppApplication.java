package de.trion.trainingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TrainingAppApplication {

   public static void main(String[] args) {
      ConfigurableApplicationContext context = SpringApplication.run(TrainingAppApplication.class, args);
      TrainingController controller = context.getBean(TrainingController.class);
      controller.handle(args);
   }

}
