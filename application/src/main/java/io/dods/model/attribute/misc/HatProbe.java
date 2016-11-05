package io.dods.model.attribute.misc;

import org.jetbrains.annotations.NotNull;

/**
 * @author Richard Gottschalk
 */
public interface HatProbe {

    @NotNull
    Probe getProbe();

    @NotNull
    default String getBezeichnungProbe() {
        Probe probe = getProbe();
        return probe == null ? "" : probe.getBezeichnung();
    }
}
