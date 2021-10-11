package de.trion.training;

import de.trion.training.common.TrainingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class AppContainerTest {
   @Autowired
   MockMvc mockMvc;

   @Container
   static MariaDBContainer<?> db = new MariaDBContainer<>(MariaDBContainer.NAME)
         .withUsername("user")
         .withPassword("password")
         .withDatabaseName("training");

   @DynamicPropertySource
   static void configure(DynamicPropertyRegistry registry) {
      registry.add("spring.datasource.url", db::getJdbcUrl);
      registry.add("spring.datasource.username", db::getUsername);
      registry.add("spring.datasource.password", db::getPassword);
   }

   @Test
   public void contextLoads() {

   }

   @Autowired
   TrainingRepository repository;

   @Test
   public void saveTraining() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.post("/trainings")
            .param("location", "remote")
            .param("topic", "Unit Test")
            .param("instructor.name", "Mr. Robot"));

      assertThat(repository.findAll()).hasSize(1);
   }

}
