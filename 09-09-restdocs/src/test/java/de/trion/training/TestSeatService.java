package de.trion.training;

import de.trion.training.common.SeatService;
import de.trion.training.common.Training;
import org.springframework.web.client.RestTemplate;

public class TestSeatService extends SeatService {
   private Integer seats = 0;
   public TestSeatService(RestTemplate restTemplate) {
      super(restTemplate);
   }
   public TestSeatService() {
      super(new RestTemplate());
   }

   @Override
   public Integer seatsFor(Training t) {
      return seats++;
   }
}
