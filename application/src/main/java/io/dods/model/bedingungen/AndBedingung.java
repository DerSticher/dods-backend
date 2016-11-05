package io.dods.model.bedingungen;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.helden.Held;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "bedingung_and")
public class AndBedingung extends Bedingung {

    @JsonProperty("and")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Bedingung> bedingungen = new ArrayList<>();

    public AndBedingung() {
    }

    public AndBedingung(List<Bedingung> bedingungen) {
        this.bedingungen = bedingungen;
    }

    public AndBedingung(Bedingung... bedingungen) {
        this.bedingungen = Arrays.asList(bedingungen);
    }

    @Override
    public boolean isFulfilled(Held held) {
        boolean isFulfilled = true;

        for (Bedingung bedingung : bedingungen) {
            isFulfilled &= bedingung.isFulfilled(held);
        }

        return isFulfilled;
    }

    public List<Bedingung> getAnd() {
        return bedingungen;
    }

    public void setAnd(@NotNull List<Bedingung> bedingungen) {
        this.bedingungen = bedingungen;
    }

    public void add(Bedingung bedingung) {
        bedingungen.add(bedingung);
    }
}
