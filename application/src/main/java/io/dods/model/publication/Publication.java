package io.dods.model.publication;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.interfaces.HasId;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Publication implements HasId<Long>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty("werk")
    private Book book;

    @Column
    @JsonProperty("seite")
    private int page;

    public Publication() {
    }

    public Publication(Book book, int page) {
        this.book = book;
        this.page = page;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int details) {
        this.page = details;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", book=" + book +
                ", page=" + page +
                '}';
    }
}
