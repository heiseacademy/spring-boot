package de.trion.training;

import de.trion.training.common.SeatService;
import de.trion.training.common.Training;
import de.trion.training.web.TrainingDto;
import de.trion.training.web.TrainingWebController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@Import(SeatTestConfig.class)
@SpringBootTest
public class TrainingTests {
   @Autowired
   private TrainingWebController trainingWebController;

   @Test
   void callsSeats() {
      ModelAndView modelAndView = trainingWebController.trainings(Pageable.unpaged());
      List<TrainingDto> trainings = (List<TrainingDto>) modelAndView.getModel().get("trainings");
      assertThat(trainings).hasSize(7)
            .extracting(TrainingDto::getSeats)
            .containsExactly(0, 1, 2, 3, 4, 5, 6);
   }

}
