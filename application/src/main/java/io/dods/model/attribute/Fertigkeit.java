package io.dods.model.attribute;

import io.dods.model.attribute.misc.HatProbe;
import io.dods.model.attribute.misc.Kostentabelle;
import io.dods.model.attribute.misc.Probe;
import io.dods.model.attribute.misc.UsesKostentabelle;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Fertigkeit extends Attribut implements HatProbe, UsesKostentabelle {

    public enum Gruppe {
        KOERPER,
        GESELLSCHAFT,
        NATUR,
        WISSEN,
        HANDWERK
    }

    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    @ManyToOne
    private Probe probe;

    @Enumerated(EnumType.STRING)
    private Gruppe gruppe;

    public Fertigkeit() {
    }

    public Fertigkeit(Kostentabelle kostentabelle, Probe probe, Gruppe gruppe) {
        this.kostentabelle = kostentabelle;
        this.probe = probe;
        this.gruppe = gruppe;
    }

    public Fertigkeit(Kostentabelle kostentabelle, Probe probe, Gruppe gruppe, String name) {
        super(name);
        this.kostentabelle = kostentabelle;
        this.probe = probe;
        this.gruppe = gruppe;
    }

    public Gruppe getGruppe() {
        return gruppe;
    }

    public @NotNull Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

    @Override
    public Probe getProbe() {
        return probe;
    }

}
