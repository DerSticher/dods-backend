package io.dods.model.properties;

import io.dods.model.properties.misc.ApFix;
import io.dods.model.publication.Publication;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * German: Spezies
 *
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(Species.NAME)
public class Species extends Property implements ApFix {

    public static final String NAME = "Spezies";

    @Column
    private Integer baseLivePoints;

    @Column
    private Integer baseSpirit;

    @Column
    private Integer baseToughness;

    @Column
    private Integer baseMovement;

    @ManyToMany
    private List<Advantage> advantages;

    @ManyToMany
    private List<Advantage> disadvantages;

    @Column
    private Integer apCost;

    public Species() {
    }

    public Species(String wikiUrl, String name, List<Publication> publications) {
        super(wikiUrl, name, publications);
    }

    public int getBaseLivePoints() {
        return baseLivePoints;
    }

    public void setBaseLivePoints(int baseLivePoints) {
        this.baseLivePoints = baseLivePoints;
    }

    public int getBaseSpirit() {
        return baseSpirit;
    }

    public void setBaseSpirit(int baseSpirit) {
        this.baseSpirit = baseSpirit;
    }

    public int getBaseToughness() {
        return baseToughness;
    }

    public void setBaseToughness(int baseToughness) {
        this.baseToughness = baseToughness;
    }

    public int getBaseMovement() {
        return baseMovement;
    }

    public void setBaseMovement(int baseMovement) {
        this.baseMovement = baseMovement;
    }

//    public List<Effect> getAttributeMaximums() {
//        return attributeMaximums;
//    }
//
//    public void setAttributeMaximums(List<Effect> attributeMaximums) {
//        this.attributeMaximums = attributeMaximums;
//    }

    public List<Advantage> getAdvantages() {
        return advantages;
    }

    public void setAdvantages(List<Advantage> advantages) {
        this.advantages = advantages;
    }

    public List<Advantage> getDisadvantages() {
        return disadvantages;
    }

    public void setDisadvantages(List<Advantage> disadvantages) {
        this.disadvantages = disadvantages;
    }

    public int getApCost() {
        return apCost;
    }

    public void setApCost(int apCost) {
        this.apCost = apCost;
    }
}
