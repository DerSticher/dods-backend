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
@Table(name = "bedingung_vorhanden")
public class VorhandenBedingung extends Bedingung {

    @JsonProperty("attribut")
    @ManyToOne
    private Attribut attribut;

    public VorhandenBedingung() {
    }

    public VorhandenBedingung(Attribut attribut) {
        this.attribut = attribut;
    }

    @Override
    public boolean isFulfilled(Held held) {
        @Nullable CharakterEigenschaft eigenschaft = held.getEigenschaft(attribut);
        if (eigenschaft == null) return false;
        return eigenschaft.isActivated();
    }

}
