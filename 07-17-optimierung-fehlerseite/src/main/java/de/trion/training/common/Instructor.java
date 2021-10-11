package de.trion.training.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Instructor {

   @Column(name="COL_INSTRUCTOR_NAME")
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
