package io.dods.model.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.heroes.Hero;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "condition_or")
public class OrCondition extends Condition {

    @JsonProperty("or")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Condition> conditions = new ArrayList<>();

    public OrCondition() {
    }

    public OrCondition(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public OrCondition(Condition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public boolean isFulfilled(Hero hero) {
        boolean isFulfilled = false;

        for (Condition condition : conditions) {
            isFulfilled |= condition.isFulfilled(hero);
        }

        return isFulfilled;
    }

    public List<Condition> getOr() {
        return conditions;
    }

    public void setOr(List<Condition> bedingungen) {
        this.conditions = bedingungen;
    }

    public void add(Condition condition) {
        this.conditions.add(condition);
    }
}
