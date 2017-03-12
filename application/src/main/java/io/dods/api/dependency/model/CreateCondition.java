package io.dods.api.dependency.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
public class CreateCondition {

    @JsonProperty("type")
    private Type type;

    @JsonProperty("attributId")
    private int propertyId;

    @JsonProperty("level")
    private int level;

    @JsonProperty("sublist")
    private List<CreateCondition> sublist;

    @JsonProperty("subelement")
    private CreateCondition subelement;

    public enum Type {
        AND,
        OR,
        MIN,
        NOT,
        HAS
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<CreateCondition> getSublist() {
        return sublist;
    }

    public void setSublist(List<CreateCondition> sublist) {
        this.sublist = sublist;
    }

    public CreateCondition getSubelement() {
        return subelement;
    }

    public void setSubelement(CreateCondition subelement) {
        this.subelement = subelement;
    }
}
