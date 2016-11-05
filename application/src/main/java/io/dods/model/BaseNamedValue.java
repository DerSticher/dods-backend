package io.dods.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * @author Richard Gottschalk
 */
@Entity
@Table(indexes = {
        @Index(columnList = "id")
})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseNamedValue implements Named {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private String name;

    @Column
    private String identifier;

    public BaseNamedValue() {
    }

    public BaseNamedValue(String name) {
        this.name = name;
        this.identifier = NameUtils.createIdentifier(name);
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof BaseNamedValue) {
            return ((BaseNamedValue) obj).id == this.id;
        }
        return false;
    }

    public long getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }
}
