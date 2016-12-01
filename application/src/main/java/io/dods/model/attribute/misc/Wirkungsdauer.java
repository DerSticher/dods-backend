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
public class Wirkungsdauer implements Named {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    public Wirkungsdauer() {
    }

    public Wirkungsdauer(String name) {
        this.name = name != null ? name : "";
    }

    @Override
    public @NotNull String getName() {
        return name;
    }
}
