package io.dods.model.properties;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.interfaces.HasId;
import io.dods.model.Named;
import io.dods.model.properties.misc.ApFix;
import io.dods.model.properties.misc.ApVar;
import io.dods.model.properties.misc.ImprovementCharted;
import io.dods.model.exceptions.HasNoDefaultLevelException;
import io.dods.model.publication.Publication;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonAutoDetect()
public abstract class Property implements HasId<Long>, Named, Serializable {

    @ApiModelProperty(notes = "will be set by server", readOnly = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL)
    private Property subcategoryOf;

    @Column
    private String wikiUrl;

    @Column
    private String name;

    @ApiModelProperty(notes = "will be set by server", readOnly = true)
    @Column(name = "type", insertable = false, updatable = false)
    @JsonProperty("typ")
    private String type;

    @JsonProperty("publikationen")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Publication> publication;

    public Property() {
        this.type = getClass().getSimpleName();
    }

    public Property(String wikiUrl, String name, List<Publication> publications) {
        this();
        this.wikiUrl = wikiUrl;
        this.name = name;
        this.publication = publications;
    }

    public int calculateAp(int level) {
        if (this instanceof ImprovementCharted) {
            return ((ImprovementCharted)this).getAp(level);

        } else if (this instanceof ApVar) {
            return ((ApVar)this).getAp(level);

        } else if (this instanceof ApFix) {
            return ((ApFix)this).getAp();

        }
        throw new IllegalStateException(
                String.format("An attribut has to instanciate %s, %s or %s",
                        ImprovementCharted.class.getSimpleName(),
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

    public Property getSubcategoryOf() {
        return subcategoryOf;
    }

    public void setSubcategoryOf(Property subcategoryOf) {
        this.subcategoryOf = subcategoryOf;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        return id == property.id;
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

    @JsonIgnore
    public List<Publication> getPublications() {
        return publication;
    }

    public void setPublications(List<Publication> publication) {
        this.publication = publication;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", subcategoryOf=" + subcategoryOf +
                ", wikiUrl='" + wikiUrl + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", publication=" + publication +
                '}';
    }

}
