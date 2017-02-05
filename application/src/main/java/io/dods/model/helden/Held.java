package io.dods.model.helden;

import io.dods.model.Named;
import io.dods.model.attribute.Attribut;
import io.dods.model.attribute.misc.ApFix;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Held implements ApFix, Named {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column
    private String name = "";

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CharakterEigenschaft> charaktereigenschaften = new ArrayList<>();

    @Column(name = "age")
    private int alter = 0;

    @Column
    private String profession;
    private String kultur;
    private String augenfarbe;
    private String geburtsort;
    private double gewicht;
    private double groesse;

    @Nullable
    public CharakterEigenschaft getEigenschaft(Attribut attribut) {
        return charaktereigenschaften.parallelStream().filter(a -> a.isAttribut(attribut)).findFirst().orElse(null);
    }

    @Nullable
    public CharakterEigenschaft getEigenschaft(long attributId) {
        return charaktereigenschaften.parallelStream().filter(a -> a.getAttribut().getId() == attributId).findFirst().orElse(null);
    }

    public void addEigenschaft(@NotNull Attribut attribut, int level) {
        addEigenschaft(new CharakterEigenschaft(attribut, level));
    }

    public synchronized void addEigenschaft(CharakterEigenschaft charakterEigenschaft) {
        if (charakterEigenschaft.getAttribut() == null) {
            throw new IllegalArgumentException(String.format("You cannot add a %s without %s",
                    CharakterEigenschaft.class.getSimpleName(),
                    Attribut.class.getSimpleName()));
        }

        @Nullable CharakterEigenschaft currentCharakterEigenschaft = getEigenschaft(charakterEigenschaft.getAttribut());
        if (currentCharakterEigenschaft == null) {
            charaktereigenschaften.add(charakterEigenschaft);
        } else {
            currentCharakterEigenschaft.setLevel(charakterEigenschaft.getLevel());
        }
    }

    public void removeEigenschaft(@NotNull Attribut attribut) {
        executeRemoveEigenschaft(attribut.getId());
    }

    public void removeEigenschaft(int attributId) {
        executeRemoveEigenschaft(attributId);
    }

    private synchronized void executeRemoveEigenschaft(long attributId) {
        @Nullable CharakterEigenschaft charakterEigenschaft = getEigenschaft(attributId);
        if (charakterEigenschaft == null) return;

        if (charakterEigenschaft.getAttribut().hasDefaultLevel()) {
            charakterEigenschaft.setLevel(charakterEigenschaft.getAttribut().getDefaultLevel());
        } else {
            this.charaktereigenschaften.stream()
                    .filter(eigenschaft -> eigenschaft.getAttribut().getId() == attributId)
                    .findFirst()
                    .ifPresent(charaktereigenschaften::remove);
        }
    }

    /**
     * Returns a list of {@link CharakterEigenschaft}en
     * @return the lsit
     */
    public List<CharakterEigenschaft> getCharakterEigenschaften() {
        return charaktereigenschaften;
    }

    @Override
    public int getAp() {
        return charaktereigenschaften.stream().mapToInt(CharakterEigenschaft::getAp).sum();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getId() {
        return id;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public int getAlter() {
        return alter;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setKultur(String kultur) {
        this.kultur = kultur;
    }

    public String getKultur() {
        return kultur;
    }

    public void setAugenfarbe(String augenfarbe) {
        this.augenfarbe = augenfarbe;
    }

    public String getAugenfarbe() {
        return augenfarbe;
    }

    public void setGeburtsort(String geburtsort) {
        this.geburtsort = geburtsort;
    }

    public String getGeburtsort() {
        return geburtsort;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGroesse(double groesse) {
        this.groesse = groesse;
    }

    public double getGroesse() {
        return groesse;
    }
}
