package io.dods.api.dependency.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Richard Gottschalk
 */
public class CreateEffect {

    @JsonProperty("propertyId")
    private long propertyId;

    @JsonProperty("absoluteLevel")
    private int absoluteLevel;

    @JsonProperty("relativeLevel")
    private int relativeLevel;

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public int getAbsoluteLevel() {
        return absoluteLevel;
    }

    public void setAbsoluteLevel(int absoluteLevel) {
        this.absoluteLevel = absoluteLevel;
    }

    public int getRelativeLevel() {
        return relativeLevel;
    }

    public void setRelativeLevel(int relativeLevel) {
        this.relativeLevel = relativeLevel;
    }
}
