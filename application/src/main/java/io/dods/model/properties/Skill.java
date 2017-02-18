package io.dods.model.properties;

import io.dods.model.exceptions.HasNoDefaultLevelException;
import io.dods.model.properties.misc.Check;
import io.dods.model.properties.misc.Checking;
import io.dods.model.properties.misc.ImprovementChart;
import io.dods.model.properties.misc.ImprovementCharted;

import javax.persistence.*;

/**
 * German: Fertigkeit
 *
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(Skill.NAME)
public class Skill extends Property implements Checking, ImprovementCharted {

    public static final String NAME = "Fertigkeit";

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
                throw new IllegalArgumentException(String.format("Cannot parse %s to a Group", name), e);
            }
        }
    }

    @Enumerated(EnumType.STRING)
    private ImprovementChart improvementChart;

    @Embedded
    private Check check;

    @Enumerated(EnumType.STRING)
    private Gruppe gruppe;

    public Skill() {
    }

    public Skill(ImprovementChart improvementChart, Check check, Gruppe gruppe, String name) {
        super("", name, null);
        this.improvementChart = improvementChart;
        this.check = check;
        this.gruppe = gruppe;
    }

    public Gruppe getGruppe() {
        return gruppe;
    }

    public ImprovementChart getImprovementChart() {
        return improvementChart;
    }

    public Check getCheck() {
        return check;
    }

    @Override
    public int getAp(int level) {
        return ImprovementCharted.super.getAp(level) - ImprovementCharted.super.getAp(getDefaultLevel());
    }

    @Override
    public int getDefaultLevel() throws HasNoDefaultLevelException {
        return 0;
    }
}
