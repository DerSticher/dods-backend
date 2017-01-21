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

    @Column
    private boolean isFernkampf;

    public Kampftechnik() {
    }

    public Kampftechnik(String wikiUrl, Eigenschaft leiteigenschaft, Kostentabelle kostentabelle, String name, boolean isFernkampf) {
        super(wikiUrl, name);
        this.leiteigenschaft = leiteigenschaft;
        this.kostentabelle = kostentabelle;
        this.isFernkampf = isFernkampf;
    }

    public Eigenschaft getLeiteigenschaft() {
        return leiteigenschaft;
    }

    public Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

    public boolean isFernkampf() {
        return isFernkampf;
    }
}
