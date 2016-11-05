package io.dods.model.attribute;

import io.dods.model.attribute.misc.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Zaubertrick extends Attribut implements ApFix, Nutzkosten, Reichweite, Wirkend, Zielend {

    @Column
    private int reichweiteInSchritt;

    @ManyToMany
    private List<Zielkategorie> zielkategorien;

    @ManyToOne
    private Wirkungsdauer wirkungsdauer;

    @OneToOne
    private Wirkung wirkung;

    public Zaubertrick() {
    }

    public Zaubertrick(int reichweiteInSchritt,
                       List<Zielkategorie> zielkategorien,
                       Wirkungsdauer wirkungsdauer,
                       Wirkung wirkung) {
        this.reichweiteInSchritt = reichweiteInSchritt;
        this.zielkategorien = zielkategorien;
        this.wirkungsdauer = wirkungsdauer;
        this.wirkung = wirkung;
    }

    public Zaubertrick(int reichweiteInSchritt,
                       List<Zielkategorie> zielkategorien,
                       Wirkungsdauer wirkungsdauer,
                       Wirkung wirkung,
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
    public Wirkung getWirkung() {
        return wirkung;
    }

    @Override
    public @NotNull Wirkungsdauer getWirkungsdauer() {
        return wirkungsdauer;
    }
}
