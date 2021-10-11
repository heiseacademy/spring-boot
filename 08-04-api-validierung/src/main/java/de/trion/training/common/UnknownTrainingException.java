package de.trion.training.common;

public class UnknownTrainingException extends RuntimeException {
   public UnknownTrainingException(IndexOutOfBoundsException ex) {
      super(ex);
   }

   public UnknownTrainingException(String message) {
      super(message);
   }
}
