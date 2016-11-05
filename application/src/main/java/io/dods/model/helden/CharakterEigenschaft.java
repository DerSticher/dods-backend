package io.dods.model.helden;

import io.dods.model.attribute.Attribut;
import io.dods.model.attribute.misc.ApFix;
import io.dods.model.attribute.misc.ApVar;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
public class CharakterEigenschaft {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Attribut attribut;

    @Column
    private int level;

    @Column
    private boolean isActivated = true;

    public CharakterEigenschaft() {
        // default constructor for ORM
    }

    @Nullable
    public Attribut getAttribut() {
        return attribut;
    }

    public void setAttribut(Attribut attribut) {
        this.attribut = attribut;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 0) throw new IllegalArgumentException("level cannot be negative!");
        this.level = level;
    }

    public boolean isAttribut(Attribut attribut) {
        if (this.attribut != null) return this.attribut.equals(attribut);
        return false;
    }

    public int getAp() {
        if (!isActivated()) return 0;

        if (attribut instanceof ApFix) {
            return ((ApFix) attribut).getAp();

        } else if (attribut instanceof ApVar) {
            return ((ApVar) attribut).getAp(level);

        }
        throw new IllegalStateException(String.format("Class %s is not an instance of %s or %s",
                attribut.getClass().getSimpleName(),
                ApFix.class.getSimpleName(),
                ApVar.class.getSimpleName()));
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

}
