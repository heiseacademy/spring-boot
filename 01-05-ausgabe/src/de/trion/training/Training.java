package de.trion.training;

public class Training {
    private String location;
    private String topic;
    private Instructor instructor;

    public static TrainingBuilder withLocation(String location) {
        return new TrainingBuilder().withLocation(location);
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
