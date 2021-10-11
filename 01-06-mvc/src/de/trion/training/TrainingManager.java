package de.trion.training;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainingManager {
    private List<Training> trainings;

    public TrainingManager() {
        trainings = new ArrayList<>();

        save(Training.withLocation("Remote").withTopic("Java").withInstructor("Thomas").build());
        save(Training.withLocation("Video").withTopic("Spring").withInstructor("Thomas").build());

    }

    public void save(Training training) {
        trainings.add(training);
    }

    public List<Training> findAll() {
        return trainings;
    }

    public List<String> findLocations() {
        return trainings.stream().map(Training::getLocation).collect(Collectors.toList());
    }
}
