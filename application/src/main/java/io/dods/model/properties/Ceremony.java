package io.dods.model.properties;

import io.dods.model.properties.misc.*;
import io.dods.model.publication.Publication;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

/**
 * German: Zeremonie
 *
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(Ceremony.NAME)
public class Ceremony extends Property implements Casting, Checking, Costs, Ranged, ImprovementCharted, Effecting, Aiming {

    public static final String NAME = "Zeremonie";

    @Embedded
    private Check check;

    @ManyToOne
    private Range range;

    @ManyToOne
    private CastTime castTime;

    @ManyToOne
    private Duration duration;

    @Enumerated(EnumType.STRING)
    private ImprovementChart improvementChart;

    @ManyToMany
    private List<Target> targets;

    @ManyToOne
    private Cost cost;

    public Ceremony() {
    }

    public Ceremony(String wikiUrl,
                    List<Publication> publication,
                    Cost cost,
                    Check check,
                    Range range,
                    CastTime castTime,
                    Duration duration,
                    ImprovementChart improvementChart,
                    List<Target> targets,
                    String name) {
        super(wikiUrl, name, publication);
        this.cost = cost;
        this.check = check;
        this.range = range;
        this.castTime = castTime;
        this.duration = duration;
        this.improvementChart = improvementChart;
        this.targets = targets;
    }

    public Check getCheck() {
        return check;
    }

    public Range getRange() {
        return range;
    }

    public CastTime getCastTime() {
        return castTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public ImprovementChart getImprovementChart() {
        return improvementChart;
    }

    @Override
    public @NotNull List<Target> getTargets() {
        return targets;
    }

    public Cost getCost() {
        return cost;
    }
}
