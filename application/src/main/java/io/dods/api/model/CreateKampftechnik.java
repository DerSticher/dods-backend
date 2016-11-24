package io.dods.api.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Richard Gottschalk
 */
public class CreateKampftechnik {

    @ApiModelProperty(required = true, example = "1")
    private int leiteigenschaftId;

    @ApiModelProperty(required = true, example = "A")
    private String steigerungsfaktor;

    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    private String name;

    public int getLeiteigenschaftId() {
        return leiteigenschaftId;
    }

    public void setLeiteigenschaftId(int leiteigenschaftId) {
        this.leiteigenschaftId = leiteigenschaftId;
    }

    public String getSteigerungsfaktor() {
        return steigerungsfaktor;
    }

    public void setSteigerungsfaktor(String steigerungsfaktor) {
        this.steigerungsfaktor = steigerungsfaktor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
