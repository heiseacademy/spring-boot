package de.trion.training;

public class Main {

    public static void main(String[] args) {
	 new Main().run(args);
    }

    private void run(String[] args) {
        var manager = new TrainingManager();

        manager.save(Training.withLocation("Remote").withTopic("Java").withInstructor("Thomas").build());

        manager.findAll().forEach(s -> output(s));
    }

    private void output(Training s) {
        System.out.printf("%s - Ort: %s mit Trainer %s", s.getTopic(), s.getLocation(), s.getInstructor().getName());
    }
}
