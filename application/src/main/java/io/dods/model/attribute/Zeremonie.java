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
@DiscriminatorValue("Zeremonie")
public class Zeremonie extends Attribut implements Dauernd, HatProbe, Nutzkosten, HatReichweite, UsesKostentabelle, Wirkend, Zielend {

    @ApiModelProperty
    @Embedded
    private Probe probe;

    @ApiModelProperty(required = true)
    @ManyToOne
    private Reichweite reichweite;

    @ApiModelProperty
    @ManyToOne
    private Dauer dauer;

    @ApiModelProperty(required = true)
    @Lob
    @Column
    private String wirkung;

    @ApiModelProperty
    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @ApiModelProperty
    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    @ApiModelProperty(required = true)
    @ManyToMany
    private List<Zielkategorie> zielkategorien;

    @Column
    private int kapKosten;

    public Zeremonie() {
    }

    public Zeremonie(Probe probe,
                     Reichweite reichweite,
                     Dauer dauer,
                     String wirkung,
                     Wirkungsdauer wirkungsdauer,
                     Kostentabelle kostentabelle,
                     List<Zielkategorie> zielkategorien) {
        this.probe = probe;
        this.reichweite = reichweite;
        this.dauer = dauer;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.kostentabelle = kostentabelle;
        this.zielkategorien = zielkategorien;
    }

    public Zeremonie(Probe probe,
                     Reichweite reichweite,
                     Dauer dauer,
                     String wirkung,
                     Wirkungsdauer wirkungsdauer,
                     Kostentabelle kostentabelle,
                     List<Zielkategorie> zielkategorien,
                     String name) {
        super(name);
        this.probe = probe;
        this.reichweite = reichweite;
        this.dauer = dauer;
        this.wirkung = wirkung;
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
    public @NotNull String getWirkung() {
        return wirkung;
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
    public int getNutzkosten() {
        return kapKosten;
    }
}
