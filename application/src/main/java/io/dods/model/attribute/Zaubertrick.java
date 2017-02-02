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

    public Zaubertrick() {
    }

    public Zaubertrick(Reichweite reichweite,
                       List<Zielkategorie> zielkategorien,
                       Wirkungsdauer wirkungsdauer) {
        this.reichweite = reichweite;
        this.zielkategorien = zielkategorien;
        this.wirkungsdauer = wirkungsdauer;
    }

    public Zaubertrick(String wikuUrl,
                       Reichweite reichweite,
                       List<Zielkategorie> zielkategorien,
                       Wirkungsdauer wirkungsdauer,
                       String name) {
        super(wikuUrl, name);
        this.reichweite = reichweite;
        this.zielkategorien = zielkategorien;
        this.wirkungsdauer = wirkungsdauer;
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
    public Wirkungsdauer getWirkungsdauer() {
        return wirkungsdauer;
    }
}
