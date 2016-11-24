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
public class Zaubertrick extends Attribut implements ApFix, Nutzkosten, Reichweite, Wirkend, Zielend {

    @ApiModelProperty(required = true)
    @Column
    private int reichweiteInSchritt;

    @ApiModelProperty(required = true)
    @ManyToMany
    private List<Zielkategorie> zielkategorien;

    @ApiModelProperty(required = true)
    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @ApiModelProperty(required = true)
    @Lob
    @Column
    private String wirkung;

    public Zaubertrick() {
    }

    public Zaubertrick(int reichweiteInSchritt,
                       List<Zielkategorie> zielkategorien,
                       Wirkungsdauer wirkungsdauer,
                       String wirkung) {
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.zielkategorien = zielkategorien;
        this.wirkungsdauer = wirkungsdauer;
        this.wirkung = wirkung;
    }

    public Zaubertrick(int reichweiteInSchritt,
                       List<Zielkategorie> zielkategorien,
                       Wirkungsdauer wirkungsdauer,
                       String wirkung,
                       String name) {
        super(name);
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.zielkategorien = zielkategorien;
        this.wirkungsdauer = wirkungsdauer;
        this.wirkung = wirkung;
    }

    @Override
    public int getReichweiteInSchritt() {
        return reichweiteInSchritt;
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
    public @NotNull Wirkungsdauer getWirkungsdauer() {
        return wirkungsdauer;
    }
}
