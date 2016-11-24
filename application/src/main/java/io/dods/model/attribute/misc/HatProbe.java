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

    @NotNull
    @JsonProperty
    default String getBezeichnungProbe() {
        Probe probe = getProbe();
        return probe == null ? "" : probe.getBezeichnung();
    }
}
