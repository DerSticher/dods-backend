package io.dods.model.conditions.level.check;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.creation.Experience;
import io.dods.model.heroes.Hero;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "level_check_experience")
@DiscriminatorValue(ExperienceLevelCheck.NAME)
public class ExperienceLevelCheck extends LevelCheck {

    public static final String NAME = "Experience";

    @JsonProperty("value")
    @Enumerated(EnumType.STRING)
    @Nullable
    private Experience.Value value;

    public ExperienceLevelCheck() {
    }

    public ExperienceLevelCheck(Experience.Value value) {
        this.value = value;
    }

    @Override
    public int getLevel(Hero hero) {
        return hero.getStartingExperience().getValue(value);
    }

}
