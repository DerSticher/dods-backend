package io.dods.model.attribute.misc;

import org.jetbrains.annotations.Nullable;

/**
 * @author Richard Gottschalk
 */
public interface UsesKostentabelle extends ApVar {

    @Nullable
    Kostentabelle getKostentabelle();

    @Override
    default int getAp(int level) {
        if (!isActivated(level)) return 0;
        Kostentabelle kostentabelle = getKostentabelle();
        if (kostentabelle != null) {
            return kostentabelle.getAp(level);
        }
        return 0;
    }

    @Override
    default boolean isActivated(int level) {
        Kostentabelle kostentabelle = getKostentabelle();
        if (kostentabelle != null) {
            return kostentabelle.isActivated(level);
        } else {
            return level >= 0;
        }
    }
}
