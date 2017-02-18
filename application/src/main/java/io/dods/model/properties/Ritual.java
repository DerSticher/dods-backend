package io.dods.model.properties;

import io.dods.model.properties.misc.*;
import io.dods.model.publication.Publication;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

/**
 * German. Ritual:
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(Ritual.NAME)
public class Ritual extends Property implements Casting, Checking, Ranged, Costs, ImprovementCharted, Effecting, Aiming {

    public static final String NAME = "Ritual";

    @ManyToOne
    private Cost cost;

    @ManyToOne
    private CastTime castTime;

    @Enumerated(EnumType.STRING)
    private ImprovementChart improvementChart;

    @Embedded
    private Check check;

    @ManyToOne
    private Range range;

    @ManyToOne
    private Duration duration;

    @ManyToMany
    private List<Target> targets;

    public Ritual() {
    }

    public Ritual(String wikuUrl,
                  List<Publication> publication,
                  Cost cost,
                  CastTime castTime,
                  ImprovementChart improvementChart,
                  Range range,
                  Check check,
                  Duration duration,
                  List<Target> targets,
                  String name) {
        super(wikuUrl, name, publication);
        this.cost = cost;
        this.castTime = castTime;
        this.improvementChart = improvementChart;
        this.range = range;
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

    public Check getCheck() {
        return check;
    }
}
