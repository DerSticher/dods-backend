package io.dods.model.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.properties.misc.*;
import io.dods.model.publication.Publication;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.List;

/**
 * German: Sonderfertigkeit
 *
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(SpecialAbility.NAME)
public class SpecialAbility extends Property implements ApFix, Casting, Checking, Costs, Ranged, ImprovementCharted, Effecting {

    public static final String NAME = "Sonderfertigkeit";

    public enum Group {
        KARMAL,

        MAGISCH,
        MAGISCH_AHNENZEICHEN,
        MAGISCH_DOLCH,
        MAGISCH_ELFENLIED,
        MAGISCH_ELFENLIED_VERZERRT,
        MAGISCH_ERWEITERT,
        MAGISCH_FLUCH,
        MAGISCH_HERRSCHAFTSRITUAL,
        MAGISCH_STAB,
        MAGISCH_VERTRAUTENTIER,
        MAGISCH_STIL,

        PROFAN,
        PROFAN_ALLGEMEIN,
        PROFAN_BEFEHL,
        PROFAN_KAMPF,
        PROFAN_KAMPF_ERWEITERT,
        PROFAN_KAMPF_STIL,
        PROFAN_PRUEGEL,
        PROFAN_SCHICKSALSPUNKT,
        PROFAN_TIER
    }

    @Column
    private int ap;

    @ApiModelProperty(required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "special_ability_group")
    @JsonProperty("gruppe")
    private Group group;

    @ManyToOne
    private CastTime castTime;

    @ManyToOne
    private Cost cost;

    @Embedded
    private Check check;

    @ManyToOne
    private Range range;

    @ManyToOne
    private Duration duration;

    @Enumerated(EnumType.STRING)
    private ImprovementChart improvementChart;

    public SpecialAbility() {
    }

    public SpecialAbility(String wikiUrl,
                          List<Publication> publication,
                          int ap,
                          Group group,
                          CastTime castTime,
                          Cost cost,
                          Check check,
                          Range range,
                          Duration duration,
                          ImprovementChart improvementChart,
                          String name) {
        super(wikiUrl, name, publication);
        this.ap = ap;
        this.group = group;
        this.castTime = castTime;
        this.cost = cost;
        this.check = check;
        this.range = range;
        this.duration = duration;
        this.improvementChart = improvementChart;
    }

    @Override
    public void update(Property property) {
        super.update(property);

        SpecialAbility castedProperty = (SpecialAbility) property;
        this.ap = castedProperty.ap;
        this.group = castedProperty.group;
        this.castTime = castedProperty.castTime;
        this.cost = castedProperty.cost;
        this.check = castedProperty.check;
        this.range = castedProperty.range;
        this.duration = castedProperty.duration;
        this.improvementChart = castedProperty.improvementChart;
    }

    @Override
    public int getAp() {
        return ap;
    }

    public CastTime getCastTime() {
        return castTime;
    }

    public Range getRange() {
        return range;
    }

    public @Nullable ImprovementChart getImprovementChart() {
        return improvementChart;
    }

    public @Nullable Check getCheck() {
        return check;
    }

    public @Nullable Duration getDuration() {
        return duration;
    }

    public @Nullable Cost getCost() {
        return cost;
    }

    public Group getGroup() {
        return group;
    }
}
