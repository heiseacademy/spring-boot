package de.trion.training.web;

import de.trion.training.common.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Optional;

@Controller
public class IndexController {

   private Logger logger = LoggerFactory.getLogger(getClass());

   @RequestMapping("/hello")
   public ModelAndView hello() {
      return new ModelAndView("hello", Map.of("heading", "Hello Model and View!"));
   }

   @RequestMapping("/")
   public String helloRedirect() {
      return "redirect:/hello";
   }

   @RequestMapping("/ok")
   public ResponseEntity<String> ok() {
      return ResponseEntity.ok("OK!");
   }

   @RequestMapping(path="/ok", method = RequestMethod.POST)
   public ResponseEntity<String> postOk() {
      return ResponseEntity.ok("POST OK!");
   }

   @RequestMapping(path="/ok", method = RequestMethod.POST, params = "key")
   public ResponseEntity<String> paramOk() {
      return ResponseEntity.ok("POST PARAM OK!");
   }

   @RequestMapping("/echo")
   public ResponseEntity<String> echo(@RequestParam(defaultValue = "default") String input) {
      return ResponseEntity.ok(input);
   }

   @RequestMapping("/write")
   public void write(@RequestHeader(defaultValue = "unset") String head, OutputStream out) throws IOException {
      logger.info("Header: {}", head);
      var osw = new OutputStreamWriter(out);
      osw.write("out!\n");
      osw.write("header: %s".formatted(head));
      osw.close();
   }

   @PostMapping("/training")
   public ResponseEntity<String> training(Training training) {
      logger.info("Training erhalten: {}", training);
      return ResponseEntity.ok("Training: %s".formatted(training));
   }
}
