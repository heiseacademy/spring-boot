package de.trion.trainingapp;

public class Training {
   private Integer id;
   private String location;
   private String topic;
   private Instructor instructor;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public static TrainingBuilder withLocation(String location) {
      return new TrainingBuilder().withLocation(location);
   }

   public static TrainingBuilder withTopic(String topic) {
      return new TrainingBuilder().withTopic(topic);
   }

   public static TrainingBuilder withInstructor(String name) {
      return new TrainingBuilder().withInstructor(name);
   }

   public static TrainingBuilder withId(Integer id) {
      return new TrainingBuilder().withId(id);
   }

   public String getLocation() {
      return location;
   }

   public void setLocation(String location) {
      this.location = location;
   }

   public String getTopic() {
      return topic;
   }

   public void setTopic(String topic) {
      this.topic = topic;
   }

   public Instructor getInstructor() {
      return instructor;
   }

   public void setInstructor(Instructor instructor) {
      this.instructor = instructor;
   }

   public static class TrainingBuilder {
      private Training template = new Training();

      public TrainingBuilder withLocation(String location) {
         template.location = location;
         return this;
      }

      public TrainingBuilder withTopic(String topic) {
         template.topic = topic;
         return this;
      }

      public TrainingBuilder withInstructor(String name) {
         template.instructor = new Instructor(name);
         return this;
      }

      public TrainingBuilder withId(Integer id) {
         template.id = id;
         return this;
      }

      public Training build() {
         return template;
      }
   }

   @Override
   public String toString() {
      return "Training{" +
            "location='" + location + '\'' +
            ", topic='" + topic + '\'' +
            ", instructor=" + instructor +
            '}';
   }
}
