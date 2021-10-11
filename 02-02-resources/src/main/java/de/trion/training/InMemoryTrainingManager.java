package de.trion.training;

import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InMemoryTrainingManager implements TrainingManager {
   private List<Training> trainings;

   public InMemoryTrainingManager() {
      trainings = new ArrayList<>();
   }

   public void demoData() {
      save(Training.withLocation("Remote").withTopic("Java").withInstructor("Thomas").build());
      save(Training.withLocation("Video").withTopic("Spring").withInstructor("Thomas").build());

   }

   public void demoFromCsv() {
      var loader = new DefaultResourceLoader();
      try (var is = loader.getResource("/demo.csv").getInputStream();
            var scanner = new Scanner(is)) {
         while (scanner.hasNextLine()) {
            try (var line = new Scanner(scanner.nextLine())) {
               line.useDelimiter(",");

               var training = Training.withId(line.nextInt())
                     .withTopic(line.next())
                     .withLocation(line.next())
                     .withInstructor(line.next()).build();

               trainings.add(training);
            }
         }
      }
      catch (IOException ex) {
         //
      }
   }

   @Override
   public Training save(Training training) {
      trainings.add(training);
      return training;
   }

   @Override
   public List<Training> findAll() {
      return trainings;
   }

   @Override
   public List<String> findLocations() {
      return trainings.stream().map(Training::getLocation).collect(Collectors.toList());
   }
}
