package de.trion.training.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class HibernateTrainingManager implements TrainingManager{
   private Logger logger = LoggerFactory.getLogger(getClass());

   @Autowired
   private EntityManager entityManager;

   @Override
   public Training save(Training training) {
      entityManager.persist(training);
      logger.info("Neues Training: {}", training.getId());
      return training;
   }

   @Override
   public List<Training> findAll() {
      return entityManager.createQuery("SELECT t FROM Training t", Training.class).getResultList();
   }

   @Override
   public List<String> findLocations() {
      return entityManager.createQuery("SELECT location FROM Training", String.class).getResultList();
   }

   @Override
   public Training getOne(Integer id) {
      return entityManager.find(Training.class, id);
   }
}
