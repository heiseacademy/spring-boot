package de.trion.training;

import de.trion.training.common.SeatService;
import de.trion.training.common.Training;
import de.trion.training.common.TrainingRepository;
import de.trion.training.web.TrainingDto;
import de.trion.training.web.TrainingWebController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
//@Import(SeatTestConfig.class)
@SpringBootTest
public class TrainingBackendTests {
   @MockBean
   private SeatService service;

   @SpyBean
   private TrainingWebController trainingWebController;

   @Autowired
   private TrainingRepository trainingRepository;

   @BeforeEach
   public void setup() {
      when(service.seatsFor(any(Training.class))).thenReturn(0);
   }

   @Test
   void callsSeats() {
      ModelAndView modelAndView = trainingWebController.trainings(Pageable.unpaged());
      List<TrainingDto> trainings = (List<TrainingDto>) modelAndView.getModel().get("trainings");
      assertThat(trainings).hasSize(7)
            .extracting(TrainingDto::getSeats)
            .containsOnly(0);
            //.containsExactly(0, 1, 2, 3, 4, 5, 6);
      doReturn(null).when(trainingWebController).trainings(any(Pageable.class));
      var second = trainingWebController.trainings(Pageable.unpaged());
      assertThat(second).isNull();

      verify(trainingWebController, times(2)).trainings(any(Pageable.class));
   }

   @Disabled
   @DirtiesContext
   @Test
   public void clear() {
      trainingRepository.deleteAll();

   }

}
