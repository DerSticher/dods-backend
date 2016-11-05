package io.dods.model.attribute;

import io.dods.model.attribute.misc.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Liturgie extends Attribut implements Dauernd, HatProbe, Nutzkosten, Reichweite, UsesKostentabelle, Wirkend, Zielend {

    @ManyToOne
    private Dauer dauer;

    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    @Column
    private int reichweiteInSchritt;

    @Column
    private int kapKosten;

    @ManyToOne
    private Probe probe;

    @OneToOne
    private Wirkung wirkung;

    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @ManyToMany
    private List<Zielkategorie> zielkategorie;

    public Liturgie(Dauer dauer,
                    Kostentabelle kostentabelle,
                    int reichweiteInSchritt,
                    int kapKosten,
                    Probe probe,
                    Wirkung wirkung,
                    Wirkungsdauer wirkungsdauer,
                    List<Zielkategorie> zielkategorie) {
        this.dauer = dauer;
        this.kostentabelle = kostentabelle;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.kapKosten = kapKosten;
        this.probe = probe;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.zielkategorie = zielkategorie;
    }

    public Liturgie(Dauer dauer,
                    Kostentabelle kostentabelle,
                    int reichweiteInSchritt,
                    int kapKosten,
                    Probe probe,
                    Wirkung wirkung,
                    Wirkungsdauer wirkungsdauer,
                    List<Zielkategorie> zielkategorie,
                    String name) {
        super(name);
        this.dauer = dauer;
        this.kostentabelle = kostentabelle;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.kapKosten = kapKosten;
        this.probe = probe;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.zielkategorie = zielkategorie;
    }

    @Override
    public Dauer getDauer() {
        return dauer;
    }

    public @NotNull Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

    @Override
    public int getNutzkosten() {
        return kapKosten;
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
    public Wirkung getWirkung() {
        return wirkung;
    }

    @Override
    public Wirkungsdauer getWirkungsdauer() {
        return wirkungsdauer;
    }

    @Override
    public @NotNull List<Zielkategorie> getZielkategorien() {
        return zielkategorie;
    }
}
