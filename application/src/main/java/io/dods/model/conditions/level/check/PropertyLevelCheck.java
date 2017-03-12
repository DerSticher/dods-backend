package io.dods.model.conditions.level.check;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.heroes.Hero;
import io.dods.model.heroes.HeroProperty;
import io.dods.model.properties.Property;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "level_check_property")
@DiscriminatorValue(PropertyLevelCheck.NAME)
public class PropertyLevelCheck extends LevelCheck {

    public static final String NAME = "Property";

    @JsonProperty
    @OneToOne
    private Property property;

    public PropertyLevelCheck() {
    }

    public PropertyLevelCheck(Property property) {
        this.property = property;
    }

    @Override
    public int getLevel(Hero hero) {
        if (hero.getProperty(property).isPresent()) {
            HeroProperty heroProperty = hero.getProperty(property).get();
            return heroProperty.getLevel();
        }
        return 0;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
