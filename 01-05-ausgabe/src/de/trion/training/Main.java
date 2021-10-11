package de.trion.training;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	 new Main().run(args);
    }

    private void run(String[] args) {
        var manager = new TrainingManager();
        manager.save(Training.withLocation("Remote").withTopic("Java").withInstructor("Thomas").build());

        if (args.length > 0 && args[0].equalsIgnoreCase("locations"))
        {
            outputLocations(manager.findLocations());
        }
        else {
            manager.findAll().forEach(s -> output(s));
        }
    }

    private void outputLocations(List<String> locations) {
        System.out.println("Alle Locations:");
        locations.forEach(System.out::println);
    }

    private void output(Training s) {
        System.out.printf("%s - Ort: %s mit Trainer %s", s.getTopic(), s.getLocation(), s.getInstructor().getName());
    }
}
