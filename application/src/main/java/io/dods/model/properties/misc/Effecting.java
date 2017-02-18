package io.dods.model.properties.misc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Richard Gottschalk
 */
public interface Effecting {

    @Nullable
    @ApiModelProperty
    @JsonProperty("wirkungsdauer")
    Duration getDuration();

    @NotNull
    @JsonIgnore
    default String getBezeichnungWirkungsdauer() {
        Duration duration = getDuration();
        return duration == null ? "" : duration.getName();
    }

}
