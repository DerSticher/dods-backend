package io.dods.api.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Richard Gottschalk
 */
public class CreateSonderfertigkeit {
    @ApiModelProperty(required = true, example = "2")
    private int ap;

    @ApiModelProperty(required = true, example = "false")
    private boolean isPassive;

    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    private String name;

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public boolean isPassive() {
        return isPassive;
    }

    public void setPassive(boolean passive) {
        isPassive = passive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
