package de.trion.training.api;

import de.trion.training.common.Training;
import de.trion.training.common.TrainingRepository;
import de.trion.training.web.TrainingDto;
import de.trion.training.web.TrainingMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
   @ApiResponses(value ={
         @ApiResponse(responseCode = "200", description = "Trainings abgefragt",
         content = {@Content(mediaType = "application/json",
         schema = @Schema(implementation = TrainingDto.class))}),
         @ApiResponse(responseCode = "400",
         description = "Ungueltige Paginierungsdaten angefordert")
   })
   @Parameters(value={
         @Parameter(name="size", description = "Anzahl Elemente pro Seite", in = ParameterIn.QUERY),
         @Parameter(name="page", description = "Abzufragende Seite", in=ParameterIn.QUERY),
         @Parameter(name="sort", description = "Property zur Sortierung", in=ParameterIn.QUERY,
         array = @ArraySchema(schema = @Schema(type="string", example = "topic,desc"))),
         @Parameter(name = "pageable", hidden = true, description = "Spring provided")
   })
   @Operation(summary = "Abfrage aller Trainings", description = "Hier werden alle Trainings abgefragt")
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
