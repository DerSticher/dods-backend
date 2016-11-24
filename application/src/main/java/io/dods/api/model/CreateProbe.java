package io.dods.api.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
public class CreateProbe {

    @ApiModelProperty(required = true)
    private List<Integer> attributIds;

    public List<Integer> getAttributIds() {
        return attributIds;
    }

    public void setAttributIds(List<Integer> attributIds) {
        this.attributIds = attributIds;
    }
}
