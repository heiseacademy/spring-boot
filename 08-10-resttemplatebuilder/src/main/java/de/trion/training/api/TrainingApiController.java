package de.trion.training.api;

import de.trion.training.common.Training;
import de.trion.training.common.TrainingRepository;
import de.trion.training.web.TrainingDto;
import de.trion.training.web.TrainingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trainings")
public class TrainingApiController {
   private Logger logger = LoggerFactory.getLogger(getClass());

   private final TrainingRepository trainingRepository;
   private final TrainingMapper trainingMapper;

   public TrainingApiController(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
      this.trainingRepository = trainingRepository;
      this.trainingMapper = trainingMapper;
   }

   @GetMapping
   public List<TrainingDto> get(Pageable pageable) {
      return trainingMapper.toDto(trainingRepository.findAll(pageable).getContent());
   }

   @PostMapping
   public TrainingDto save(@Valid @RequestBody TrainingDto training){
      logger.info("Valides Training: {}", training);
      return trainingMapper.toDto(trainingRepository.save(trainingMapper.fromDto(training)));
   }

   @GetMapping("/locations")
   public List<String> locations() {
      return trainingRepository.findLocations();
   }

   @ExceptionHandler
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public ValidationDto validationHandler(MethodArgumentNotValidException ex) {
      var result = new ValidationDto();
      result.setCode("VALIDATION_ERROR");
      var br = ex.getBindingResult();
      for (final FieldError fr: br.getFieldErrors()) {
         result.addFieldError(new FieldErrorDto(fr.getField(), fr.getDefaultMessage()));
      }
      return result;
   }
}
