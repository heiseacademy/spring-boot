package de.trion.trainingapp;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class BootcampRunner implements ApplicationRunner {

   private final TrainingController trainingController;

   public BootcampRunner(TrainingController trainingController) {
      this.trainingController = trainingController;
   }

   @Override
   public void run(ApplicationArguments args) throws Exception {

      trainingController.add(Training.withLocation("").withTopic("Properties Demo").withInstructor("Thomas").build());

      trainingController.handle(args.getSourceArgs());

   }
}
