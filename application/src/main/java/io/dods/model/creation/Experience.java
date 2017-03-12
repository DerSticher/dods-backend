package io.dods.model.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Experience {

    public enum Value {
        MAX_ATTRIBUTE_VALUE,
        MAX_SKILL_VALUE,
        MAX_COMBAT_TECHNIQUE,
        MAX_ATTRIBUTE_TOTAL,
        MAX_SPELL_OR_LITURGICAL_CHANT_COUNT,
        MAX_FROM_OTHER_TRADITIONS
    }

    @ApiModelProperty(notes = "will be set by server", readOnly = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @JsonProperty("name")
    private String name;
    
    @Column
    @JsonProperty("ap")
    private int adventurePoints;

    @Column
    @JsonProperty("max_eigenschaft")
    private int maximumAttributeValue;

    @Column
    @JsonProperty("max_fertigkeit")
    private int maximumSkillValue;

    @Column
    @JsonProperty("max_kampftechnik")
    private int maximumCombatTechnique;

    @Column
    @JsonProperty("max_eigenschaften_total")
    private int maximumAttributeTotal;

    @Column
    @JsonProperty("max_zauber_oder_liturgie")
    private int maxNumberOfSpellsOrLiturgicalChants;

    @Column
    @JsonProperty("max_von_anderen_tratidionen")
    private int numerFromOtherTraditions;

    public int getValue(Experience.Value value) {
        if (value == null) return 1;
        switch (value) {
            case MAX_ATTRIBUTE_VALUE:
                return getMaximumAttributeValue();
            case MAX_SKILL_VALUE:
                return getMaximumSkillValue();
            case MAX_COMBAT_TECHNIQUE:
                return getMaximumCombatTechnique();
            case MAX_ATTRIBUTE_TOTAL:
                return getMaximumAttributeTotal();
            case MAX_SPELL_OR_LITURGICAL_CHANT_COUNT:
                return getMaxNumberOfSpellsOrLiturgicalChants();
            case MAX_FROM_OTHER_TRADITIONS:
                return getNumerFromOtherTraditions();
        }
        throw new IllegalStateException(String.format("There is no mapping from %s to an valid level", value.toString()));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAdventurePoints() {
        return adventurePoints;
    }

    public void setAdventurePoints(int adventurePoints) {
        this.adventurePoints = adventurePoints;
    }

    public int getMaximumAttributeValue() {
        return maximumAttributeValue;
    }

    public void setMaximumAttributeValue(int maximumAttributeValue) {
        this.maximumAttributeValue = maximumAttributeValue;
    }

    public int getMaximumSkillValue() {
        return maximumSkillValue;
    }

    public void setMaximumSkillValue(int maximumSkillValue) {
        this.maximumSkillValue = maximumSkillValue;
    }

    public int getMaximumCombatTechnique() {
        return maximumCombatTechnique;
    }

    public void setMaximumCombatTechnique(int maximumCombatTechnique) {
        this.maximumCombatTechnique = maximumCombatTechnique;
    }

    public int getMaximumAttributeTotal() {
        return maximumAttributeTotal;
    }

    public void setMaximumAttributeTotal(int maximumAttributeTotal) {
        this.maximumAttributeTotal = maximumAttributeTotal;
    }

    public int getMaxNumberOfSpellsOrLiturgicalChants() {
        return maxNumberOfSpellsOrLiturgicalChants;
    }

    public void setMaxNumberOfSpellsOrLiturgicalChants(int maxNumberOfSpellsOrLiturgicalChants) {
        this.maxNumberOfSpellsOrLiturgicalChants = maxNumberOfSpellsOrLiturgicalChants;
    }

    public int getNumerFromOtherTraditions() {
        return numerFromOtherTraditions;
    }

    public void setNumerFromOtherTraditions(int numerFromOtherTraditions) {
        this.numerFromOtherTraditions = numerFromOtherTraditions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
