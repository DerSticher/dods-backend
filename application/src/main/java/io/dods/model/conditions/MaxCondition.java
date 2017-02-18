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
@Table(name = "condition_max")
public class MaxCondition extends Condition {

    @JsonProperty("property")
    @ManyToOne
    private Property property;

    @JsonProperty("level")
    private int level;

    public MaxCondition() {
    }

    public MaxCondition(Property property, int level) {
        this.property = property;
        this.level = level;
    }

    @Override
    public boolean isFulfilled(Hero hero) {
        @Nullable HeroProperty heroProperty = hero.getProperty(this.property);
        if (heroProperty != null) {
            return heroProperty.getLevel() <= this.level;
        }
        // not available means it can only be under the given level!
        return true;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
