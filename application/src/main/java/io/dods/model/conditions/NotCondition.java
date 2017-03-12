package io.dods.model.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.heroes.Hero;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "condition_not")
@DiscriminatorValue(NotCondition.NAME)
public class NotCondition extends Condition {

    public static final String NAME = "NOT";

    @JsonProperty("not")
    @ManyToOne(cascade = CascadeType.ALL)
    private Condition not;

    public NotCondition() {
    }

    public NotCondition(Condition not) {
        this.not = not;
    }

    @Override
    public boolean isFulfilled(Hero hero) {
        return !not.isFulfilled(hero);
    }

    @Override
    public int getMinLevel(Hero hero) {
        return 0;
    }

    @Override
    public int getMaxLevel(Hero hero) {
        return 0;
    }

    public Condition getNot() {
        return not;
    }

    public void setNot(Condition condition) {
        this.not = condition;
    }
}
