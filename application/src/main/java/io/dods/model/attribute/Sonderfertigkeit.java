package io.dods.model.attribute;

import io.dods.model.attribute.misc.ApFix;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Sonderfertigkeit extends Attribut implements ApFix {

    @Column
    private int ap;

    @Column
    private boolean isPassive;

    public Sonderfertigkeit() {
    }

    public Sonderfertigkeit(int ap, boolean isPassive) {
        this.ap = ap;
        this.isPassive = isPassive;
    }

    public Sonderfertigkeit(int ap, boolean isPassive, String name) {
        super(name);
        this.ap = ap;
        this.isPassive = isPassive;
    }

    @Override
    public int getAp() {
        return ap;
    }

    public boolean isPassive() {
        return isPassive;
    }
}
