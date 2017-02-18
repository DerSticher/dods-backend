package io.dods.model.rules;

import io.dods.interfaces.HasId;
import io.dods.model.conditions.Condition;
import io.dods.model.heroes.Hero;
import org.jetbrains.annotations.Contract;

import javax.persistence.*;

/**
 * Basic idea: only if the <code>condition</code> is fulfilled the <code>effect</code> can be used.
 *
 * In other words: if you want to get the <code>effect</code> you have to be sure the <code>condition</code> is fulfilled!
 *
 * @author Richard Gottschalk
 */
@Entity
public class Dependency implements HasId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Regelwerk regelwerk;

    @OneToOne(cascade = CascadeType.ALL)
    private Condition condition;

    @ManyToOne(cascade = CascadeType.ALL)
    private Effect effect;

    public Dependency() {
    }

    public Dependency(Effect effect, Condition condition) {
        this(effect, condition, null);
    }

    public Dependency(Effect effect, Condition condition, Regelwerk regelwerk) {
        this.effect = effect;
        this.condition = condition;
        this.regelwerk = regelwerk;
    }

    public boolean isConditionFulfilled(Hero hero) {
        return condition == null || condition.isFulfilled(hero);
    }

    public boolean isEffectFulfilled(Hero hero) {
        return effect.isFulfilled(hero);
    }

    public Status getStatus(Hero hero) {
        return Status.parseStatus(this, hero);
    }

    public Regelwerk getRegelwerk() {
        return regelwerk;
    }

    public void setRegelwerk(Regelwerk regelwerk) {
        this.regelwerk = regelwerk;
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

    public enum Status {
        NOTHING_FULFILLED(true),
        REQUIREMENTS_MET(true),
        REQUIREMENTS_NOT_MET(false),
        FULFILLED(true);

        private boolean isValid;

        Status(boolean isValid) {
            this.isValid = isValid;
        }

        public boolean isValid() {
            return isValid;
        }

        @Contract("null, _ -> fail; !null, null -> fail")
        public static Status parseStatus(Dependency dependency, Hero hero) {
            if (dependency == null) throw new IllegalArgumentException("dependency cannot be null");
            if (hero == null) throw new IllegalArgumentException("heroes cannot be null");

            boolean conditionFulfilled = dependency.isConditionFulfilled(hero);
            boolean effectFulfilled = dependency.isEffectFulfilled(hero);

            if (conditionFulfilled && effectFulfilled) return FULFILLED;
            if (conditionFulfilled) return REQUIREMENTS_MET;
            if (effectFulfilled) return REQUIREMENTS_NOT_MET;
            return NOTHING_FULFILLED;
        }
    }
}
