package de.trion.training;

import de.trion.training.common.AppProperties;
import de.trion.training.common.InMemoryTrainingManager;
import de.trion.training.common.TrainingManager;
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

}
