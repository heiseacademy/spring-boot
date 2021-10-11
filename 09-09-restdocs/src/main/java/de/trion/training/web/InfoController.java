package de.trion.training.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class InfoController {

   @RequestMapping("/info")
   public ModelAndView info() {
      return new ModelAndView("info", Map.of("heading", "Info!"));
   }
}
