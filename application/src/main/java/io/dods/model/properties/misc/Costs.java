package io.dods.model.properties.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Richard Gottschalk
 */
public interface Costs {

    @ApiModelProperty
    @JsonProperty("nutzkosten")
    default Cost getCost() {
        return null;
    }

}
