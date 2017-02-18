package io.dods.model.heroes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.properties.Property;
import io.dods.model.properties.misc.ApFix;
import io.dods.model.properties.misc.ApVar;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
public class HeroProperty {

    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @ManyToOne
    @JsonProperty("attribut")
    private Property property;

    @Column
    @JsonProperty("level")
    private int level;

    public HeroProperty() {
        // default constructor for ORM
    }

    public HeroProperty(@NotNull Property property, int level) {
        this.setProperty(property);
        this.setLevel(level);
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(@NotNull Property property) {
        this.property = property;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 0) throw new IllegalArgumentException("level cannot be negative!");
        this.level = level;
    }

    public boolean isAttribut(Property property) {
        if (this.property != null) return this.property.equals(property);
        return false;
    }

    public int getAp() {
        if (property instanceof ApFix) {
            return ((ApFix) property).getAp();

        } else if (property instanceof ApVar) {
            return ((ApVar) property).getAp(level);

        }
        throw new IllegalStateException(String.format("Class %s is not an instance of %s or %s",
                property.getClass().getSimpleName(),
                ApFix.class.getSimpleName(),
                ApVar.class.getSimpleName()));
    }

}
