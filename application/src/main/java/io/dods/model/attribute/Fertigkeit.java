package io.dods.model.attribute;

import io.dods.model.attribute.misc.HatProbe;
import io.dods.model.attribute.misc.Kostentabelle;
import io.dods.model.attribute.misc.Probe;
import io.dods.model.attribute.misc.UsesKostentabelle;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue("Fertigkeit")
public class Fertigkeit extends Attribut implements HatProbe, UsesKostentabelle {

    public enum Gruppe {
        KOERPER,
        GESELLSCHAFT,
        NATUR,
        WISSEN,
        HANDWERK;

        public static Gruppe findOrThrow(String name) throws IllegalArgumentException {
            try {
                return valueOf(name);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(String.format("Cannot parse %s to a Gruppe", name), e);
            }
        }
    }

    @ApiModelProperty(required = true)
    @Enumerated(EnumType.STRING)
    private Kostentabelle kostentabelle;

    @ApiModelProperty
    @Embedded
    private Probe probe;

    @ApiModelProperty(required = true)
    @Enumerated(EnumType.STRING)
    private Gruppe gruppe;

    public Fertigkeit() {
    }

    public Fertigkeit(Kostentabelle kostentabelle, Probe probe, Gruppe gruppe) {
        this.kostentabelle = kostentabelle;
        this.probe = probe;
        this.gruppe = gruppe;
    }

    public Fertigkeit(Kostentabelle kostentabelle, Probe probe, Gruppe gruppe, String name) {
        super(name);
        this.kostentabelle = kostentabelle;
        this.probe = probe;
        this.gruppe = gruppe;
    }

    public Gruppe getGruppe() {
        return gruppe;
    }

    public Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

    @Override
    public Probe getProbe() {
        return probe;
    }

}
