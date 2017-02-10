package io.dods.model.publikation;

import io.dods.interfaces.HasId;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Publikation implements HasId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Werk werk;

    @Column
    private String details;

    public Publikation() {
    }

    public Publikation(Werk werk, String details) {
        this.werk = werk;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Werk getWerk() {
        return werk;
    }

    public void setWerk(Werk werk) {
        this.werk = werk;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
