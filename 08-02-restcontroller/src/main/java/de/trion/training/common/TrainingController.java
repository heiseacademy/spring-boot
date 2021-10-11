package de.trion.training.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainingController {
   private final Logger logger = LoggerFactory.getLogger(getClass());
   private final TrainingRepository trainingRepository;

   public TrainingController(TrainingRepository trainingRepository) {
      this.trainingRepository = trainingRepository;
   }

   void handle(String[] args) {

      if (args.length > 0 && args[0].equalsIgnoreCase("locations")) {
         outputLocations(trainingRepository.findLocations());
      } else {
         trainingRepository.findAll().forEach(s -> output(s));
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
      trainingRepository.save(training);
   }
}
