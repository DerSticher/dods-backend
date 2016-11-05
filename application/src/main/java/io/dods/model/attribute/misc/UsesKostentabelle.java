package io.dods.model.attribute.misc;

import org.jetbrains.annotations.NotNull;

/**
 * @author Richard Gottschalk
 */
public interface UsesKostentabelle extends ApVar {

    @NotNull
    Kostentabelle getKostentabelle();

    @Override
    default int getAp(int level) {
        if (!isActivated(level)) return 0;
        return getKostentabelle().getAp(level);
    }

    @Override
    default boolean isActivated(int level) {
        return getKostentabelle().isActivated(level);
    }
}
