package de.trion.training.common;

import javax.validation.constraints.NotBlank;

public class Instructor {

   @NotBlank
   private String name;

   public Instructor() {}
   public Instructor(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return "Instructor{" +
            "name='" + name + '\'' +
            '}';
   }
}
