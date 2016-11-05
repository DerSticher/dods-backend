package io.dods.model.attribute;

import io.dods.model.attribute.misc.ApFix;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Vorteil extends Attribut implements ApFix {

    @Column
    private int ap;

    public Vorteil() {
    }

    public Vorteil(int ap) {
        this.ap = ap;
    }

    public Vorteil(int ap, String name) {
        super(name);
        this.ap = ap;
    }

    @Override
    public int getAp() {
        return ap;
    }

}
