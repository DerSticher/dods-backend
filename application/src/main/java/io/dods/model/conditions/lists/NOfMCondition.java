package io.dods.model.conditions.lists;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.conditions.Condition;
import io.dods.model.heroes.Hero;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "condition_n_of_m")
@DiscriminatorValue(NOfMCondition.NAME)
public class NOfMCondition extends Condition {

    public static final String NAME = "N_OF_M";

    public static final int ALL = -1;

    public enum Comparison {
        MINIMUM,
        MAXIMUM,
        EXACT
    }

    @JsonProperty("conditions")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Condition> conditions = new ArrayList<>();

    @JsonProperty("n")
    private int n = ALL;

    @JsonProperty("vergleich")
    @Enumerated(EnumType.STRING)
    private Comparison comparison;

    public NOfMCondition() {
        this(0);
    }

    public NOfMCondition(int n) {
        this(n, new ArrayList<>());
    }

    public NOfMCondition(int n, List<Condition> conditions) {
        this(Comparison.MINIMUM, n, conditions);
    }

    public NOfMCondition(Comparison comparison, int n, List<Condition> conditions) {
        this.comparison = comparison;
        this.n = n;
        this.conditions = conditions;
    }

    @Override
    public final boolean isFulfilled(Hero hero) {
        long conditionCount = this.conditions.size();
        long fulfilledCount = this.conditions.stream().filter(c -> c.isFulfilled(hero)).count();

        switch (comparison) {
            case MINIMUM:
                return n == ALL ? fulfilledCount >= conditionCount : fulfilledCount >= n;
            case MAXIMUM:
                return n == ALL ? fulfilledCount <= conditionCount : fulfilledCount <= n;
            case EXACT:
            default:
                return n == ALL ? fulfilledCount == conditionCount : fulfilledCount == n;
        }
    }

    @Override
    public int getMinLevel(Hero hero) {
        return conditions.stream()
                .filter(c -> c.isFulfilled(hero))
                .mapToInt(c -> c.getMinLevel(hero))
                .min().orElse(0);
    }

    @Override
    public int getMaxLevel(Hero hero) {
        return conditions.stream()
                .filter(c -> c.isFulfilled(hero))
                .mapToInt(c -> c.getMinLevel(hero))
                .max().orElse(0);
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void add(Condition condition) {
        this.conditions.add(condition);
    }
}
