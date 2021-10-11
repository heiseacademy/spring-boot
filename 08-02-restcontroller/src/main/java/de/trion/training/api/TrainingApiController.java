package de.trion.training.api;

import de.trion.training.common.Training;
import de.trion.training.common.TrainingRepository;
import de.trion.training.web.TrainingDto;
import de.trion.training.web.TrainingMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainings")
public class TrainingApiController {
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
   public Training save(@RequestBody Training training){
      return trainingRepository.save(training);
   }
}
