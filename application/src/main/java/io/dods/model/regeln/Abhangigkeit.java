package io.dods.model.regeln;

import io.dods.model.helden.Held;
import io.dods.model.bedingungen.Bedingung;
import org.jetbrains.annotations.Contract;

import javax.persistence.*;

/**
 * Basic idea: only if the <code>bedingung</code> is fulfilled the <code>effekt</code> can be used.
 *
 * In other words: if you want to get the <code>effekt</code> you have to be sure the <code>bedingung</code> is fulfilled!
 *
 * @author Richard Gottschalk
 */
@Entity
public class Abhangigkeit {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Regelwerk regelwerk;

    @OneToOne(cascade = CascadeType.ALL)
    private Bedingung bedingung;

    @ManyToOne(cascade = CascadeType.ALL)
    private Effekt effekt;

    public Abhangigkeit() {
    }

    public Abhangigkeit(Effekt effekt, Bedingung bedingung) {
        this(effekt, bedingung, null);
    }

    public Abhangigkeit(Effekt effekt, Bedingung bedingung, Regelwerk regelwerk) {
        this.effekt = effekt;
        this.bedingung = bedingung;
        this.regelwerk = regelwerk;
    }

    public boolean isConditionFulfilled(Held held) {
        return bedingung == null || bedingung.isFulfilled(held);
    }

    public boolean isEffectFulfilled(Held held) {
        return effekt.isFulfilled(held);
    }

    public Status getStatus(Held held) {
        return Status.parseStatus(this, held);
    }

    public Regelwerk getRegelwerk() {
        return regelwerk;
    }

    public void setRegelwerk(Regelwerk regelwerk) {
        this.regelwerk = regelwerk;
    }

    public Bedingung getBedingung() {
        return bedingung;
    }

    public void setBedingung(Bedingung bedingung) {
        this.bedingung = bedingung;
    }

    public Effekt getEffekt() {
        return effekt;
    }

    public void setEffekt(Effekt effekt) {
        this.effekt = effekt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public enum Status {
        NOTHING_FULFILLED(true),
        REQUIREMENTS_MET(true),
        REQUIREMENTS_NOT_MET(false),
        FULFILLED(true);

        private boolean isValid;

        Status(boolean isValid) {
            this.isValid = isValid;
        }

        public boolean isValid() {
            return isValid;
        }

        @Contract("null, _ -> fail; !null, null -> fail")
        public static Status parseStatus(Abhangigkeit abhangigkeit, Held held) {
            if (abhangigkeit == null) throw new IllegalArgumentException("abhangigkeit cannot be null");
            if (held == null) throw new IllegalArgumentException("helden cannot be null");

            boolean conditionFulfilled = abhangigkeit.isConditionFulfilled(held);
            boolean effectFulfilled = abhangigkeit.isEffectFulfilled(held);

            if (conditionFulfilled && effectFulfilled) return FULFILLED;
            if (conditionFulfilled) return REQUIREMENTS_MET;
            if (effectFulfilled) return REQUIREMENTS_NOT_MET;
            return NOTHING_FULFILLED;
        }
    }
}
