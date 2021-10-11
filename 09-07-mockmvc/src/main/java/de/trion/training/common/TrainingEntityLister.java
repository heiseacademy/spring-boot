package de.trion.training.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.PostPersist;

public class TrainingEntityLister {
   Logger logger = LoggerFactory.getLogger(getClass());

   @PostPersist
   public void postPersist(Training training) {
      var isactive = TransactionSynchronizationManager.isActualTransactionActive();
      var txname = TransactionSynchronizationManager.getCurrentTransactionName();
      logger.info("SAVE: {} ({}, {})", training, txname, isactive);
   }
}
