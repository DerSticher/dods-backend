package io.dods.model.conditions.lists;

import io.dods.model.conditions.Condition;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "condition_and")
@DiscriminatorValue(AndCondition.NAME)
public class AndCondition extends NOfMCondition {

    public static final String NAME = "AND";

    public AndCondition() {
        this(new ArrayList<>());
    }

    public AndCondition(Condition... conditions) {
        this(Arrays.asList(conditions));
    }

    public AndCondition(List<Condition> conditions) {
        super(Comparison.EXACT, NOfMCondition.ALL, conditions);
    }
}
