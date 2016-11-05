package io.dods.model.attribute.misc;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
public interface Kategorisiert {

    @NotNull
    List<Aspekt> getKategorie();

    @NotNull
    default String getBezeichnungKategorie() {
        List<Aspekt> kategorien = getKategorie();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < kategorien.size(); i++) {
            Aspekt aspekt = kategorien.get(i);
            if (i > 0) stringBuilder.append('/');
            stringBuilder.append(aspekt.getName());
        }

        return stringBuilder.toString();
    }
}
