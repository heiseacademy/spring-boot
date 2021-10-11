package de.trion.trainingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainingController {
   private final Logger logger = LoggerFactory.getLogger(getClass());
   private TrainingManager manager;

   @Autowired
   private Environment environment;

   public TrainingController(TrainingManager trainingManager) {
      manager = trainingManager;
   }

   void handle(String[] args) {
      logger.info("Profile: {}", environment.getActiveProfiles());

      if (args.length > 0 && args[0].equalsIgnoreCase("locations")) {
         outputLocations(manager.findLocations());
      } else {
         manager.findAll().forEach(s -> output(s));
      }
   }

   private void outputLocations(List<String> locations) {
      logger.info("Alle Locations:");
      locations.forEach(logger::info);
   }

   private void output(Training s) {
      logger.info("{} - Ort: {} mit Trainer {}", s.getTopic(), s.getLocation(), s.getInstructor().getName());
   }

   public void add(Training training) {
      manager.save(training);
   }
}
