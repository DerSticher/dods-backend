package io.dods.model.conditions.level;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.interfaces.HasId;
import io.dods.model.conditions.Condition;
import io.dods.model.conditions.level.check.LevelCheck;
import io.dods.model.heroes.Hero;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "condition_level")
public abstract class LevelCondition extends Condition implements HasId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("modifier")
    @Column
    private Integer modifier;

    @JsonProperty
    @OneToOne(cascade = CascadeType.ALL)
    private LevelCheck levelCheck;

    public LevelCondition() {
    }

    public LevelCondition(LevelCheck levelCheck, int modifier) {
        this.levelCheck = levelCheck;
        this.modifier = modifier;
    }

    @Override
    public final int getMinLevel(Hero hero) {
        return getLevel(hero) + getModifier();
    }

    @Override
    public final int getMaxLevel(Hero hero) {
        return getLevel(hero) + getModifier();
    }

    public final int getLevel(Hero hero) {
        return getLevelCheck().getLevel(hero);
    }

    private LevelCheck getLevelCheck() {
        return levelCheck;
    }

    public final int getModifier() {
        return modifier != null ? modifier : 0;
    }

    public void setLevelCheck(LevelCheck levelCheck) {
        this.levelCheck = levelCheck;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
