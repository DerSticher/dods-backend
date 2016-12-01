package io.dods.model.attribute.misc;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Richard Gottschalk
 */
public interface HatReichweite {

    @JsonProperty
    Reichweite getReichweite();

}
