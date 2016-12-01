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
@DiscriminatorValue("Zaubertrick")
public class Zaubertrick extends Attribut implements ApFix, HatNutzkosten, HatReichweite, Wirkend, Zielend {

    @ApiModelProperty(required = true)
    @ManyToOne
    private Reichweite reichweite;

    @ApiModelProperty(required = true)
    @ManyToMany
    private List<Zielkategorie> zielkategorien;

    @ApiModelProperty
    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @ApiModelProperty(required = true)
    @Lob
    @Column
    private String wirkung;

    public Zaubertrick() {
    }

    public Zaubertrick(Reichweite reichweite,
                       List<Zielkategorie> zielkategorien,
                       Wirkungsdauer wirkungsdauer,
                       String wirkung) {
        this.reichweite = reichweite;
        this.zielkategorien = zielkategorien;
        this.wirkungsdauer = wirkungsdauer;
        this.wirkung = wirkung;
    }

    public Zaubertrick(Reichweite reichweite,
                       List<Zielkategorie> zielkategorien,
                       Wirkungsdauer wirkungsdauer,
                       String wirkung,
                       String name) {
        super(name);
        this.reichweite = reichweite;
        this.zielkategorien = zielkategorien;
        this.wirkungsdauer = wirkungsdauer;
        this.wirkung = wirkung;
    }

    @Override
    public Reichweite getReichweite() {
        return reichweite;
    }

    @Override
    public @NotNull List<Zielkategorie> getZielkategorien() {
        return zielkategorien;
    }

    @Override
    public @NotNull String getWirkung() {
        return wirkung;
    }

    @Override
    public Wirkungsdauer getWirkungsdauer() {
        return wirkungsdauer;
    }
}
