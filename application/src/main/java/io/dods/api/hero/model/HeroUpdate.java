package io.dods.api.hero.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
public class HeroUpdate {

    @JsonProperty("name")
    private String name;

    @JsonProperty("alter")
    private int age;

    @JsonProperty("augenfarbe")
    private String eyeColor;

    @JsonProperty("groesse")
    private double bodySize;

    @JsonProperty("gewicht")
    private double bodyWeight;

    @JsonProperty("geburtsort")
    private String placeOfBirth;

    @JsonProperty("kultur")
    private String culture;

    @JsonProperty("profession")
    private String profession;

    @JsonProperty("update")
    private List<CharacterPropertyUpdate> updates = new ArrayList<>();

    @JsonProperty("clearAttributIds")
    private List<Integer> clearPropertyIds = new ArrayList<>();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public double getBodySize() {
        return bodySize;
    }

    public double getBodyWeight() {
        return bodyWeight;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getCulture() {
        return culture;
    }

    public String getProfession() {
        return profession;
    }

    public List<CharacterPropertyUpdate> getUpdates() {
        return updates;
    }

    public List<Integer> getClearPropertyIds() {
        return clearPropertyIds;
    }
}
