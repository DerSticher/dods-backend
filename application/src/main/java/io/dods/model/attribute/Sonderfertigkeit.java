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
@DiscriminatorValue("Sonderfertigkeit")
public class Sonderfertigkeit extends Attribut implements ApFix {

    @ApiModelProperty(required = true)
    @Column
    private int ap;

    @ApiModelProperty(required = true)
    @Column
    private boolean isPassive;

    public Sonderfertigkeit() {
    }

    public Sonderfertigkeit(int ap, boolean isPassive) {
        this.ap = ap;
        this.isPassive = isPassive;
    }

    public Sonderfertigkeit(int ap, boolean isPassive, String name) {
        super("", name);
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
