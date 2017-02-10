package io.dods.model.attribute;

import io.dods.model.attribute.misc.ApFix;
import io.dods.model.attribute.misc.HatReichweite;
import io.dods.model.attribute.misc.Reichweite;
import io.dods.model.publikation.Publikation;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue("Vorteil")
public class Vorteil extends Attribut implements ApFix, HatReichweite {

    @ApiModelProperty(required = true)
    @Column
    private int ap;

    @ApiModelProperty
    @ManyToOne
    private Reichweite reichweite;

    public Vorteil() {
    }

    public Vorteil(String wikuUrl, List<Publikation> publikation, int ap, Reichweite reichweite, String name) {
        super(wikuUrl, name, publikation);
        this.ap = ap;
        this.reichweite = reichweite;
    }

    @Override
    public int getAp() {
        return ap;
    }

    @Override
    public Reichweite getReichweite() {
        return reichweite;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }
}
