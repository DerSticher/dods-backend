package io.dods.model.attribute.misc;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
public interface Zielend {

    @NotNull
    List<Zielkategorie> getZielkategorien();

}
