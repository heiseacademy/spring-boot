package de.trion.training.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SeatService {
   private final Logger logger = LoggerFactory.getLogger(getClass());

   private static String NUMBER_API =
         "http://www.randomnumberapi.com/api/v1.0/randomnumber?min={min}&max={max}";

   private static String LOCAL_API =
         "http://localhost:8080/api/randomnumber?min={min}&max={max}";

   private final RestTemplate restTemplate;

   public SeatService(RestTemplate restTemplate) {
      this.restTemplate = restTemplate;
   }

   public Integer seatsFor(Training t) {
      Integer[] seats = restTemplate.getForObject(NUMBER_API, Integer[].class, 0, 20);
      logger.info("Seats: {} for training {}", seats, t);
      if(seats == null)
      {
         return null;
      }
      return seats[0];
   }
}
