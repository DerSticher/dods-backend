package io.dods.model.attribute.misc;

import org.jetbrains.annotations.Contract;

/**
 * Kostentabellen
 *
 * @author Richard Gottschalk
 */
public enum Kostentabelle {
    A(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14),
    B(2, 2, 2, 2, 2, 2, 2 ,2 ,2, 2, 2, 2, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28),
    C(3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42),
    D(4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56),
    E(0, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 30, 45, 60, 75, 90, 105, 120, 135, 150, 165, 180);

    private final int[] costToLevel;

    Kostentabelle(int ... costToLevel) {
        this.costToLevel = costToLevel;
    }

    @Contract(pure = true)
    public int getAp(int targetLevel) {
        int cost = 0;

        for (int level = 0; level <= targetLevel; level++) {
            cost += getApToLevelFromPreviousLevel(level);
        }

        return cost;
    }

    @Contract(pure = true)
    private int getApToLevelFromPreviousLevel(int level) {
        if (isActivated(level)) {
            return costToLevel[level];
        }
        return 0;
    }

    public int getAp(int fromLevel, int targetLevel) {
        return getAp(targetLevel) - getAp(fromLevel);
    }

    @Contract(pure = true)
    public boolean isActivated(int level) {
        return level > 0 || !this.equals(E);
    }

    public static Kostentabelle findOrThrow(String name) throws IllegalArgumentException {
        try {
            return valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Cannot parse %s to a corresponding table", name), e);
        }
    }
}
