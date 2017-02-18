package io.dods.model.properties.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.services.properties.check.Check;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.Nullable;

/**
 * @author Richard Gottschalk
 */
public interface Checking {

    @Nullable
    @ApiModelProperty
    @JsonProperty("probe")
    Check getCheck();

}
