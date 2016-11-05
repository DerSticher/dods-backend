package io.dods.model.attribute;

import io.dods.model.attribute.misc.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Ritual extends Attribut implements Dauernd, HatProbe, Reichweite, Nutzkosten, UsesKostentabelle, Wirkend, Zielend {

    @Column
    private int aspKosten;

    @ManyToOne
    private Dauer dauer;

    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    @ManyToOne
    private Probe probe;

    @Column
    private int reichweiteInSchritt;

    @OneToOne
    private Wirkung wirkung;

    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @ManyToMany
    private List<Zielkategorie> zielkategorien;

    public Ritual() {
    }

    public Ritual(int aspKosten,
                  Dauer dauer,
                  Kostentabelle kostentabelle,
                  int reichweiteInSchritt,
                  Wirkung wirkung,
                  Wirkungsdauer wirkungsdauer) {
        this.aspKosten = aspKosten;
        this.dauer = dauer;
        this.kostentabelle = kostentabelle;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
    }

    public Ritual(int aspKosten,
                  Dauer dauer,
                  Kostentabelle kostentabelle,
                  int reichweiteInSchritt,
                  Wirkung wirkung,
                  Wirkungsdauer wirkungsdauer,
                  String name) {
        super(name);
        this.aspKosten = aspKosten;
        this.dauer = dauer;
        this.kostentabelle = kostentabelle;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
    }

    @Override
    public @NotNull Dauer getDauer() {
        return dauer;
    }

    @Override
    public @NotNull Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

    @Override
    public int getNutzkosten() {
        return aspKosten;
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
    public @NotNull Wirkungsdauer getWirkungsdauer() {
        return wirkungsdauer;
    }

    @Override
    public @NotNull List<Zielkategorie> getZielkategorien() {
        return zielkategorien;
    }

    @Override
    public @NotNull Probe getProbe() {
        return probe;
    }
}
