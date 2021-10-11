package de.trion.training.web;

import de.trion.training.common.AppProperties;
import de.trion.training.common.Training;
import de.trion.training.common.TrainingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
