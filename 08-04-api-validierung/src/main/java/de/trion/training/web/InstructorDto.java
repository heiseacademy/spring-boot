package de.trion.training.web;

import javax.validation.constraints.NotEmpty;

public class InstructorDto {

   @NotEmpty
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
