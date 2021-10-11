package de.trion.training.api;

import de.trion.training.web.InstructorDto;
import de.trion.training.web.TrainingDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.BasicJsonTester;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
public class TrainingApiTest {
   @Autowired
   private MockMvc mockMvc;
   @Autowired
   private BasicJsonTester jsonTester;

   @Autowired
   private JacksonTester<TrainingDto> jacksonTester;

   @Test
   public void get() throws Exception {
      String content = mockMvc.perform(MockMvcRequestBuilders.get("/api/trainings")
                  .param("size", "7"))
            //.andDo(print())
            .andDo(document("get", preprocessResponse(prettyPrint())))
            .andReturn().getResponse().getContentAsString();

      assertThat(jsonTester.from(content))
            .extractingJsonPathArrayValue("$").hasSize(7);
   }

   @Test
   public void add() throws Exception {
      var training = new TrainingDto();
      training.setTopic("Testing");
      training.setLocation("Unit Test");
      var instructor = new InstructorDto();
      instructor.setName("Mr. Robot");
      training.setInstructor(instructor);

      String content = mockMvc.perform(MockMvcRequestBuilders.post("/api/trainings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jacksonTester.write(training).getJson()))
            //.andDo(print())
            .andReturn().getResponse().getContentAsString();

      assertThat(jacksonTester.parseObject(content))
            .extracting(TrainingDto::getTopic)
            .isEqualTo("Testing");
   }
}
