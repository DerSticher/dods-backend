package io.dods.model.attribute.misc;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Richard Gottschalk
 */
public interface HatNutzkosten {

    @JsonProperty
    default Nutzkosten getNutzkosten() {
        return null;
    }

}
