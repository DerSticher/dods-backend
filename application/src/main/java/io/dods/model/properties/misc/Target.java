package io.dods.model.properties.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.interfaces.HasId;
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
public class Target implements HasId<Long>, Named {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Target() {
    }

    public Target(String name) {
        this.name = name != null ? name : "";
    }

    @Override
    public @NotNull String getName() {
        return name;
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
