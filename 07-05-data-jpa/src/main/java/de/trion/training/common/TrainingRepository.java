package de.trion.training.common;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;

public interface TrainingRepository extends TrainingManager, JpaRepository<Training, Integer> {

   @Override
   default List<String> findLocations() {
    return Collections.emptyList();
   }
}
