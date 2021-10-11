package de.trion.training.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

   @RequestMapping("/hello")
   public String hello() {
      return "hello.html";
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

}
