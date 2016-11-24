package io.dods.model.attribute;

import io.dods.model.attribute.misc.ApFix;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue("Vorteil")
public class Vorteil extends Attribut implements ApFix {

    @ApiModelProperty(required = true)
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
