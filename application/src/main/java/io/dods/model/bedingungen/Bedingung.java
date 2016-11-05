package io.dods.model.bedingungen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dods.model.helden.Held;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Richard Gottschalk
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Bedingung implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    public abstract boolean isFulfilled(Held held);
}
