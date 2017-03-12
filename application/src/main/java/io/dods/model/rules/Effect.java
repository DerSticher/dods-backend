package io.dods.model.rules;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.conditions.LevelRange;
import io.dods.model.creation.Experience;
import io.dods.model.heroes.Hero;
import io.dods.model.heroes.HeroProperty;
import io.dods.model.properties.*;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Richard Gottschalk
 */
@Entity
@Table(indexes = {
        @Index(columnList = "id")
})
public class Effect {

    @JsonProperty("value")
    @Enumerated(EnumType.STRING)
    @Nullable
    private Experience.Value value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty
    @Column
    private int modifier;

    @ManyToOne
    private Property property;

    public Effect() {
        this(null);
    }

    public Effect(Property property) {
        this(property, 0);
    }

    public Effect(Property property, int modifier) {
        this(property, modifier, null);
    }

    public Effect(Property property, int modifier, @Nullable Experience.Value value) {
        this.property = property;
        this.modifier = modifier;

        if (value != null) {
            this.value = value;
        } else if (property != null && property.getType() != null) {
            switch (property.getType()) {
                case Skill.NAME:
                    this.value = Experience.Value.MAX_SKILL_VALUE;
                    break;
                case Attribute.NAME:
                    this.value = Experience.Value.MAX_ATTRIBUTE_VALUE;
                    break;
            }
        }
    }

    boolean isFulfilledByExperience(Hero hero, LevelRange levelRange) {
        if (value != null) {
            switch (value) {
                case MAX_ATTRIBUTE_VALUE:
                case MAX_SKILL_VALUE:
                case MAX_COMBAT_TECHNIQUE:
                    return hero.getProperty(property)
                            .map(HeroProperty::getLevel)
                            .map(level -> level <= getMaxLevel(hero, levelRange))
                            .orElse(false);

                case MAX_ATTRIBUTE_TOTAL:
                    return hero.getHeroProperties(Attribute.NAME)
                            .stream()
                            .mapToInt(HeroProperty::getLevel)
                            .sum() <= getExperienceBasedMaxLevel(hero.getStartingExperience(), value);

                case MAX_SPELL_OR_LITURGICAL_CHANT_COUNT:
                    List<HeroProperty> liturgicalChants = hero.getHeroProperties(LiturgicalChant.NAME);
                    List<HeroProperty> spells = hero.getHeroProperties(Spell.NAME);

                    return liturgicalChants.size() <= getExperienceBasedMaxLevel(hero.getStartingExperience(), value)
                            && spells.size() <= getExperienceBasedMaxLevel(hero.getStartingExperience(), value);

                case MAX_FROM_OTHER_TRADITIONS:
                    // TODO: As soon as traditions are implemented
                    break;
            }
        } else if (property != null) {
            return hero.getProperty(property).isPresent();
        }
        return false;
    }

    public int getMaxLevel(Hero hero, LevelRange levelRange) {
        return getMaxLevel(hero, value, levelRange);
    }

    private int getMaxLevel(Hero hero, Experience.Value value, LevelRange levelRange) {
        int experienceBasedMaxLevel = getExperienceBasedMaxLevel(hero.getStartingExperience(), value);
        int levelRangeBasedMaxLevel = getLevelRangeBasedMaxLevel(levelRange);

        if (hero.ignoreExperience()) {
            return levelRangeBasedMaxLevel;
        }
        return Math.min(experienceBasedMaxLevel, levelRangeBasedMaxLevel);
    }

    private int getLevelRangeBasedMaxLevel(LevelRange levelRange) {
        return levelRange.getMax() + modifier;
    }

    private int getExperienceBasedMaxLevel(Experience experience, Experience.Value value) {
        if (value == null) return 1;
        return experience.getValue(value);
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
