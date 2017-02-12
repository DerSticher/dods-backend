package io.dods.model.publikation;

import io.dods.interfaces.HasId;
import io.dods.model.Named;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Werk implements HasId<Long>, Named, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String shop;

    public Werk() {
    }

    public Werk(String name) {
        this.name = name;
    }

    public String getName() {
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

    @Override
    public String toString() {
        return "Werk{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shop='" + shop + '\'' +
                '}';
    }
}
