package io.dods.model.attribute;

import io.dods.model.attribute.misc.ApFix;
import io.dods.model.attribute.misc.HatReichweite;
import io.dods.model.attribute.misc.Reichweite;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

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
    @Lob
    @Column
    private String wirkung;

    @ApiModelProperty
    @ManyToOne
    private Reichweite reichweite;

    public Vorteil() {
    }

    public Vorteil(int ap, String wirkung, Reichweite reichweite) {
        this.ap = ap;
        this.wirkung = wirkung;
        this.reichweite = reichweite;
    }

    public Vorteil(int ap, String wirkung, Reichweite reichweite, String name) {
        super(name);
        this.ap = ap;
        this.wirkung = wirkung;
        this.reichweite = reichweite;
    }

    @Override
    public int getAp() {
        return ap;
    }

    public String getWirkung() {
        return wirkung;
    }

    @Override
    public Reichweite getReichweite() {
        return reichweite;
    }
}
