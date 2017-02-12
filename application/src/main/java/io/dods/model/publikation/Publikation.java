package io.dods.model.publikation;

import io.dods.interfaces.HasId;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Publikation implements HasId<Long>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Werk werk;

    @Column
    private int page;

    public Publikation() {
    }

    public Publikation(Werk werk, int page) {
        this.werk = werk;
        this.page = page;
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

    public int getPage() {
        return page;
    }

    public void setPage(int details) {
        this.page = details;
    }

    @Override
    public String toString() {
        return "Publikation{" +
                "id=" + id +
                ", werk=" + werk +
                ", page=" + page +
                '}';
    }
}
