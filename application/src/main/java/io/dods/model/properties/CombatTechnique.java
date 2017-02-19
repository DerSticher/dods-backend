package io.dods.model.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.properties.misc.ImprovementChart;
import io.dods.model.properties.misc.ImprovementCharted;
import io.dods.model.publication.Publication;

import javax.persistence.*;
import java.util.List;

/**
 * German: Kampftechnik
 *
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(CombatTechnique.NAME)
public class CombatTechnique extends Property implements ImprovementCharted {

    public static final String NAME = "Kampftechnik";

    @JsonProperty("leiteigenschaft")
    @ManyToMany
    private List<Attribute> primaryAttributes;

    @Enumerated(EnumType.STRING)
    private ImprovementChart improvementChart;

    @JsonProperty("isFernkampf")
    @Column
    private boolean isRangedCombat;

    public CombatTechnique() {
    }

    public CombatTechnique(String wikiUrl, List<Publication> publication, List<Attribute> primaryAttributes, ImprovementChart improvementChart, String name, boolean isRangedCombat) {
        super(wikiUrl, name, publication);
        this.primaryAttributes = primaryAttributes;
        this.improvementChart = improvementChart;
        this.isRangedCombat = isRangedCombat;
    }

    public List<Attribute> getPrimaryAttributes() {
        return primaryAttributes;
    }

    public ImprovementChart getImprovementChart() {
        return improvementChart;
    }

    @JsonIgnore
    public boolean isRangedCombat() {
        return isRangedCombat;
    }

    @Override
    public int getAp(int level) {
        return ImprovementCharted.super.getAp(level) - ImprovementCharted.super.getAp(getDefaultLevel());
    }

    @Override
    public int getDefaultLevel() {
        return 6;
    }
}
