package de.trion.training.web;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class TrainingDto {
   private Integer id;
   private Integer version;

   @NotBlank
   private String location;

   @Size(min=4, max=100)
   private String topic;

   @Valid
   private InstructorDto instructor;

   private String description;
   private LocalDateTime createdOn;
   private LocalDateTime modifiedOn;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getVersion() {
      return version;
   }

   public void setVersion(Integer version) {
      this.version = version;
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

   public InstructorDto getInstructor() {
      return instructor;
   }

   public void setInstructor(InstructorDto instructor) {
      this.instructor = instructor;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public LocalDateTime getCreatedOn() {
      return createdOn;
   }

   public void setCreatedOn(LocalDateTime createdOn) {
      this.createdOn = createdOn;
   }

   public LocalDateTime getModifiedOn() {
      return modifiedOn;
   }

   public void setModifiedOn(LocalDateTime modifiedOn) {
      this.modifiedOn = modifiedOn;
   }
}
