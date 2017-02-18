package io.dods.model.properties.misc;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Richard Gottschalk
 */
public interface ApVar {

    int getAp(int level);

    default boolean isActivated(int level) {
        return true;
    }

}
