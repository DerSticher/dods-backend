package io.dods.model.attribute;

import io.dods.model.attribute.misc.*;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue("Zauber")
public class Zauber extends Attribut implements Dauernd, HatProbe, HatNutzkosten, HatReichweite, UsesKostentabelle, Wirkend, Zielend {

    @ApiModelProperty
    @ManyToOne
    private Nutzkosten nutzkosten;

    @ApiModelProperty
    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    @ApiModelProperty
    @Embedded
    private Probe probe;

    @ApiModelProperty(required = true)
    @ManyToOne
    private Reichweite reichweite;

    @ApiModelProperty(required = true)
    @Lob
    @Column
    private String wirkung;

    @ApiModelProperty
    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @ApiModelProperty
    @ManyToOne
    private Dauer zauberdauer;

    @ApiModelProperty(required = true)
    @ManyToMany
    private List<Zielkategorie> zielkategorien = new ArrayList<>();

    public Zauber() {
    }

    public Zauber(Nutzkosten nutzkosten,
                  Kostentabelle kostentabelle,
                  Probe probe,
                  Reichweite reichweite,
                  String wirkung,
                  Wirkungsdauer wirkungsdauer,
                  Dauer zauberdauer,
                  List<Zielkategorie> zielkategorien) {
        this.nutzkosten = nutzkosten;
        this.kostentabelle = kostentabelle;
        this.probe = probe;
        this.reichweite = reichweite;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.zauberdauer = zauberdauer;
        this.zielkategorien = zielkategorien;
    }

    public Zauber(String wikuUrl,
                  Nutzkosten nutzkosten,
                  Kostentabelle kostentabelle,
                  Probe probe,
                  Reichweite reichweite,
                  String wirkung,
                  Wirkungsdauer wirkungsdauer,
                  Dauer zauberdauer,
                  List<Zielkategorie> zielkategorien,
                  String name) {
        super(wikuUrl, name);
        this.nutzkosten = nutzkosten;
        this.kostentabelle = kostentabelle;
        this.probe = probe;
        this.reichweite = reichweite;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.zauberdauer = zauberdauer;
        this.zielkategorien = zielkategorien;
    }

    @Override
    public Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

    @Override
    public Nutzkosten getNutzkosten() {
        return nutzkosten;
    }

    @Override
    public Probe getProbe() {
        return probe;
    }

    @Override
    public Reichweite getReichweite() {
        return reichweite;
    }

    @Override
    public Dauer getDauer() {
        return zauberdauer;
    }

    @Override
    public @NotNull String getWirkung() {
        return wirkung;
    }

    @Override
    public Wirkungsdauer getWirkungsdauer() {
        return wirkungsdauer;
    }

    @Override
    public @NotNull List<Zielkategorie> getZielkategorien() {
        return zielkategorien;
    }
}
