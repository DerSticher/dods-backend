package io.dods.model.attribute;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dods.interfaces.HasId;
import io.dods.model.Named;
import io.dods.model.attribute.misc.ApFix;
import io.dods.model.attribute.misc.ApVar;
import io.dods.model.attribute.misc.UsesKostentabelle;
import io.dods.model.exceptions.HasNoDefaultLevelException;
import io.dods.model.publikation.Publikation;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typ", discriminatorType = DiscriminatorType.STRING)
public abstract class Attribut implements HasId<Long>, Named, Serializable{

    @ApiModelProperty(notes = "will be set by server", readOnly = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL)
    private Attribut subcategoryOf;

    private String wikiUrl;

    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    @Column
    private String name;

    @ApiModelProperty(notes = "will be set by server", readOnly = true)
    @Column(name = "typ", insertable = false, updatable = false)
    private String typ;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Publikation> publikation;

    public Attribut() {
        this.typ = getClass().getSimpleName();
    }

    public Attribut(String wikiUrl, String name, List<Publikation> publikations) {
        this();
        this.wikiUrl = wikiUrl;
        this.name = name;
        this.publikation = publikations;
    }

    public int calculateAp(int level) {
        if (this instanceof UsesKostentabelle) {
            return ((UsesKostentabelle)this).getAp(level);

        } else if (this instanceof ApVar) {
            return ((ApVar)this).getAp(level);

        } else if (this instanceof ApFix) {
            return ((ApFix)this).getAp();

        }
        throw new IllegalStateException(
                String.format("An attribut has to instanciate %s, %s or %s",
                        UsesKostentabelle.class.getSimpleName(),
                        ApVar.class.getSimpleName(),
                        ApFix.class.getSimpleName()));
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Attribut getSubcategoryOf() {
        return subcategoryOf;
    }

    public void setSubcategoryOf(Attribut subcategoryOf) {
        this.subcategoryOf = subcategoryOf;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribut attribut = (Attribut) o;

        return id == attribut.id;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    @JsonIgnore
    public boolean hasDefaultLevel() {
        try {
            getDefaultLevel();
            return true;
        } catch (HasNoDefaultLevelException ignore) {
            return false;
        }
    }

    @JsonIgnore
    public int getDefaultLevel() throws HasNoDefaultLevelException {
        throw new HasNoDefaultLevelException();
    }

    public List<Publikation> getPublikations() {
        return publikation;
    }
    public void setPublications(List<Publikation> publikation) {
        this.publikation = publikation;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", subcategoryOf=" + subcategoryOf +
                ", wikiUrl='" + wikiUrl + '\'' +
                ", name='" + name + '\'' +
                ", typ='" + typ + '\'' +
                ", publikation=" + publikation +
                '}';
    }

}
