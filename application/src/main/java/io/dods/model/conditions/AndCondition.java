package io.dods.model.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.heroes.Hero;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "condition_and")
public class AndCondition extends Condition {

    @JsonProperty("and")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Condition> conditions = new ArrayList<>();

    public AndCondition() {
    }

    public AndCondition(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public AndCondition(Condition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public boolean isFulfilled(Hero hero) {
        boolean isFulfilled = true;

        for (Condition condition : conditions) {
            isFulfilled &= condition.isFulfilled(hero);
        }

        return isFulfilled;
    }

    public List<Condition> getAnd() {
        return conditions;
    }

    public void setAnd(@NotNull List<Condition> bedingungen) {
        this.conditions = bedingungen;
    }

    public void add(Condition condition) {
        conditions.add(condition);
    }
}
