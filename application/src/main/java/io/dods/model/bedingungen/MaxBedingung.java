package io.dods.model.bedingungen;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.attribute.Attribut;
import io.dods.model.helden.CharakterEigenschaft;
import io.dods.model.helden.Held;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "bedingung_max")
public class MaxBedingung extends Bedingung {

    @JsonProperty("attribut")
    @ManyToOne
    private Attribut attribut;

    @JsonProperty("level")
    private int level;

    public MaxBedingung() {
    }

    public MaxBedingung(Attribut attribut, int level) {
        this.attribut = attribut;
        this.level = level;
    }

    @Override
    public boolean isFulfilled(Held held) {
        @Nullable CharakterEigenschaft charakterEigenschaft = held.getEigenschaft(this.attribut);
        if (charakterEigenschaft != null) {
            return charakterEigenschaft.getLevel() <= this.level;
        }
        // not available means it can only be under the given level!
        return true;
    }

    public Attribut getAttribut() {
        return attribut;
    }

    public void setAttribut(Attribut attribut) {
        this.attribut = attribut;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
