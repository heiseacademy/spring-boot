package de.trion.training;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTrainingManager implements TrainingManager {
    private List<Training> trainings;

    public InMemoryTrainingManager() {
        trainings = new ArrayList<>();
    }

    public void demoData() {
        save(Training.withLocation("Remote").withTopic("Java").withInstructor("Thomas").build());
        save(Training.withLocation("Video").withTopic("Spring").withInstructor("Thomas").build());

    }

    @Override
    public void save(Training training) {
        trainings.add(training);
    }

    @Override
    public List<Training> findAll() {
        return trainings;
    }

    @Override
    public List<String> findLocations() {
        return trainings.stream().map(Training::getLocation).collect(Collectors.toList());
    }
}
