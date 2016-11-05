package io.dods.model.attribute.misc;

import org.jetbrains.annotations.NotNull;

/**
 * @author Richard Gottschalk
 */
public interface Dauernd {

    Dauer getDauer();

    @NotNull
    default String getBezeichnungDauer() {
        Dauer dauer = getDauer();
        return dauer == null ? "" : dauer.getName();
    }
}
