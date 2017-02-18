package io.dods.model.rules;

import io.dods.model.properties.Property;
import io.dods.model.heroes.HeroProperty;
import io.dods.model.heroes.Hero;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;

/**
 *
 * @author Richard Gottschalk
 */
@Entity
public class Effect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int level;

    @ManyToOne
    private Property property;

    public Effect() {
    }

    public Effect(Property property, int level) {
        this.property = property;
        this.level = level;
    }

    public boolean isFulfilled(Hero hero) {
        @Nullable HeroProperty attribut = hero.getProperty(this.property);
        if (attribut != null) {
            return attribut.getLevel() >= level;
        }
        // not available means it can only be not fulfilled!
        return false;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
