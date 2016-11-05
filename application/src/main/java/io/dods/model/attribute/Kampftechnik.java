package io.dods.model.attribute;

import io.dods.model.attribute.misc.Kostentabelle;
import io.dods.model.attribute.misc.UsesKostentabelle;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Kampftechnik extends Attribut implements UsesKostentabelle {

    @OneToOne
    private Eigenschaft leiteigenschaft;

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

    public @NotNull Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

}
