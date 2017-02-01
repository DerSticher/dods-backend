package io.dods.api.abhangigkeit.model;

/**
 * @author Richard Gottschalk
 */
public class CreateEffekt {
    private long attributId;
    private int level;

    public long getAttributId() {
        return attributId;
    }

    public void setAttributId(int attributId) {
        this.attributId = attributId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
