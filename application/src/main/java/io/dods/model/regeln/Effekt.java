package io.dods.model.regeln;

import io.dods.model.attribute.Attribut;
import io.dods.model.helden.CharakterEigenschaft;
import io.dods.model.helden.Held;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;

/**
 *
 * @author Richard Gottschalk
 */
@Entity
public class Effekt {

    @Id
    @GeneratedValue
    private long id;

    private int level;

    @ManyToOne
    private Attribut attribut;

    public Effekt() {
    }

    public Effekt(Attribut attribut, int level) {
        this.attribut = attribut;
        this.level = level;
    }

    public boolean isFulfilled(Held held) {
        @Nullable CharakterEigenschaft attribut = held.getEigenschaft(this.attribut);
        if (attribut != null) {
            return attribut.getLevel() >= level;
        }
        // not available means it can only be not fulfilled!
        return false;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Attribut getAttribut() {
        return attribut;
    }

    public void setAttribut(Attribut attribut) {
        this.attribut = attribut;
    }
}
