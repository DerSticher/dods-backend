package io.dods.model.properties.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.exceptions.HasNoDefaultLevelException;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.Nullable;

/**
 * @author Richard Gottschalk
 */
public interface ImprovementCharted extends ApVar {

    @Nullable
    @ApiModelProperty
    @JsonProperty("kostentabelle")
    ImprovementChart getImprovementChart();

    @Override
    default int getAp(int level) {
        if (!isActivated(level)) return 0;
        ImprovementChart improvementChart = getImprovementChart();
        if (improvementChart != null) {
            return improvementChart.getAp(level);
        }
        return 0;
    }

    default int getDefaultLevel() throws HasNoDefaultLevelException {
        return 0;
    }

    @Override
    default boolean isActivated(int level) {
        ImprovementChart improvementChart = getImprovementChart();
        if (improvementChart != null) {
            return improvementChart.isActivated(level);
        } else {
            return level >= 0;
        }
    }
}
