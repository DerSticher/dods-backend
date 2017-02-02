package io.dods.model.attribute;

import io.dods.model.attribute.misc.*;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue("Sonderfertigkeit")
public class Sonderfertigkeit extends Attribut implements ApFix, Dauernd, HatProbe, HatNutzkosten, HatReichweite, UsesKostentabelle, Wirkend {

    public enum Gruppe {
        KAMPF,
        KARMAL,
        MAGISCH,
        PROFAN
    }

    @ApiModelProperty(required = true)
    @Column
    private int ap;

    @ApiModelProperty(required = true)
    @Enumerated(EnumType.STRING)
    private Gruppe gruppe;

    @ApiModelProperty
    @ManyToOne
    private Dauer dauer;

    @ApiModelProperty
    @ManyToOne
    private Nutzkosten nutzkosten;

    @ApiModelProperty
    @Embedded
    private Probe probe;

    @ApiModelProperty(required = true)
    @ManyToOne
    private Reichweite reichweite;

    @ApiModelProperty
    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @ApiModelProperty
    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    public Sonderfertigkeit() {
    }

    public Sonderfertigkeit(String wikiUrl,
                            int ap,
                            Gruppe gruppe,
                            Dauer dauer,
                            Nutzkosten nutzkosten,
                            Probe probe,
                            Reichweite reichweite,
                            Wirkungsdauer wirkungsdauer,
                            Kostentabelle kostentabelle,
                            String name) {
        super(wikiUrl, name);
        this.ap = ap;
        this.gruppe = gruppe;
        this.dauer = dauer;
        this.nutzkosten = nutzkosten;
        this.probe = probe;
        this.reichweite = reichweite;
        this.wirkungsdauer = wirkungsdauer;
        this.kostentabelle = kostentabelle;
    }

    @Override
    public int getAp() {
        return ap;
    }

    @Override
    public Dauer getDauer() {
        return dauer;
    }

    @Override
    public Reichweite getReichweite() {
        return reichweite;
    }

    @Override
    public @Nullable Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

    @Override
    public @Nullable Probe getProbe() {
        return probe;
    }

    @Override
    public @Nullable Wirkungsdauer getWirkungsdauer() {
        return wirkungsdauer;
    }

    @Override
    public @Nullable Nutzkosten getNutzkosten() {
        return nutzkosten;
    }

    public Gruppe getGruppe() {
        return gruppe;
    }
}
