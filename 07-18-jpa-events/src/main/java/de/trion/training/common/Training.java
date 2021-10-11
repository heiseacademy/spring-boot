package de.trion.training.common;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EntityListeners(TrainingEntityLister.class)
@Entity
@Table(name = "TBL_TRAININGS")
public class Training {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="COL_ID")
   private Integer id;

   @Version
   @Column(name = "COL_VERSION")
   private Integer version;

   @Column(name="COL_LOCATION")
   @NotBlank
   private String location;

   @Column(name="COL_TOPIC")
   @Size(min=4, max=100)
   private String topic;

   @Valid
   private Instructor instructor;

   @Column(name="COL_DESCRIPTION")
   private String description;

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

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

   public Integer getVersion() {
      return version;
   }

   public void setVersion(Integer version) {
      this.version = version;
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

      public TrainingBuilder withDescription(String text) {
         template.description = text;
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
