package de.trion.training;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

   public static void main(String[] args) {
      new Main().run(args);
   }

   private void run(String[] args) {
      var context = new AnnotationConfigApplicationContext(AppConfig.class);
      TrainingController controller = context.getBean(TrainingController.class);

      controller.add(Training.withTopic("Spring Context").withLocation("Remote").withInstructor("Thomas").build());

      controller.handle(args);

      context.close();
   }
}
