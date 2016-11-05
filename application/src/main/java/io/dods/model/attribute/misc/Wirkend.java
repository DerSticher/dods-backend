package io.dods.model.attribute.misc;

import org.jetbrains.annotations.NotNull;

/**
 * @author Richard Gottschalk
 */
public interface Wirkend {

    Wirkung getWirkung();

    Wirkungsdauer getWirkungsdauer();

    @NotNull
    default String getBezeichnungWirkung() {
        Wirkung wirkung = getWirkung();
        return wirkung == null ? "" : wirkung.getName();
    }

    @NotNull
    default String getBezeichnungWirkungsdauer() {
        Wirkungsdauer wirkungsdauer = getWirkungsdauer();
        return wirkungsdauer == null ? "" : wirkungsdauer.getName();
    }

}
