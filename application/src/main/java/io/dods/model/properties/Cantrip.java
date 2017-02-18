package io.dods.model.properties;

import io.dods.model.properties.misc.*;
import io.dods.model.publication.Publication;
import org.jetbrains.annotations.NotNull;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * German: Zaubertrick
 *
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(Cantrip.NAME)
public class Cantrip extends Property implements ApFix, Costs, Ranged, Effecting, Aiming {

    public static final String NAME = "Zaubertrick";

    @ManyToOne
    private Range range;

    @ManyToMany
    private List<Target> targets;

    @ManyToOne
    private Duration duration;

    public Cantrip() {
    }

    public Cantrip(String wikuUrl,
                   List<Publication> publication,
                   Range range,
                   List<Target> targets,
                   Duration duration,
                   String name) {
        super(wikuUrl, name, publication);
        this.range = range;
        this.targets = targets;
        this.duration = duration;
    }

    public Range getRange() {
        return range;
    }

    @Override
    public @NotNull List<Target> getTargets() {
        return targets;
    }

    public Duration getDuration() {
        return duration;
    }
}
