package io.dods.model.attribute;

import io.dods.model.attribute.misc.Kostentabelle;
import io.dods.model.attribute.misc.UsesKostentabelle;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(Eigenschaft.NAME)
public class Eigenschaft extends Attribut implements UsesKostentabelle {

    public static final String NAME = "Eigenschaft";

    @ApiModelProperty(hidden = true)
    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle = Kostentabelle.E;

    public Eigenschaft() {
    }

    public Eigenschaft(Kostentabelle kostentabelle) {
        this.kostentabelle = kostentabelle;
    }

    public Eigenschaft(Kostentabelle kostentabelle, String name) {
        super("", name);
        this.kostentabelle = kostentabelle;
    }

    public Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

    @Override
    public int getAp(int level) {
        return UsesKostentabelle.super.getAp(level) - UsesKostentabelle.super.getAp(getDefaultLevel());
    }

    @Override
    public int getDefaultLevel() {
        return 8;
    }
}
