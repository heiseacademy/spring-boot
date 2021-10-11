package de.trion.training;

import java.util.List;

public interface TrainingManager {
    void save(Training training);

    List<Training> findAll();

    List<String> findLocations();
}
