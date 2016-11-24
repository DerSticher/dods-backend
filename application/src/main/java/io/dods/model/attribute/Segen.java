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
@DiscriminatorValue("Segen")
public class Segen extends Attribut implements ApFix, Kategorisiert, Nutzkosten, Reichweite, Wirkend, Zielend {

    @ApiModelProperty(required = true)
    @ManyToMany
    private List<Aspekt> aspekte = new ArrayList<>();

    @ApiModelProperty(required = true)
    @Column
    private int kapKosten;

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

    public Segen() {
    }

    public Segen(List<Aspekt> aspekte,
                 int kapKosten,
                 int reichweiteInSchritt,
                 String wirkung,
                 Wirkungsdauer wirkungsdauer,
                 List<Zielkategorie> zielkategorien) {
        this.aspekte = aspekte;
        this.kapKosten = kapKosten;
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.wirkung = wirkung;
        this.wirkungsdauer = wirkungsdauer;
        this.zielkategorien = zielkategorien;
    }

    public Segen(List<Aspekt> aspekte,
                 int kapKosten,
                 int reichweiteInSchritt,
                 String wirkung,
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
    public @NotNull List<Aspekt> getKategorie() {
        return aspekte;
    }
}
