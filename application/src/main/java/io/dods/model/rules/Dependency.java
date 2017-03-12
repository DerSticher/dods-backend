package io.dods.model.rules;

import io.dods.interfaces.HasId;
import io.dods.model.conditions.Condition;
import io.dods.model.conditions.LevelRange;
import io.dods.model.heroes.Hero;
import io.dods.model.heroes.HeroProperty;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;

/**
 * Basic idea: only if the <code>condition</code> is fulfilled the <code>effect</code> can be used.
 *
 * In other words: if you want to get the <code>effect</code> you have to be sure the <code>condition</code> is fulfilled!
 *
 * @author Richard Gottschalk
 */
@Entity
@Table(indexes = {
        @Index(columnList = "id")
})
public class Dependency implements HasId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Rule rule;

    @OneToOne(cascade = CascadeType.ALL)
    private Condition condition;

    @ManyToOne(cascade = CascadeType.ALL)
    private Effect effect;

    public Dependency() {
    }

    public Dependency(Effect effect, Condition condition) {
        this(effect, condition, null);
    }

    public Dependency(Effect effect, Condition condition, Rule rule) {
        this.effect = effect;
        this.condition = condition;
        this.rule = rule;
    }

    public boolean isConditionFulfilled(Hero hero) {
        return condition == null || condition.isFulfilled(hero);
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isObligatory() {
        return getEffect().getProperty() == null;
    }

    public boolean isEffectFulfilledByExperienceLevel(Hero hero) {
        LevelRange levelRange = getLevelRange(hero);
        return getEffect().isFulfilledByExperience(hero, levelRange);
    }

    @Nullable
    private LevelRange getLevelRange(Hero hero) {
        if (condition != null) {
            return condition.getLevelRange(hero);
        }
        return null;
    }

    public int getMaxEffectLevel(Hero hero) {
        LevelRange levelRange = getLevelRange(hero);
        return effect.getMaxLevel(hero, levelRange);
    }

    public int getHeroEffectLevel(Hero hero) {
        return hero.getProperty(effect.getProperty()).map(HeroProperty::getLevel).orElse(0);
    }

}
