package io.dods.model.attribute.misc;

import io.dods.model.Named;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Nutzkosten implements Named {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    public Nutzkosten() {
    }

    public Nutzkosten(String name) {
        this.name = name;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }
}
