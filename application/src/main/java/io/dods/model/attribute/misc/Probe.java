package io.dods.model.attribute.misc;

import io.dods.model.attribute.Eigenschaft;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Probe {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    private List<Eigenschaft> eigenschaften = new ArrayList<>();

    public @NotNull String getBezeichnung() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < eigenschaften.size(); i++) {
            if (i > 0) stringBuilder.append('/');
            Eigenschaft eigenschaft = eigenschaften.get(i);
            stringBuilder.append(eigenschaft.getName());
        }

        return stringBuilder.toString();
    }
}
