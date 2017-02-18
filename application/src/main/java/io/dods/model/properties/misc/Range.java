package io.dods.model.properties.misc;

import io.dods.interfaces.HasId;
import io.dods.model.Named;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "ranges",
        indexes = {
        @Index(columnList = "id")
})
public class Range implements HasId<Long>, Named {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Lob
    private String name;

    public Range() {
    }

    public Range(String name) {
        this.name = name;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
