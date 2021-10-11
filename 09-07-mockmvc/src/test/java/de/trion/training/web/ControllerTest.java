package de.trion.training.web;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
   @Autowired
   MockMvc mockMvc;

   @Test
   public void index() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/trainings").queryParam("size", "2"))
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("/trainings/list"))
            .andExpect(model().attribute("trainings", Matchers.hasSize(2)));
   }

   @Test
   public void add() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.post("/trainings")
            .param("location", "Unit Test")
            .param("instructor.name", "Mr. Robot")
            .param("topic", "Testing")
      )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/trainings"));
   }

   @Test
   public void validation() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.post("/trainings")
                  .param("location", "Unit Test")
                  .param("instructor.name", "Mr. Robot")
                  .param("topic", "tst")
            )
            .andExpect(status().isOk())
            .andExpect(view().name("/trainings/list"))
            .andExpect(model().attributeHasFieldErrors("training", "topic"));
   }
}
