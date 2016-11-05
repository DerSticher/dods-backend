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
public class Segnung extends Attribut implements ApFix, Nutzkosten, Reichweite, Wirkend, Zielend {

    @ManyToMany
    private List<Aspekt> aspekte = new ArrayList<>();

    @Column
    private int kapKosten;

    @Column
    private int reichweiteInSchritt;

    @OneToOne
    private Wirkung wirkung;

    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @ManyToMany
    private List<Zielkategorie> zielkategorien;

    public Segnung() {
    }

    public Segnung(List<Aspekt> aspekte,
                   int kapKosten,
                   int reichweiteInSchritt,
                   Wirkung wirkung,
                   Wirkungsdauer wirkungsdauer,
                   List<Zielkategorie> zielkategorien) {
        this.aspekte = aspekte;
        this.kapKosten = kapKosten;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.zielkategorien = zielkategorien;
    }

    public Segnung(List<Aspekt> aspekte,
                   int kapKosten,
                   int reichweiteInSchritt,
                   Wirkung wirkung,
                   Wirkungsdauer wirkungsdauer,
                   List<Zielkategorie> zielkategorien,
                   String name) {
        super(name);
        this.aspekte = aspekte;
        this.kapKosten = kapKosten;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.zielkategorien = zielkategorien;
    }

    @Override
    public int getReichweiteInSchritt() {
        return reichweiteInSchritt;
    }

    @Override
    public int getNutzkosten() {
        return kapKosten;
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
