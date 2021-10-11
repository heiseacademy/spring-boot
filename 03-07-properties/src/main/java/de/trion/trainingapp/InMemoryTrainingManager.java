package de.trion.trainingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class InMemoryTrainingManager implements TrainingManager, InitializingBean {
   private List<Training> trainings;
   private final Logger logger = LoggerFactory.getLogger(getClass());

   @Autowired
   private AppProperties appProperties;

   public InMemoryTrainingManager() {
      trainings = new ArrayList<>();
   }

   @Override
   public void afterPropertiesSet() throws Exception {
      System.out.println("Initialisierung - afterPropertiesSet");
      demoFromCsv();
   }

   @PostConstruct
   public void demoData() {
      System.out.println("demo data!");
      save(Training.withLocation("Remote").withTopic("Java").withInstructor("Thomas").build());
      save(Training.withLocation("Video").withTopic("Spring").withInstructor("Thomas").build());
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
