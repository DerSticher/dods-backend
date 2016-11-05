package io.dods.model.helden;

import io.dods.model.Named;
import io.dods.model.attribute.Attribut;
import io.dods.model.attribute.misc.ApFix;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Held implements ApFix, Named {

    @Id
    @GeneratedValue
    private long id;

    private String name = "";

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CharakterEigenschaft> charaktereigenschaften = new ArrayList<>();

    @Nullable
    public CharakterEigenschaft getEigenschaft(Attribut attribut) {
        return charaktereigenschaften.parallelStream().filter(a -> a.isAttribut(attribut)).findFirst().orElse(null);
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
            currentCharakterEigenschaft.setActivated(charakterEigenschaft.isActivated());
        }
    }

    /**
     * Returns a list of {@link CharakterEigenschaft}en with {@link CharakterEigenschaft#isActivated()} == true
     * @return the lsit
     */
    public List<CharakterEigenschaft> getActivatedEigenschaften() {
        return charaktereigenschaften.stream()
                .filter(CharakterEigenschaft::isActivated)
                .collect(Collectors.toList());
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
}
