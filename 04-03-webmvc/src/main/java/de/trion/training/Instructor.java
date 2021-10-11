package de.trion.training;

public class Instructor {
   private String name;

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
