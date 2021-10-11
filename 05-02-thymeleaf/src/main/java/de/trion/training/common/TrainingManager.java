package de.trion.training.common;

import java.util.List;

public interface TrainingManager {
   Training save(Training training);

   List<Training> findAll();

   List<String> findLocations();
}
