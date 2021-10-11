package de.trion.training.web;

import de.trion.training.common.AppProperties;
import de.trion.training.common.SeatService;
import de.trion.training.common.Training;
import de.trion.training.common.TrainingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/trainings")
public class TrainingWebController {

   private final AppProperties appProperties;
   private final TrainingRepository trainingRepository;
   private final TrainingMapper trainingMapper;
   private final SeatService seatService;

   public TrainingWebController(AppProperties appProperties, TrainingRepository trainingRepository, TrainingMapper trainingMapper, SeatService seatService) {
      this.appProperties = appProperties;
      this.trainingRepository = trainingRepository;
      this.trainingMapper = trainingMapper;
      this.seatService = seatService;
   }

   @GetMapping("")
   public ModelAndView trainings(Pageable pageable) {
      var mav = new ModelAndView("/trainings/list");
      var trainings = trainingRepository.findAll(pageable);
      var trainingsWithSeats = trainings.getContent().stream().map(t ->{
         var seats = seatService.seatsFor(t);
         var dto = trainingMapper.toDto(t);
         dto.setSeats(seats);
         return dto;
      }).collect(Collectors.toList());

      mav.addObject("heading", "Alle Trainings");
      mav.addObject("trainings", trainingsWithSeats);

      mav.addObject("page", pageable);
      mav.addObject("totalPages", trainings.getTotalPages());
      mav.addObject("totalElements", trainings.getTotalElements());

      mav.addObject("training", trainingMapper.toDto(Training.withLocation(appProperties.getDefaultLocation()).build()));

      return mav;
   }

   @GetMapping("{id}")
   public ModelAndView training(@PathVariable Integer id) {
      var mav = new ModelAndView("/trainings/detail");
      var training = trainingRepository.getOne(id);

      mav.addObject("training", trainingMapper.toDto(training));
      return mav;
   }

   @GetMapping("{id}/edit")
   public ModelAndView editTraining(@PathVariable Integer id) {
      var mav = new ModelAndView("/trainings/edit");
      var training = trainingRepository.getOne(id);

      mav.addObject("training", trainingMapper.toDto(training));
      return mav;
   }

   @PostMapping("{id}")
   public String saveEdit(@PathVariable Integer id, @Valid @ModelAttribute TrainingDto training,
                          BindingResult bindingResult, HttpServletRequest request)  {
      if(bindingResult.hasErrors()) {
         return "/trainings/edit";
      }

      request.setAttribute("training", training);
      trainingRepository.save(trainingMapper.fromDto(training));
      return "redirect:/trainings/%d".formatted(id);
   }

   @ExceptionHandler
   public ModelAndView trainingEditConcurrent(ObjectOptimisticLockingFailureException ex, HttpServletRequest request) {
      var mav = new ModelAndView("/trainings/concurrent");
      mav.addObject("training", request.getAttribute("training"));
      mav.addObject("id", ex.getIdentifier());

      var current = trainingRepository.getById( (Integer) ex.getIdentifier());
      mav.addObject("current", trainingMapper.toDto(current));
      return mav;
   }

   @Transactional
   @PostMapping("")
   public String addTraining(@ModelAttribute("training") @Valid TrainingDto training, BindingResult result, Model model,
                             Pageable pageable) {
      if (result.hasErrors()) {
         model.addAttribute("trainings", trainingMapper.toDto(trainingRepository.findAll()));
         model.addAttribute("heading", "Alle Trainings");
         model.addAttribute("page", pageable);
         return "/trainings/list";
      }
      var savedTraining = trainingRepository.save(trainingMapper.fromDto(training));

//      if (savedTraining.getId() % 2 == 0) {
//         throw new RuntimeException("Fehler!");
//      }
      return "redirect:/trainings";
   }
}
