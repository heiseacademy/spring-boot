package de.trion.training.web;

import de.trion.training.common.AppProperties;
import de.trion.training.common.Training;
import de.trion.training.common.TrainingManager;
import de.trion.training.common.UnknownTrainingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/trainings")
public class TrainingWebController {

   private final TrainingManager trainingManager;
   private final AppProperties appProperties;

   public TrainingWebController(TrainingManager trainingManager, AppProperties appProperties) {
      this.trainingManager = trainingManager;
      this.appProperties = appProperties;
   }

   @GetMapping("")
   public ModelAndView trainings() {
      var mav = new ModelAndView("/trainings/list");
      var trainings = trainingManager.findAll();
      mav.addObject("heading", "Alle Trainings");
      mav.addObject("trainings", trainings);

      mav.addObject("training", Training.withLocation(appProperties.getDefaultLocation()).build());

      return mav;
   }

   @GetMapping("{id}")
   public ModelAndView training(@PathVariable Integer id) {
      var mav = new ModelAndView("/trainings/detail");
      var training = trainingManager.getOne(id);

      mav.addObject("training", training);
      return mav;
   }

   @PostMapping("")
   public String addTraining(@Valid Training training, BindingResult result, Model model) {

      if (result.hasErrors()) {
         model.addAttribute("trainings", trainingManager.findAll());
         model.addAttribute("heading", "Alle Trainings");

         return "/trainings/list";
      }

      trainingManager.save(training);
      return "redirect:/trainings";
   }
}
