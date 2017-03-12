package io.dods.model.rules;

import io.dods.model.BaseNamedValue;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Rule extends BaseNamedValue {

    @Column
    private boolean isOptional;

    public Rule(boolean isOptional) {
        super();
        this.isOptional = isOptional;
    }

    public Rule(boolean isOptional, String name) {
        super(name);
        this.isOptional = isOptional;
    }
}
