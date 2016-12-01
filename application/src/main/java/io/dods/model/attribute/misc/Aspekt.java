package io.dods.model.attribute.misc;

import io.dods.model.Named;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Aspekt implements Named {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    public Aspekt() {
    }

    public Aspekt(String name) {
        this.name = name != null ? name : "";
    }

    @Override
    public @NotNull String getName() {
        return name;
    }
}
