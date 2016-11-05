package io.dods.model.helden;

import io.dods.model.attribute.Attribut;
import io.dods.model.attribute.misc.ApFix;

/**
 * @author Richard Gottschalk
 */
public class TestableAttribut extends Attribut implements ApFix {

    private int ap;

    public TestableAttribut(long id, int ap) {
        this(id, ap, "");
    }

    public TestableAttribut(long id, int ap, String name) {
        super(name);
        this.id = id;
        this.ap = ap;
    }

    @Override
    public int getAp() {
        return ap;
    }
}
