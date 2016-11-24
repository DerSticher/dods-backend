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
public class Zeremonie extends Attribut implements Dauernd, HatProbe, Nutzkosten, Reichweite, UsesKostentabelle, Wirkend, Zielend {

    @ApiModelProperty(required = true)
    @ManyToOne
    private Probe probe;

    @ApiModelProperty(required = true)
    @Column
    private int reichweiteInSchritt;

    @ApiModelProperty(required = true)
    @ManyToOne
    private Dauer dauer;

    @ApiModelProperty(required = true)
    @Lob
    @Column
    private String wirkung;

    @ApiModelProperty(required = true)
    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @ApiModelProperty(required = true)
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
                     int reichweiteInSchritt,
                     Dauer dauer,
                     String wirkung,
                     Wirkungsdauer wirkungsdauer,
                     Kostentabelle kostentabelle,
                     List<Zielkategorie> zielkategorien) {
        this.probe = probe;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.dauer = dauer;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.kostentabelle = kostentabelle;
        this.zielkategorien = zielkategorien;
    }

    public Zeremonie(Probe probe,
                     int reichweiteInSchritt,
                     Dauer dauer,
                     String wirkung,
                     Wirkungsdauer wirkungsdauer,
                     Kostentabelle kostentabelle,
                     List<Zielkategorie> zielkategorien,
                     String name) {
        super(name);
        this.probe = probe;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.dauer = dauer;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.kostentabelle = kostentabelle;
        this.zielkategorien = zielkategorien;
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
    public @NotNull Kostentabelle getKostentabelle() {
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
