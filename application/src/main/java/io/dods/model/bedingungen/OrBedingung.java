package io.dods.model.bedingungen;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.helden.Held;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "bedingung_or")
public class OrBedingung extends Bedingung {

    @JsonProperty("or")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Bedingung> bedingungen = new ArrayList<>();

    public OrBedingung() {
    }

    public OrBedingung(List<Bedingung> bedingungen) {
        this.bedingungen = bedingungen;
    }

    public OrBedingung(Bedingung... bedingungen) {
        this.bedingungen = Arrays.asList(bedingungen);
    }

    @Override
    public boolean isFulfilled(Held held) {
        boolean isFulfilled = false;

        for (Bedingung bedingung : bedingungen) {
            isFulfilled |= bedingung.isFulfilled(held);
        }

        return isFulfilled;
    }

    public List<Bedingung> getOr() {
        return bedingungen;
    }

    public void setOr(List<Bedingung> bedingungen) {
        this.bedingungen = bedingungen;
    }

    public void add(Bedingung bedingung) {
        this.bedingungen.add(bedingung);
    }
}
