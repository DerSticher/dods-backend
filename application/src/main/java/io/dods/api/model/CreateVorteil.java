package io.dods.api.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Richard Gottschalk
 */
public class CreateVorteil {
    @ApiModelProperty(required = true, example = "10")
    private int ap;

    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    private String name;

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
