package de.trion.trainingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InMemoryTrainingManager implements TrainingManager {
   private List<Training> trainings;
   private final Logger logger = LoggerFactory.getLogger(getClass());

   @Autowired
   private AppProperties appProperties;

   @Autowired
   private Environment environment;

   public InMemoryTrainingManager() {
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
}
