package io.dods.model.attribute;

import io.dods.model.attribute.misc.Kostentabelle;
import io.dods.model.attribute.misc.UsesKostentabelle;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.NotNull;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue("Eigenschaft")
public class Eigenschaft extends Attribut implements UsesKostentabelle {

    @ApiModelProperty(hidden = true)
    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle = Kostentabelle.E;

    public Eigenschaft() {
    }

    public Eigenschaft(Kostentabelle kostentabelle) {
        this.kostentabelle = kostentabelle;
    }

    public Eigenschaft(Kostentabelle kostentabelle, String name) {
        super(name);
        this.kostentabelle = kostentabelle;
    }

    public @NotNull Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

}
