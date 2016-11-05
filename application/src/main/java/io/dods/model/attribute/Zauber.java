package io.dods.model.attribute;

import io.dods.model.attribute.misc.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Zauber extends Attribut implements Dauernd, HatProbe, Nutzkosten, Reichweite, UsesKostentabelle, Wirkend, Zielend {

    @Column
    private int aspKosten;

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

    @ManyToOne
    private Dauer zauberdauer;

    @ManyToMany
    private List<Zielkategorie> zielkategorien = new ArrayList<>();

    public Zauber() {
    }

    public Zauber(int aspKosten,
                  Kostentabelle kostentabelle,
                  Probe probe,
                  int reichweiteInSchritt,
                  Wirkung wirkung,
                  Wirkungsdauer wirkungsdauer,
                  Dauer zauberdauer,
                  List<Zielkategorie> zielkategorien) {
        this.aspKosten = aspKosten;
        this.kostentabelle = kostentabelle;
        this.probe = probe;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.zauberdauer = zauberdauer;
        this.zielkategorien = zielkategorien;
    }

    public Zauber(int aspKosten,
                  Kostentabelle kostentabelle,
                  Probe probe,
                  int reichweiteInSchritt,
                  Wirkung wirkung,
                  Wirkungsdauer wirkungsdauer,
                  Dauer zauberdauer,
                  List<Zielkategorie> zielkategorien,
                  String name) {
        super(name);
        this.aspKosten = aspKosten;
        this.kostentabelle = kostentabelle;
        this.probe = probe;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.zauberdauer = zauberdauer;
        this.zielkategorien = zielkategorien;
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
    public @NotNull Probe getProbe() {
        return probe;
    }

    @Override
    public int getReichweiteInSchritt() {
        return reichweiteInSchritt;
    }

    @Override
    public @NotNull Dauer getDauer() {
        return zauberdauer;
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
}
