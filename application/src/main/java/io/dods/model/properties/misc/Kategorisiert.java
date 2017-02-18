package io.dods.model.properties.misc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
public interface Kategorisiert {

    @JsonProperty("kategorie")
    @NotNull
    List<Aspect> getCategory();

    @JsonIgnore
    @NotNull
    default String getBezeichnungKategorie() {
        List<Aspect> kategorien = getCategory();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < kategorien.size(); i++) {
            Aspect aspect = kategorien.get(i);
            if (i > 0) stringBuilder.append('/');
            stringBuilder.append(aspect.getName());
        }

        return stringBuilder.toString();
    }
}
