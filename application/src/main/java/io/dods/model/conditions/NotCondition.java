package io.dods.model.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.heroes.Hero;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "condition_not")
public class NotCondition extends Condition {

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

    public Condition getNot() {
        return not;
    }

    public void setNot(Condition condition) {
        this.not = condition;
    }
}
