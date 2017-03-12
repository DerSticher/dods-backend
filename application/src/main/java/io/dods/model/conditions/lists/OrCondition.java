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
@Table(name = "condition_or")
@DiscriminatorValue(OrCondition.NAME)
public class OrCondition extends NOfMCondition {

    public static final String NAME = "OR";

    public OrCondition() {
        this(new ArrayList<>());
    }

    public OrCondition(Condition... conditions) {
        this(Arrays.asList(conditions));
    }

    public OrCondition(List<Condition> conditions) {
        super(Comparison.MINIMUM, 1, conditions);
    }
}
