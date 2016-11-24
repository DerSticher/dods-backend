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
public class Dauer implements Named {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    public Dauer() {
    }

    public Dauer(String name) {
        this.name = name != null ? name : "";
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
