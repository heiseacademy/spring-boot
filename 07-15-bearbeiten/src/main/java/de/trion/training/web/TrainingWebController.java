package de.trion.training.web;

import de.trion.training.common.AppProperties;
import de.trion.training.common.Training;
import de.trion.training.common.TrainingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/trainings")
public class TrainingWebController {

   private final AppProperties appProperties;
   private final TrainingRepository trainingRepository;

   public TrainingWebController(AppProperties appProperties, TrainingRepository trainingRepository) {
      this.appProperties = appProperties;
      this.trainingRepository = trainingRepository;
   }

   @GetMapping("")
   public ModelAndView trainings(Pageable pageable) {
      var mav = new ModelAndView("/trainings/list");
      var trainings = trainingRepository.findAll(pageable);
      mav.addObject("heading", "Alle Trainings");
      mav.addObject("trainings", trainings.getContent());

      mav.addObject("page", pageable);
      mav.addObject("totalPages", trainings.getTotalPages());
      mav.addObject("totalElements", trainings.getTotalElements());

      mav.addObject("training", Training.withLocation(appProperties.getDefaultLocation()).build());

      return mav;
   }

   @GetMapping("{id}")
   public ModelAndView training(@PathVariable Integer id) {
      var mav = new ModelAndView("/trainings/detail");
      var training = trainingRepository.getOne(id);

      mav.addObject("training", training);
      return mav;
   }

   @GetMapping("{id}/edit")
   public ModelAndView editTraining(@PathVariable Integer id) {
      var mav = new ModelAndView("/trainings/edit");
      var training = trainingRepository.getOne(id);

      mav.addObject("training", training);
      return mav;
   }

   @PostMapping("{id}")
   public String saveEdit(@PathVariable Integer id, @Valid @ModelAttribute Training training,
                                BindingResult bindingResult)  {
      if(bindingResult.hasErrors()) {
         return "/trainings/edit";
      }

      trainingRepository.save(training);
      return "redirect:/trainings/%d".formatted(id);
   }

   @Transactional
   @PostMapping("")
   public String addTraining(@Valid Training training, BindingResult result, Model model) {
      if (result.hasErrors()) {
         model.addAttribute("trainings", trainingRepository.findAll());
         model.addAttribute("heading", "Alle Trainings");
         return "/trainings/list";
      }
      var savedTraining = trainingRepository.save(training);

//      if (savedTraining.getId() % 2 == 0) {
//         throw new RuntimeException("Fehler!");
//      }
      return "redirect:/trainings";
   }
}
