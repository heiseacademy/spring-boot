package de.trion.training.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/randomnumber")
public class RandomNumberController {
   private final Random random = new Random();

   @GetMapping
   public List<Integer> random(@RequestParam Integer min, @RequestParam Integer max) {
      return List.of(random.nextInt(max + 1 - min) + min);
   }
}
