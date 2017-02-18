package io.dods.model.properties.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Richard Gottschalk
 */
public interface ApFix {

    @ApiModelProperty
    @JsonProperty("ap")
    default int getAp() {
        return 1;
    }

}
