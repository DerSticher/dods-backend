package io.dods.model.bedingungen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dods.model.helden.Held;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Richard Gottschalk
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AndBedingung.class, name = "AND"),
        @JsonSubTypes.Type(value = MaxBedingung.class, name = "MAX"),
        @JsonSubTypes.Type(value = MinBedingung.class, name = "MIN"),
        @JsonSubTypes.Type(value = NotBedingung.class, name = "NOT"),
        @JsonSubTypes.Type(value = OrBedingung.class, name = "OR"),
        @JsonSubTypes.Type(value = VorhandenBedingung.class, name = "HAS")
})
public abstract class Bedingung implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    public abstract boolean isFulfilled(Held held);
}
