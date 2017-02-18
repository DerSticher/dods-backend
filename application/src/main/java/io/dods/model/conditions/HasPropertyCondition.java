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
@Table(name = "condition_has_property")
public class HasPropertyCondition extends Condition {

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
        @Nullable HeroProperty eigenschaft = hero.getProperty(property);
        return eigenschaft != null;
    }

}
