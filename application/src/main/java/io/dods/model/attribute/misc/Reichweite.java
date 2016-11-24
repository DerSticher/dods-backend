package io.dods.model.attribute.misc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Richard Gottschalk
 */
public interface Reichweite {

    int SELBST = -1;
    int BERUEHRUNG = -2;
    int SICHT = -3;

    @JsonProperty
    int getReichweiteInSchritt();

    @JsonIgnore
    default int[] values() {
        return new int[] {SELBST, BERUEHRUNG, SICHT};
    }

    @JsonProperty
    default boolean isReichweiteSelbst() {
        return getReichweiteInSchritt() == SELBST;
    }

    @JsonProperty
    default boolean isReichweiteBeruehrung() {
        return getReichweiteInSchritt() == BERUEHRUNG;
    }

    @JsonProperty
    default boolean isReichweiteSicht() {
        return getReichweiteInSchritt() == SICHT;
    }
}
