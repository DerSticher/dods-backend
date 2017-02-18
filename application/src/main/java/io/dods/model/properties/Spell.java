package io.dods.model.properties;

import io.dods.model.properties.misc.*;
import io.dods.model.publication.Publication;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * German: Zauber
 *
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(Spell.NAME)
public class Spell extends Property implements Casting, Checking, Costs, Ranged, ImprovementCharted, Effecting, Aiming {

    public static final String NAME = "Zauber";

    @ManyToOne
    private Cost cost;

    @Enumerated(EnumType.STRING)
    private ImprovementChart improvementChart;

    @Embedded
    private Check check;

    @ManyToOne
    private Range range;

    @ManyToOne
    private Duration duration;

    @ManyToOne
    private CastTime castTime;

    @ManyToMany
    private List<Target> targets = new ArrayList<>();

    public Spell() {
    }

    public Spell(String wikuUrl,
                 List<Publication> publication,
                 Cost cost,
                 ImprovementChart improvementChart,
                 Check check,
                 Range range,
                 Duration duration,
                 CastTime castTime,
                 List<Target> targets,
                 String name) {
        super(wikuUrl, name, publication);
        this.cost = cost;
        this.improvementChart = improvementChart;
        this.check = check;
        this.range = range;
        this.duration = duration;
        this.castTime = castTime;
        this.targets = targets;
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

    public CastTime getCastTime() {
        return castTime;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public @NotNull List<Target> getTargets() {
        return targets;
    }
}
