package io.dods.model.properties;

import io.dods.model.properties.misc.ApFix;
import io.dods.model.properties.misc.Range;
import io.dods.model.properties.misc.Ranged;
import io.dods.model.publication.Publication;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * German: Vorteil
 *
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(Advantage.NAME)
public class Advantage extends Property implements ApFix, Ranged {

    public static final String NAME = "Vorteil";

    @Column
    private int ap;

    @ManyToOne
    private Range range;

    public Advantage() {
    }

    public Advantage(String wikuUrl, List<Publication> publication, int ap, Range range, String name) {
        super(wikuUrl, name, publication);
        this.ap = ap;
        this.range = range;
    }

    @Override
    public int getAp() {
        return ap;
    }

    public Range getRange() {
        return range;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }
}
