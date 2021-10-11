package de.trion.training;

import de.trion.training.common.Training;
import de.trion.training.common.TrainingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest()
@TestPropertySource(locations = "classpath:test.properties")
class TrainingAppApplicationTests {

   @Test
   void contextLoads() {
   }

   @Value("${demo:}")
   private String demoProperty;

   @Autowired
   Environment environment;

   @DynamicPropertySource
   public static void dynamic(DynamicPropertyRegistry registry) {
      registry.add("demo", () -> "dynamicproperty--" + LocalTime.now());
   }

   @Test
   public void showProperty() throws Exception {
      assertThat(demoProperty).isNotNull();
      System.out.printf("Demo: '%s' %n", demoProperty);
      System.out.printf("env: '%s' %n", environment.getProperty("demo"));
      TimeUnit.SECONDS.sleep(1);
      System.out.printf("Demo: '%s' %n", demoProperty);
      System.out.printf("env: '%s' %n", environment.getProperty("demo"));
   }

   @Autowired
   TrainingRepository trainingRepository;

   @Test
   void locations() {
      trainingRepository.deleteAll();
      trainingRepository.save(Training.withLocation("Test").withTopic("Test").withInstructor("Thomas").build());
      assertThat(trainingRepository.findLocations()).hasSize(1);
   }

}
