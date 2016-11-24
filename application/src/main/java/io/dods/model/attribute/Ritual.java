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
public class Ritual extends Attribut implements Dauernd, HatProbe, Reichweite, Nutzkosten, UsesKostentabelle, Wirkend, Zielend {

    @ApiModelProperty(required = true)
    @Column
    private int aspKosten;

    @ApiModelProperty(required = true)
    @ManyToOne
    private Dauer dauer;

    @ApiModelProperty(required = true)
    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    @ApiModelProperty(required = true)
    @ManyToOne
    private Probe probe;

    @ApiModelProperty(required = true)
    @Column
    private int reichweiteInSchritt;

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

    public Ritual() {
    }

    public Ritual(int aspKosten,
                  Dauer dauer,
                  Kostentabelle kostentabelle,
                  int reichweiteInSchritt,
                  Probe probe,
                  String wirkung,
                  Wirkungsdauer wirkungsdauer,
                  List<Zielkategorie> zielkategorien) {
        this.aspKosten = aspKosten;
        this.dauer = dauer;
        this.kostentabelle = kostentabelle;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.probe = probe;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.zielkategorien = zielkategorien;
    }

    public Ritual(int aspKosten,
                  Dauer dauer,
                  Kostentabelle kostentabelle,
                  int reichweiteInSchritt,
                  Probe probe,
                  String wirkung,
                  Wirkungsdauer wirkungsdauer,
                  List<Zielkategorie> zielkategorien,
                  String name) {
        super(name);
        this.aspKosten = aspKosten;
        this.dauer = dauer;
        this.kostentabelle = kostentabelle;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.probe = probe;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.zielkategorien = zielkategorien;
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
    public @NotNull String getWirkung() {
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
