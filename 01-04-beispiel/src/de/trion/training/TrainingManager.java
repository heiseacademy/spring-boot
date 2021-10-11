package de.trion.training;

import java.util.ArrayList;
import java.util.List;

public class TrainingManager {
    private List<Training> trainings;

    public TrainingManager() {
        trainings = new ArrayList<>();
    }

    public void save(Training training) {
        trainings.add(training);
    }

    public List<Training> findAll() {
        return trainings;
    }
}
