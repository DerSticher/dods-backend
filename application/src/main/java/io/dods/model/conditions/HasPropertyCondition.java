package io.dods.model.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "condition_has_property")
@DiscriminatorValue(HasPropertyCondition.NAME)
public class HasPropertyCondition extends Condition {

    public static final String NAME = "HAS";

    @JsonProperty("property")
    @ManyToOne
    private Property property;

    public HasPropertyCondition() {
    }

    public HasPropertyCondition(Property property) {
        this.property = property;
    }

    @Override
    public boolean isFulfilled(Hero hero) {
        return hero.getProperty(property).isPresent();
    }

    @Override
    public int getMinLevel(Hero hero) {
        return hero.getProperty(property)
                .map(HeroProperty::getLevel)
                .orElse(0);
    }

    @Override
    public int getMaxLevel(Hero hero) {
        return hero.getProperty(property)
                .map(HeroProperty::getLevel)
                .orElse(0);
    }

}
