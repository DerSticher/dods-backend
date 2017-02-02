package io.dods.model.attribute;

import io.dods.model.attribute.misc.*;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue("Liturgie")
public class Liturgie extends Attribut implements Dauernd, HatProbe, HatNutzkosten, HatReichweite, UsesKostentabelle, Wirkend, Zielend {

    @ApiModelProperty
    @ManyToOne
    private Dauer dauer;

    @ApiModelProperty
    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    @ApiModelProperty(required = true)
    @ManyToOne
    private Reichweite reichweite;

    @ApiModelProperty
    @ManyToOne
    private Nutzkosten nutzkosten;

    @ApiModelProperty
    @Embedded
    private Probe probe;

    @ApiModelProperty
    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @ApiModelProperty(required = true)
    @ManyToMany
    private List<Zielkategorie> zielkategorien;

    public Liturgie() {
    }

    public Liturgie(Dauer dauer,
                    Kostentabelle kostentabelle,
                    Reichweite reichweite,
                    Nutzkosten nutzkosten,
                    Probe probe,
                    Wirkungsdauer wirkungsdauer,
                    List<Zielkategorie> zielkategorien) {
        this.dauer = dauer;
        this.kostentabelle = kostentabelle;
        this.reichweite = reichweite;
        this.nutzkosten = nutzkosten;
        this.probe = probe;
        this.wirkungsdauer = wirkungsdauer;
        this.zielkategorien = zielkategorien;
    }

    public Liturgie(String wikuUrl,
                    Dauer dauer,
                    Kostentabelle kostentabelle,
                    Reichweite reichweite,
                    Nutzkosten nutzkosten,
                    Probe probe,
                    Wirkungsdauer wirkungsdauer,
                    List<Zielkategorie> zielkategorien,
                    String name) {
        super(wikuUrl, name);
        this.dauer = dauer;
        this.kostentabelle = kostentabelle;
        this.reichweite = reichweite;
        this.nutzkosten = nutzkosten;
        this.probe = probe;
        this.wirkungsdauer = wirkungsdauer;
        this.zielkategorien = zielkategorien;
    }

    @Override
    public Dauer getDauer() {
        return dauer;
    }

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
    public Wirkungsdauer getWirkungsdauer() {
        return wirkungsdauer;
    }

    @Override
    public @NotNull List<Zielkategorie> getZielkategorien() {
        return zielkategorien;
    }

    public void setDauer(Dauer dauer) {
        this.dauer = dauer;
    }
}
