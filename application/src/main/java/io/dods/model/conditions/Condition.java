package io.dods.model.conditions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dods.model.heroes.Hero;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Richard Gottschalk
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AndCondition.class, name = "AND"),
        @JsonSubTypes.Type(value = MaxCondition.class, name = "MAX"),
        @JsonSubTypes.Type(value = MinCondition.class, name = "MIN"),
        @JsonSubTypes.Type(value = NotCondition.class, name = "NOT"),
        @JsonSubTypes.Type(value = OrCondition.class, name = "OR"),
        @JsonSubTypes.Type(value = HasPropertyCondition.class, name = "HAS")
})
@Table(name = "conditions")
public abstract class Condition implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public abstract boolean isFulfilled(Hero hero);
}
