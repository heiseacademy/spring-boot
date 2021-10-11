package de.trion.training.web;

import de.trion.training.common.AppProperties;
import de.trion.training.common.Training;
import de.trion.training.common.TrainingManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
