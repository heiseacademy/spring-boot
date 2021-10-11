package de.trion.training.common;

import org.springframework.stereotype.Service;

@Service
public class SeatService {

   public Integer seatsFor(Training t) {
      return 4;
   }
}
