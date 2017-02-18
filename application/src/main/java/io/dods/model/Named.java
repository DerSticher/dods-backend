package io.dods.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.NotNull;

/**
 * @author Richard Gottschalk
 */
public interface Named {

    @NotNull
    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    @JsonProperty("name")
    String getName();
}
