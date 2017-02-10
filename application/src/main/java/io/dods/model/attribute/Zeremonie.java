package io.dods.model.attribute;

import io.dods.model.attribute.misc.*;
import io.dods.model.publikation.Publikation;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue("Zeremonie")
public class Zeremonie extends Attribut implements Dauernd, HatProbe, HatNutzkosten, HatReichweite, UsesKostentabelle, Wirkend, Zielend {

    @ApiModelProperty
    @Embedded
    private Probe probe;

    @ApiModelProperty(required = true)
    @ManyToOne
    private Reichweite reichweite;

    @ApiModelProperty
    @ManyToOne
    private Dauer dauer;

    @ApiModelProperty
    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @ApiModelProperty
    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    @ApiModelProperty(required = true)
    @ManyToMany
    private List<Zielkategorie> zielkategorien;

    @ApiModelProperty
    @ManyToOne
    private Nutzkosten nutzkosten;

    public Zeremonie() {
    }

    public Zeremonie(String wikiUrl,
                     List<Publikation> publikation,
                     Nutzkosten nutzkosten,
                     Probe probe,
                     Reichweite reichweite,
                     Dauer dauer,
                     Wirkungsdauer wirkungsdauer,
                     Kostentabelle kostentabelle,
                     List<Zielkategorie> zielkategorien,
                     String name) {
        super(wikiUrl, name, publikation);
        this.nutzkosten = nutzkosten;
        this.probe = probe;
        this.reichweite = reichweite;
        this.dauer = dauer;
        this.wirkungsdauer = wirkungsdauer;
        this.kostentabelle = kostentabelle;
        this.zielkategorien = zielkategorien;
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
        return dauer;
    }

    @Override
    public Wirkungsdauer getWirkungsdauer() {
        return wirkungsdauer;
    }

    @Override
    public Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

    @Override
    public @NotNull List<Zielkategorie> getZielkategorien() {
        return zielkategorien;
    }

    @Override
    public Nutzkosten getNutzkosten() {
        return nutzkosten;
    }
}
