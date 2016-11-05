package io.dods.model.attribute;

import io.dods.model.attribute.misc.Kostentabelle;
import io.dods.model.attribute.misc.UsesKostentabelle;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Eigenschaft extends Attribut implements UsesKostentabelle {

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
