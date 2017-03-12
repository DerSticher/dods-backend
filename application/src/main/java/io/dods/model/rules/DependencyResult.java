package io.dods.model.rules;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.properties.Property;

/**
 * @author Richard Gottschalk
 */
public class DependencyResult {

    public enum Status {
        NOTHING_FULFILLED(true),
        REQUIREMENTS_MET(true),
        REQUIREMENTS_NOT_MET(false),
        LEVEL_EXCEEDED(false),
        FULFILLED(true),
        OBLIGATORY_NOT_FULFILLED(false);

        private boolean isValid;

        Status(boolean isValid) {
            this.isValid = isValid;
        }

        public boolean isValid() {
            return isValid;
        }
    }

    @JsonProperty
    private Status status;

    @JsonProperty
    private Change change;

    public DependencyResult() {
    }

    public DependencyResult(Status status) {
        this.status = status;
    }

    public DependencyResult(Status status, Change change) {
        this.status = status;
        this.setChange(change);
    }

    public void setChange(Change change) {
        this.change = change;
    }

    public Status getStatus() {
        return status;
    }

    public static class Change {
        @JsonProperty
        private Property property;

        @JsonProperty
        private int currentLevel;

        @JsonProperty
        private int maxAllowedLevel;

        public Change(Property property, int currentLevel, int maxAllowedLevel) {
            this.property = property;
            this.currentLevel = currentLevel;
            this.maxAllowedLevel = maxAllowedLevel;
        }

        public Property getProperty() {
            return property;
        }

        public void setProperty(Property property) {
            this.property = property;
        }

        public int getMaxAllowedLevel() {
            return maxAllowedLevel;
        }

        public void setMaxAllowedLevel(int maxAllowedLevel) {
            this.maxAllowedLevel = maxAllowedLevel;
        }

        public int getCurrentLevel() {
            return currentLevel;
        }

        public void setCurrentLevel(int currentLevel) {
            this.currentLevel = currentLevel;
        }
    }
}
