package io.dods.api.hero.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Richard Gottschalk
 */
public class CharacterPropertyUpdate {

    @JsonProperty("attributId")
    private long propertyId;

    @JsonProperty("level")
    private int level;

    public long getPropertyId() {
        return propertyId;
    }

    public int getLevel() {
        return level;
    }
}
