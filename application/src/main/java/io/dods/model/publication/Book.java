package io.dods.model.publication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.interfaces.HasId;
import io.dods.model.Named;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Book implements HasId<Long>, Named, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @JsonProperty
    @Column
    private String shopUrl;

    @JsonProperty("pflicht")
    @Column
    private Boolean isRequired;

    public Book() {
    }

    public Book(String name) {
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

    @JsonIgnore
    public Boolean getRequired() {
        return isRequired;
    }

    @JsonIgnore
    public String getShopUrl() {
        return shopUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shopUrl='" + shopUrl + '\'' +
                '}';
    }
}
