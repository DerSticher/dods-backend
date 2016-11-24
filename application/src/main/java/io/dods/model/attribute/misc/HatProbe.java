package io.dods.model.attribute.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**
 * @author Richard Gottschalk
 */
public interface HatProbe {

    @NotNull
    @JsonProperty
    Probe getProbe();

}
