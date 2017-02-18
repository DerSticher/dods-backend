package io.dods.model.properties.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
public interface Aiming {

    @NotNull
    @ApiModelProperty
    @JsonProperty("zielkategorien")
    List<Target> getTargets();

}
