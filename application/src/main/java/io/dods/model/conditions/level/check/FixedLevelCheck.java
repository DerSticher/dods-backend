package io.dods.model.conditions.level.check;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.heroes.Hero;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "level_check_fixed")
@DiscriminatorValue(FixedLevelCheck.NAME)
public class FixedLevelCheck extends LevelCheck {

    public static final String NAME = "Fix";

    @JsonProperty
    @Column
    private int level;

    public FixedLevelCheck() {
    }

    public FixedLevelCheck(int level) {
        this.level = level;
    }

    @Override
    public int getLevel(Hero hero) {
        return level;
    }
}
