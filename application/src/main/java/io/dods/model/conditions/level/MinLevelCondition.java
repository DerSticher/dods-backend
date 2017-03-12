package io.dods.model.conditions.level;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.conditions.level.check.LevelCheck;
import io.dods.model.heroes.Hero;
import io.dods.model.heroes.HeroProperty;
import io.dods.model.properties.Property;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "condition_level_min")
@DiscriminatorValue(MinLevelCondition.NAME)
public class MinLevelCondition extends LevelCondition {

    public static final String NAME = "MIN";

    @JsonProperty("property")
    @ManyToOne
    private Property property;

    public MinLevelCondition() {
    }

    public MinLevelCondition(Property property, LevelCheck levelCheck, int modifier) {
        super(levelCheck, modifier);
        this.property = property;
    }

    @Override
    public boolean isFulfilled(Hero hero) {
        return hero.getProperty(property)
                .map(HeroProperty::getLevel)
                .map(level -> level >= getLevel(hero))
                .orElse(false);
    }

    public Property getProperty() {
        return property;
    }
}
