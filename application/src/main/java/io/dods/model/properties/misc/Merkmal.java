package io.dods.model.properties.misc;

import io.dods.model.Named;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(indexes = {
        @Index(columnList = "id")
})
public class Merkmal implements Named {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    public Merkmal() {
    }

    public Merkmal(String name) {
        this.name = name != null ? name : "";
    }

    @Override
    public @NotNull String getName() {
        return name;
    }
}
