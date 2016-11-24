package io.dods.model.attribute.misc;

import io.dods.model.attribute.Eigenschaft;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Probe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    private List<Eigenschaft> eigenschaften = new ArrayList<>();

    public Probe() {
    }

    public Probe(List<Eigenschaft> eigenschaften) {
        this.eigenschaften = eigenschaften;
    }

    public @NotNull String getBezeichnung() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < eigenschaften.size(); i++) {
            if (i > 0) stringBuilder.append('/');
            Eigenschaft eigenschaft = eigenschaften.get(i);
            stringBuilder.append(eigenschaft.getName());
        }

        return stringBuilder.toString();
    }

    public long getId() {
        return id;
    }

    public List<Eigenschaft> getEigenschaften() {
        return eigenschaften;
    }

    public void setEigenschaften(List<Eigenschaft> eigenschaften) {
        this.eigenschaften = eigenschaften;
    }

    public void addEigenschaft(Eigenschaft eigenschaft) {
        eigenschaften.add(eigenschaft);
    }
}
