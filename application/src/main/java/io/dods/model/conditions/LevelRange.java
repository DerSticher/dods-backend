package io.dods.model.conditions;

import io.dods.model.heroes.HeroProperty;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Richard Gottschalk
 */
public class LevelRange {
    private int min;
    private int max;

    public LevelRange() {}

    public LevelRange(Optional<HeroProperty> heroProperty) {
        merge(heroProperty);
    }

    public LevelRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public LevelRange merge(@Nullable LevelRange levelRange) {
        if (levelRange == null) return this;

        min = Math.min(min, levelRange.min);
        max = Math.max(max, levelRange.max);
        return this;
    }

    public LevelRange merge(Optional<HeroProperty> property) {
        if (!property.isPresent()) return this;

        HeroProperty heroProperty = property.get();
        min = Math.min(min, heroProperty.getLevel());
        max = Math.max(max, heroProperty.getLevel());
        return this;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
