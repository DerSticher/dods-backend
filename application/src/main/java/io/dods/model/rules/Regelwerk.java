package io.dods.model.rules;

import io.dods.model.BaseNamedValue;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Regelwerk extends BaseNamedValue {

    @Column
    private boolean isOptional;

    public Regelwerk(boolean isOptional) {
        super();
        this.isOptional = isOptional;
    }

    public Regelwerk(boolean isOptional, String name) {
        super(name);
        this.isOptional = isOptional;
    }
}
