package io.dods.model.attribute.misc;

import io.dods.model.BaseNamedValue;

import javax.persistence.Entity;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Dauer extends BaseNamedValue {

    public Dauer() {
    }

    public Dauer(String name) {
        super(name);
    }
}
