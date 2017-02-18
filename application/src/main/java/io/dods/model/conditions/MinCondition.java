package io.dods.model.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.properties.Property;
import io.dods.model.heroes.HeroProperty;
import io.dods.model.heroes.Hero;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "condition_min")
public class MinCondition extends Condition {

    @JsonProperty("property")
    @ManyToOne
    private Property property;

    @JsonProperty("level")
    private int level;

    public MinCondition() {
    }

    public MinCondition(Property property, int level) {
        this.property = property;
        this.level = level;
    }

    @Override
    public boolean isFulfilled(Hero hero) {
        @Nullable HeroProperty heroProperty = hero.getProperty(this.property);
        if (heroProperty != null) {
            return heroProperty.getLevel() >= this.level;
        }
        return false;
    }

    public Property getProperty() {
        return property;
    }

    public int getLevel() {
        return level;
    }
}
