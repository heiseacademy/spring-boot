package de.trion.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrainingManagerTest {
    private TrainingManager trainingManager;

    @BeforeEach
    public void setUp() {
        trainingManager = new InMemoryTrainingManager();
    }

    @Test
    public void emptyManager() {
        assertThat(trainingManager.findAll()).isEmpty();
    }

    @Test
    public void saveTraining() {
        trainingManager.save(Training.withLocation("demo").withTopic("test").withInstructor("junit").build());

        assertThat(trainingManager.findAll()).isNotEmpty();
    }
}
