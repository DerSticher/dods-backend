package io.dods.model.attribute;

import io.dods.model.attribute.misc.Kostentabelle;
import io.dods.model.attribute.misc.UsesKostentabelle;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue("Kampftechnik")
public class Kampftechnik extends Attribut implements UsesKostentabelle {

    @ApiModelProperty(required = true)
    @OneToOne
    private Eigenschaft leiteigenschaft;

    @ApiModelProperty(required = true)
    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    public Kampftechnik(Eigenschaft leiteigenschaft, Kostentabelle kostentabelle) {
        this.leiteigenschaft = leiteigenschaft;
        this.kostentabelle = kostentabelle;
    }

    public Kampftechnik(Eigenschaft leiteigenschaft, Kostentabelle kostentabelle, String name) {
        super(name);
        this.leiteigenschaft = leiteigenschaft;
        this.kostentabelle = kostentabelle;
    }

    public Eigenschaft getLeiteigenschaft() {
        return leiteigenschaft;
    }

    public Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

}
