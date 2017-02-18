package io.dods.api.dependency.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Richard Gottschalk
 */
public class CreateEffect {

    @JsonProperty("propertyId")
    private long propertyId;

    @JsonProperty("level")
    private int level;

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
