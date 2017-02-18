package io.dods.model.properties;

import io.dods.model.properties.misc.*;
import io.dods.model.publication.Publication;
import io.dods.services.properties.check.Check;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

/**
 * German: Liturgie
 *
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(LiturgicalChant.NAME)
public class LiturgicalChant extends Property implements Casting, Checking, Costs, Ranged, ImprovementCharted, Effecting, Aiming {

    public static final String NAME = "Liturgie";

    @ManyToOne
    private CastTime castTime;

    @Enumerated(EnumType.STRING)
    private ImprovementChart improvementChart;

    @ManyToOne
    private Range range;

    @ManyToOne
    private Cost cost;

    @Embedded
    private CheckImpl check;

    @ManyToOne
    private Duration duration;

    @ManyToMany
    private List<Target> targets;

    public LiturgicalChant() {
    }

    public LiturgicalChant(String wikuUrl,
                           List<Publication> publication,
                           CastTime castTime,
                           ImprovementChart improvementChart,
                           Range range,
                           Cost cost,
                           CheckImpl check,
                           Duration duration,
                           List<Target> targets,
                           String name) {
        super(wikuUrl, name, publication);
        this.castTime = castTime;
        this.improvementChart = improvementChart;
        this.range = range;
        this.cost = cost;
        this.check = check;
        this.duration = duration;
        this.targets = targets;
    }

    public CastTime getCastTime() {
        return castTime;
    }

    public ImprovementChart getImprovementChart() {
        return improvementChart;
    }

    public Cost getCost() {
        return cost;
    }

    public Check getCheck() {
        return check;
    }

    public Range getRange() {
        return range;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public @NotNull List<Target> getTargets() {
        return targets;
    }

    public void setCastTime(CastTime castTime) {
        this.castTime = castTime;
    }
}
