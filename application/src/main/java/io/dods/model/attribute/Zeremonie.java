package io.dods.model.attribute;

import io.dods.model.attribute.misc.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Zeremonie extends Attribut implements Dauernd, HatProbe, Nutzkosten, Reichweite, UsesKostentabelle, Wirkend, Zielend {

    @ManyToOne
    private Probe probe;

    @Column
    private int reichweiteInSchritt;

    @ManyToOne
    private Dauer dauer;

    @OneToOne
    private Wirkung wirkung;

    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    @ManyToMany
    private List<Zielkategorie> zielkategorien;

    @Column
    private int kapKosten;

    public Zeremonie() {
    }

    public Zeremonie(Probe probe,
                     int reichweiteInSchritt,
                     Dauer dauer,
                     Wirkung wirkung,
                     Wirkungsdauer wirkungsdauer,
                     Kostentabelle kostentabelle,
                     List<Zielkategorie> zielkategorien) {
        this.probe = probe;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.dauer = dauer;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.kostentabelle = kostentabelle;
        this.zielkategorien = zielkategorien;
    }

    public Zeremonie(Probe probe,
                     int reichweiteInSchritt,
                     Dauer dauer,
                     Wirkung wirkung,
                     Wirkungsdauer wirkungsdauer,
                     Kostentabelle kostentabelle,
                     List<Zielkategorie> zielkategorien,
                     String name) {
        super(name);
        this.probe = probe;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.dauer = dauer;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.kostentabelle = kostentabelle;
        this.zielkategorien = zielkategorien;
    }

    @Override
    public @NotNull Probe getProbe() {
        return probe;
    }

    @Override
    public int getReichweiteInSchritt() {
        return reichweiteInSchritt;
    }

    @Override
    public Dauer getDauer() {
        return dauer;
    }

    @Override
    public Wirkung getWirkung() {
        return wirkung;
    }

    @Override
    public Wirkungsdauer getWirkungsdauer() {
        return wirkungsdauer;
    }

    @Override
    public @NotNull Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

    @Override
    public @NotNull List<Zielkategorie> getZielkategorien() {
        return zielkategorien;
    }

    @Override
    public int getNutzkosten() {
        return kapKosten;
    }
}
