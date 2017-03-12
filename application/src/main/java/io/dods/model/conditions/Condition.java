package io.dods.model.conditions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dods.model.conditions.level.MaxLevelCondition;
import io.dods.model.conditions.level.MinLevelCondition;
import io.dods.model.conditions.lists.AndCondition;
import io.dods.model.conditions.lists.NOfMCondition;
import io.dods.model.conditions.lists.OrCondition;
import io.dods.model.heroes.Hero;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Richard Gottschalk
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AndCondition.class, name = "AND"),
        @JsonSubTypes.Type(value = MinLevelCondition.class, name = "MIN"),
        @JsonSubTypes.Type(value = MaxLevelCondition.class, name = "MAX"),
        @JsonSubTypes.Type(value = NotCondition.class, name = "NOT"),
        @JsonSubTypes.Type(value = OrCondition.class, name = "OR"),
        @JsonSubTypes.Type(value = HasPropertyCondition.class, name = "HAS"),
        @JsonSubTypes.Type(value = NOfMCondition.class, name = "N_OF_M")
})
@Table(name = "conditions", indexes = {
        @Index(columnList = "id")
})
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Condition implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public abstract boolean isFulfilled(Hero hero);

    public abstract int getMinLevel(Hero hero);
    public abstract int getMaxLevel(Hero hero);

    public Condition() {
    }

    public final LevelRange getLevelRange(Hero hero) {
        int minLevel = getMinLevel(hero);
        int maxLevel = getMaxLevel(hero);
        return new LevelRange(minLevel, maxLevel);
    }

}
