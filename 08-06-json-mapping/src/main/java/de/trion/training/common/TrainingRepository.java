package de.trion.training.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collections;
import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Integer> {

   //@Query(value = "SELECT COL_LOCATION FROM TBL_TRAININGS",nativeQuery = true)
   @Query("SELECT t.location FROM Training t")
   List<String> findLocations();
}
