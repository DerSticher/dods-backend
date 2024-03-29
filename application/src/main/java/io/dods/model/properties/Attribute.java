package io.dods.model.properties;

import io.dods.model.properties.misc.ImprovementChart;
import io.dods.model.properties.misc.ImprovementCharted;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * German: Eigenschaft
 *
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(Attribute.NAME)
public class Attribute extends Property implements ImprovementCharted {

    public static final String NAME = "Eigenschaft";

    @Enumerated(EnumType.STRING)
    private ImprovementChart improvementChart = ImprovementChart.E;

    public Attribute() {
    }

    public Attribute(ImprovementChart improvementChart) {
        this.improvementChart = improvementChart;
    }

    public Attribute(ImprovementChart improvementChart, String name) {
        super("", name, null);
        this.improvementChart = improvementChart;
    }

    public ImprovementChart getImprovementChart() {
        return improvementChart;
    }

    @Override
    public int getAp(int level) {
        return ImprovementCharted.super.getAp(level) - ImprovementCharted.super.getAp(getDefaultLevel());
    }

    @Override
    public int getDefaultLevel() {
        return 8;
    }
}
