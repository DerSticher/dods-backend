package io.dods.model.attribute;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dods.model.Named;
import io.dods.model.attribute.misc.ApFix;
import io.dods.model.attribute.misc.ApVar;
import io.dods.model.attribute.misc.UsesKostentabelle;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Richard Gottschalk
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typ", discriminatorType = DiscriminatorType.STRING)
public abstract class Attribut implements Named, Serializable {

    @ApiModelProperty(notes = "will be set by server", readOnly = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    private Attribut subcategoryOf;

    private String wikiUrl;
    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    @Column
    private String name;

    @ApiModelProperty(notes = "will be set by server", readOnly = true)
    @Column(name = "typ", insertable = false, updatable = false)
    private String typ;

    public Attribut() {
        this.typ = getClass().getSimpleName();
    }

    public Attribut(String wikiUrl, String name) {
        this();
        this.wikiUrl = wikiUrl;
        this.name = name;
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

    public long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribut attribut = (Attribut) o;

        return id == attribut.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
