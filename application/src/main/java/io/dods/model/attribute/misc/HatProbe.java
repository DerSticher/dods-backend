package io.dods.model.attribute.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

/**
 * @author Richard Gottschalk
 */
public interface HatProbe {

    @Nullable
    @JsonProperty
    Probe getProbe();

}