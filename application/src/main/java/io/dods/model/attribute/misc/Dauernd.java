package io.dods.model.attribute.misc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**
 * @author Richard Gottschalk
 */
public interface Dauernd {

    @JsonProperty
    Dauer getDauer();

    @NotNull
    @JsonIgnore
    default String getBezeichnungDauer() {
        Dauer dauer = getDauer();
        return dauer == null ? "" : dauer.getName();
    }
}
