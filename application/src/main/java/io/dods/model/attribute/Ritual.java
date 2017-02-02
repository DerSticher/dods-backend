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
@DiscriminatorValue("Ritual")
public class Ritual extends Attribut implements Dauernd, HatProbe, HatReichweite, HatNutzkosten, UsesKostentabelle, Wirkend, Zielend {

    @ApiModelProperty(required = true)
    @ManyToOne
    private Nutzkosten nutzkosten;

    @ApiModelProperty
    @ManyToOne
    private Dauer dauer;

    @ApiModelProperty
    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    @ApiModelProperty
    @Embedded
    private Probe probe;

    @ApiModelProperty(required = true)
    @ManyToOne
    private Reichweite reichweite;

    @ApiModelProperty
    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @ApiModelProperty(required = true)
    @ManyToMany
    private List<Zielkategorie> zielkategorien;

    public Ritual() {
    }

    public Ritual(Nutzkosten nutzkosten,
                  Dauer dauer,
                  Kostentabelle kostentabelle,
                  Reichweite reichweite,
                  Probe probe,
                  Wirkungsdauer wirkungsdauer,
                  List<Zielkategorie> zielkategorien) {
        this.nutzkosten = nutzkosten;
        this.dauer = dauer;
        this.kostentabelle = kostentabelle;
        this.reichweite = reichweite;
        this.probe = probe;
        this.wirkungsdauer = wirkungsdauer;
        this.zielkategorien = zielkategorien;
    }

    public Ritual(String wikuUrl,
                  Nutzkosten nutzkosten,
                  Dauer dauer,
                  Kostentabelle kostentabelle,
                  Reichweite reichweite,
                  Probe probe,
                  Wirkungsdauer wirkungsdauer,
                  List<Zielkategorie> zielkategorien,
                  String name) {
        super(wikuUrl, name);
        this.nutzkosten = nutzkosten;
        this.dauer = dauer;
        this.kostentabelle = kostentabelle;
        this.reichweite = reichweite;
        this.probe = probe;
        this.wirkungsdauer = wirkungsdauer;
        this.zielkategorien = zielkategorien;
    }

    @Override
    public Dauer getDauer() {
        return dauer;
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

    @Override
    public Probe getProbe() {
        return probe;
    }
}
