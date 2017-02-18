package io.dods.model.properties;

import io.dods.model.properties.misc.*;
import io.dods.model.publication.Publication;
import org.jetbrains.annotations.NotNull;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * German: Segen
 *
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(Bless.NAME)
public class Bless extends Property implements ApFix, Kategorisiert, Costs, Ranged, Effecting, Aiming {

    public static final String NAME = "Segen";

    @ManyToMany
    private List<Aspect> aspects = new ArrayList<>();

    @ManyToOne
    private Cost cost;

    @ManyToOne
    private Range range;

    @ManyToOne
    private Duration duration;

    @ManyToMany
    private List<Target> targets;

    public Bless() {
    }

    public Bless(String wikuUrl,
                 List<Publication> publication,
                 List<Aspect> aspects,
                 Cost cost,
                 Range range,
                 Duration duration,
                 List<Target> targets,
                 String name) {
        super(wikuUrl, name, publication);
        this.aspects = aspects;
        this.cost = cost;
        this.range = range;
        this.duration = duration;
        this.targets = targets;
    }

    public Range getRange() {
        return range;
    }

    public Cost getCost() {
        return cost;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public @NotNull List<Target> getTargets() {
        return targets;
    }

    @Override
    public @NotNull List<Aspect> getCategory() {
        return aspects;
    }
}
