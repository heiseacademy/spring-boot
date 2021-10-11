package de.trion.training;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	 new Main().run(args);
    }

    private void run(String[] args) {
        new TrainingController().handle(args);
    }


}
