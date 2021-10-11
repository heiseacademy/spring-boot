package de.trion.training;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class AppConfig {

   static final String DB_URL = "jdbc:mariadb://127.0.0.1/training";
   static final String DB_USER = "root";
   static final String DB_PASSWORD = "training";

   @Bean
   public JdbcTemplate jdbcTemplate (DataSource dataSource) {
      return new JdbcTemplate(dataSource);
   }

   @Bean
   public DataSource dataSource() {
      return new DriverManagerDataSource(DB_URL, DB_USER, DB_PASSWORD);
   }

   @Qualifier("persistent")
   @Bean
   public SpringJdbcTrainingManager springJdbcTrainingManager(JdbcTemplate jdbcTemplate) {
      return new SpringJdbcTrainingManager(jdbcTemplate);
   }
}
