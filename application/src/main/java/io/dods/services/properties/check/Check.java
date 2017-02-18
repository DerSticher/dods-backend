package io.dods.services.properties.check;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Richard Gottschalk
 */
public interface Check {

    @JsonIgnore
    long getCheck1Id();

    @JsonIgnore
    long getCheck2Id();

    @JsonIgnore
    long getCheck3Id();

}
