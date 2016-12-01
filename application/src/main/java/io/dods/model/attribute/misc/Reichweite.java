package io.dods.model.attribute.misc;

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
public class Reichweite implements Named {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    public Reichweite() {
    }

    public Reichweite(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
