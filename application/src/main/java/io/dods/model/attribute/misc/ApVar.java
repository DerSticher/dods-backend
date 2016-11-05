package io.dods.model.attribute.misc;

/**
 * @author Richard Gottschalk
 */
public interface ApVar {

    int getAp(int level);

    default boolean isActivated(int level) {
        return true;
    }

}
