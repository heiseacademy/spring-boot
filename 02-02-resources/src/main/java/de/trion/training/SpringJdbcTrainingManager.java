package de.trion.training;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpringJdbcTrainingManager implements TrainingManager {
   static final String DB_URL = "jdbc:mariadb://127.0.0.1/training";
   static final String DB_USER = "root";
   static final String DB_PASSWORD = "training";

   private final JdbcTemplate jdbcTemplate;

   public SpringJdbcTrainingManager() {
      var ds = new DriverManagerDataSource(DB_URL, DB_USER, DB_PASSWORD);
      jdbcTemplate = new JdbcTemplate(ds);
   }

   @Override
   public Training save(Training training) {
      var insert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("TBL_TRAININGS")
            .usingGeneratedKeyColumns("COL_ID");
      var key = insert.executeAndReturnKey(
            Map.of("COL_TOPIC", training.getTopic(),
                  "COL_LOCATION", training.getLocation(),
                  "COL_INSTRUCTOR_NAME", training.getInstructor().getName()));
      training.setId(key.intValue());
      return training;
   }

   @Override
   public List<Training> findAll() {
      var query = "SELECT COL_ID, COL_LOCATION, COL_TOPIC, COL_INSTRUCTOR_NAME FROM TBL_TRAININGS";
      return jdbcTemplate.query(query, new RowMapper<Training>() {
         @Override
         public Training mapRow(ResultSet rs, int rowNum) throws SQLException {
            var training = Training.withLocation(rs.getString(2)).withTopic(rs.getString(3)).withInstructor(rs.getString(4)).build();
            training.setId(rs.getInt(1));
            return training;
         }
      });
   }

   @Override
   public List<String> findLocations() {
      var query = "SELECT COL_LOCATION FROM TBL_TRAININGS";
      return jdbcTemplate.queryForStream(query, (rs, i) -> rs.getString(1)).collect(Collectors.toList());
   }
}
