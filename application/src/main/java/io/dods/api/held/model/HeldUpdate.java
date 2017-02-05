package io.dods.api.held.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
public class HeldUpdate {
    private String name;
    private int alter;
    private String augenfarbe;
    private double groesse;
    private double gewicht;
    private String geburtsort;
    private String kultur;
    private String profession;

    private List<CharakterEigenschaftUpdate> update = new ArrayList<>();
    private List<Integer> clearAttributIds = new ArrayList<>();

    public String getName() {
        return name;
    }

    public int getAlter() {
        return alter;
    }

    public String getAugenfarbe() {
        return augenfarbe;
    }

    public double getGroesse() {
        return groesse;
    }

    public double getGewicht() {
        return gewicht;
    }

    public String getGeburtsort() {
        return geburtsort;
    }

    public String getKultur() {
        return kultur;
    }

    public String getProfession() {
        return profession;
    }

    public List<CharakterEigenschaftUpdate> getUpdate() {
        return update;
    }

    public List<Integer> getClearAttributIds() {
        return clearAttributIds;
    }
}
