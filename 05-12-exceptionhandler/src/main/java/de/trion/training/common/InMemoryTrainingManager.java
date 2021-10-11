package de.trion.training.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InMemoryTrainingManager implements TrainingManager {
   private List<Training> trainings;
   private final Logger logger = LoggerFactory.getLogger(getClass());

   private final AppProperties appProperties;
   private final Environment environment;

   public InMemoryTrainingManager(AppProperties appProperties, Environment environment) {
      this.appProperties = appProperties;
      this.environment = environment;
      trainings = new ArrayList<>();
   }

   public void demoData() {
         logger.warn("Achtung demodaten in dev!");
         demoFromCsv();
   }

   @PreDestroy
   void predestroy() {
      logger.debug("Ich beende mich!");
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
      if(training.getLocation() == null || training.getLocation().isEmpty())
      {
         training.setLocation(appProperties.getDefaultLocation());
      }
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

   @Override
   public Training getOne(Integer id) {
      return trainings.get(id);
   }
}
