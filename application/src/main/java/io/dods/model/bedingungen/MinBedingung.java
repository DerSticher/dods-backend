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
@Table(name = "bedingung_min")
public class MinBedingung extends Bedingung {

    @JsonProperty("attribut")
    @ManyToOne
    private Attribut attribut;

    @JsonProperty("level")
    private int level;

    public MinBedingung() {
    }

    public MinBedingung(Attribut attribut, int level) {
        this.attribut = attribut;
        this.level = level;
    }

    @Override
    public boolean isFulfilled(Held held) {
        @Nullable CharakterEigenschaft charakterEigenschaft = held.getEigenschaft(this.attribut);
        if (charakterEigenschaft != null) {
            return charakterEigenschaft.getLevel() >= this.level;
        }
        return false;
    }

    public Attribut getAttribut() {
        return attribut;
    }

    public int getLevel() {
        return level;
    }
}
