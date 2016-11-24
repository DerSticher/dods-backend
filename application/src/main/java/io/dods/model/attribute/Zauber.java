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
public class Zauber extends Attribut implements Dauernd, HatProbe, Nutzkosten, Reichweite, UsesKostentabelle, Wirkend, Zielend {

    @ApiModelProperty(required = true)
    @Column
    private int aspKosten;

    @ApiModelProperty(required = true)
    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    @ApiModelProperty(required = true)
    @Embedded
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
    @ManyToOne
    private Dauer zauberdauer;

    @ApiModelProperty(required = true)
    @ManyToMany
    private List<Zielkategorie> zielkategorien = new ArrayList<>();

    public Zauber() {
    }

    public Zauber(int aspKosten,
                  Kostentabelle kostentabelle,
                  Probe probe,
                  int reichweiteInSchritt,
                  String wirkung,
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
                  String wirkung,
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
}
