package de.trion.training;

public class Main {

   public static void main(String[] args) {
      new Main().run(args);
   }

   private void run(String[] args) {
        var trainingManager = new InMemoryTrainingManager();
        trainingManager.demoFromCsv();
//        trainingManager.demoData();

      //var trainingManager = new JdbcTrainingManager();

      //var trainingManager = new SpringJdbcTrainingManager();
      new TrainingController(trainingManager).handle(args);
   }
}
