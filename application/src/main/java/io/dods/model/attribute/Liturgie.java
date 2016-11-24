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
public class Liturgie extends Attribut implements Dauernd, HatProbe, Nutzkosten, Reichweite, UsesKostentabelle, Wirkend, Zielend {

    @ApiModelProperty(required = true)
    @ManyToOne
    private Dauer dauer;

    @ApiModelProperty(required = true)
    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    @ApiModelProperty(required = true)
    @Column
    private int reichweiteInSchritt;

    @ApiModelProperty(required = true)
    @Column
    private int kapKosten;

    @ApiModelProperty(required = true)
    @Embedded
    private Probe probe;

    @ApiModelProperty(required = true)
    @Lob
    @Column
    private String wirkung;

    @ApiModelProperty(required = true)
    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @ApiModelProperty(required = true)
    @ManyToMany
    private List<Zielkategorie> zielkategorien;

    public Liturgie() {
    }

    public Liturgie(Dauer dauer,
                    Kostentabelle kostentabelle,
                    int reichweiteInSchritt,
                    int kapKosten,
                    Probe probe,
                    String wirkung,
                    Wirkungsdauer wirkungsdauer,
                    List<Zielkategorie> zielkategorien) {
        this.dauer = dauer;
        this.kostentabelle = kostentabelle;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.kapKosten = kapKosten;
        this.probe = probe;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.zielkategorien = zielkategorien;
    }

    public Liturgie(Dauer dauer,
                    Kostentabelle kostentabelle,
                    int reichweiteInSchritt,
                    int kapKosten,
                    Probe probe,
                    String wirkung,
                    Wirkungsdauer wirkungsdauer,
                    List<Zielkategorie> zielkategorien,
                    String name) {
        super(name);
        this.dauer = dauer;
        this.kostentabelle = kostentabelle;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.kapKosten = kapKosten;
        this.probe = probe;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.zielkategorien = zielkategorien;
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

    public void setDauer(Dauer dauer) {
        this.dauer = dauer;
    }
}
