package io.dods.model.attribute.misc;

/**
 * @author Richard Gottschalk
 */
public interface Reichweite {

    int SELBST = -1;
    int BERUEHRUNG = -2;
    int SICHT = -3;

    int getReichweiteInSchritt();

    default int[] values() {
        return new int[] {SELBST, BERUEHRUNG, SICHT};
    }

    default boolean isReichweiteSelbst() {
        return getReichweiteInSchritt() == SELBST;
    }

    default boolean isReichweiteBeruehrung() {
        return getReichweiteInSchritt() == BERUEHRUNG;
    }

    default boolean isReichweiteSicht() {
        return getReichweiteInSchritt() == SICHT;
    }
}
