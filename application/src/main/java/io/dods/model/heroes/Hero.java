package io.dods.model.heroes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.Named;
import io.dods.model.creation.Experience;
import io.dods.model.properties.Property;
import io.dods.model.properties.misc.ApFix;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Hero implements ApFix, Named {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column
    private String name = "";

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty("charakterEigenschaften")
    private List<HeroProperty> heroProperties = new ArrayList<>();

    @Column(name = "age")
    @JsonProperty("alter")
    private int age = 0;

    @Column
    @JsonProperty("profession")
    private String profession;

    @Column
    @JsonProperty("kultur")
    private String culture;

    @Column
    @JsonProperty("augenfarbe")
    private String eyeColor;

    @Column
    @JsonProperty("geburtsort")
    private String placeOfBirth;

    @Column
    @JsonProperty("gewicht")
    private double bodyWeight;

    @Column
    @JsonProperty("groesse")
    private double bodyHeight;

    @ManyToOne
    @JsonProperty("start_erfahrung")
    private Experience startingExperience;

    @Column
    @JsonProperty("erfahrung_ignorieren")
    private boolean ignoreExperience;

    public Optional<HeroProperty> getProperty(Property property) {
        return heroProperties.parallelStream().filter(a -> a.isProperty(property)).findFirst();
    }

    @Nullable
    public HeroProperty getProperty(long propertyId) {
        return heroProperties.parallelStream().filter(a -> a.getProperty().getId() == propertyId).findFirst().orElse(null);
    }

    public void addProperty(@NotNull Property property, int level) {
        addProperty(new HeroProperty(property, level));
    }

    public synchronized void addProperty(HeroProperty heroProperty) {
        if (heroProperty.getProperty() == null) {
            throw new IllegalArgumentException(String.format("You cannot add a %s without %s",
                    HeroProperty.class.getSimpleName(),
                    Property.class.getSimpleName()));
        }

        HeroProperty currentHeroProperty = getProperty(heroProperty.getProperty()).orElse(null);
        if (currentHeroProperty != null) {
            currentHeroProperty.setLevel(heroProperty.getLevel());
        } else {
            heroProperties.add(heroProperty);
        }
    }

    public void removeProperty(@NotNull Property property) {
        executeRemoveProperty(property.getId());
    }

    public void removeProperty(int propertyId) {
        executeRemoveProperty(propertyId);
    }

    private synchronized void executeRemoveProperty(long propertyId) {
        @Nullable HeroProperty heroProperty = getProperty(propertyId);
        if (heroProperty == null) return;

        if (heroProperty.getProperty().hasDefaultLevel()) {
            heroProperty.setLevel(heroProperty.getProperty().getDefaultLevel());
        } else {
            this.heroProperties.stream()
                    .filter(eigenschaft -> eigenschaft.getProperty().getId() == propertyId)
                    .findFirst()
                    .ifPresent(heroProperties::remove);
        }
    }

    /**
     * Returns a list of {@link HeroProperty}en
     * @return the lsit
     */
    public List<HeroProperty> getHeroProperties() {
        return heroProperties;
    }

    public List<HeroProperty> getHeroProperties(String type) {
        return heroProperties.stream()
                .filter(heroProperty -> heroProperty.getProperty().getType().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public int getAp() {
        return heroProperties.stream().mapToInt(HeroProperty::getAp).sum();
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

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getCulture() {
        return culture;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setBodyWeight(double bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public double getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyHeight(double bodyHeight) {
        this.bodyHeight = bodyHeight;
    }

    public double getBodyHeight() {
        return bodyHeight;
    }

    public Experience getStartingExperience() {
        return startingExperience;
    }

    public void setStartingExperience(Experience startingExperience) {
        this.startingExperience = startingExperience;
    }

    public boolean ignoreExperience() {
        return ignoreExperience;
    }

    public void setIgnoreExperience(boolean ignoreExperience) {
        this.ignoreExperience = ignoreExperience;
    }
}
