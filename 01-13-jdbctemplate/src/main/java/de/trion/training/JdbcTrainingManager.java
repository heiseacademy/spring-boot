package de.trion.training;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbcTrainingManager implements TrainingManager {
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/training";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "training";

    @Override
    public Training save(Training training) {

        try {
            var conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            var stmt = conn.prepareStatement("INSERT INTO TBL_TRAININGS (COL_TOPIC, COL_LOCATION, COL_INSTRUCTOR_NAME) VALUES (?, ?, ?)");
            try (conn; stmt) {
                stmt.setString(1, training.getTopic());
                stmt.setString(2, training.getLocation());
                stmt.setString(3, training.getInstructor().getName());
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return training;
    }

    @Override
    public List<Training> findAll() {
        List<Training> trainings = new ArrayList<>();
        try {
            var conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            var stmt = conn.prepareStatement("SELECT COL_ID, COL_TOPIC, COL_LOCATION, COL_INSTRUCTOR_NAME FROM TBL_TRAININGS");

            try (var result = stmt.executeQuery() ) {
                while (result.next()) {
                    var id = result.getInt("COL_ID");
                    var topic = result.getString("COL_TOPIC");
                    var location = result.getString("COL_LOCATION");
                    var instructorName = result.getString("COL_INSTRUCTOR_NAME");

                    var training = Training.withLocation(location).withTopic(topic).withInstructor(instructorName).build();
                    training.setId(id);
                    trainings.add(training);
                }
            }
            return trainings;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> findLocations() {
        List<String> locations = new ArrayList<>();

        try {
            var conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            var stmt = conn.prepareStatement("SELECT COL_LOCATION, COL_INSTRUCTOR_NAME FROM TBL_TRAININGS");

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    locations.add(resultSet.getString("COL_LOCATION"));
                }
            }
            return locations;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }
}
