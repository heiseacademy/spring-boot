package de.trion.training.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("training")
public class AppProperties {
   private String greeting = "Hello World!";
   private String defaultLocation;

   public String getGreeting() {
      return greeting;
   }

   public void setGreeting(String greeting) {
      this.greeting = greeting;
   }

   public String getDefaultLocation() {
      return defaultLocation;
   }

   public void setDefaultLocation(String defaultLocation) {
      this.defaultLocation = defaultLocation;
   }
}
