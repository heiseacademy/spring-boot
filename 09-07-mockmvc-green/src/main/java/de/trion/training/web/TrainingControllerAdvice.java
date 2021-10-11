package de.trion.training.web;

import de.trion.training.common.UnknownTrainingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TrainingControllerAdvice {
   private final Logger logger = LoggerFactory.getLogger(getClass());

   @ExceptionHandler
   public String unknownTraining(UnknownTrainingException ex) {
      logger.info("UnknownTrainingException behandelt");
      return "/trainings/unknown";
   }
}
