package io.dods.api.abhangigkeit.model;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
public class CreateBedingung {

    private Type type;

    private int attributId;

    private int level;

    private List<CreateBedingung> sublist;

    private CreateBedingung subelement;

    public enum Type {
        AND,
        OR,
        MIN,
        MAX,
        NOT,
        HAS
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

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

    public List<CreateBedingung> getSublist() {
        return sublist;
    }

    public void setSublist(List<CreateBedingung> sublist) {
        this.sublist = sublist;
    }

    public CreateBedingung getSubelement() {
        return subelement;
    }

    public void setSubelement(CreateBedingung subelement) {
        this.subelement = subelement;
    }
}
