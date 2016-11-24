package io.dods.model.attribute.misc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;

/**
 * @author Richard Gottschalk
 */
public interface Wirkend {

    @NotNull
    String getWirkung();

    Wirkungsdauer getWirkungsdauer();

    @NotNull
    @JsonIgnore
    default String getBezeichnungWirkungsdauer() {
        Wirkungsdauer wirkungsdauer = getWirkungsdauer();
        return wirkungsdauer == null ? "" : wirkungsdauer.getName();
    }

}
