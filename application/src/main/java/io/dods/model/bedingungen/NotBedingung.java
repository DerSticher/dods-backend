package io.dods.model.bedingungen;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.helden.Held;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "bedingung_not")
public class NotBedingung extends Bedingung {

    @JsonProperty("not")
    @ManyToOne(cascade = CascadeType.ALL)
    private Bedingung not;

    public NotBedingung() {
    }

    public NotBedingung(Bedingung not) {
        this.not = not;
    }

    @Override
    public boolean isFulfilled(Held held) {
        return !not.isFulfilled(held);
    }

    public Bedingung getNot() {
        return not;
    }

    public void setNot(Bedingung bedingung) {
        this.not = bedingung;
    }
}
