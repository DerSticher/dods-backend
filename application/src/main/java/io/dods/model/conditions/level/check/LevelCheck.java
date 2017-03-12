package io.dods.model.conditions.level.check;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dods.interfaces.HasId;
import io.dods.model.heroes.Hero;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(name = "level_check")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ExperienceLevelCheck.class, name = "EXPERIENCE"),
        @JsonSubTypes.Type(value = FixedLevelCheck.class, name = "FIXED"),
        @JsonSubTypes.Type(value = PropertyLevelCheck.class, name = "PROPERTY")
})
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class LevelCheck implements HasId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public abstract int getLevel(Hero hero);

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
