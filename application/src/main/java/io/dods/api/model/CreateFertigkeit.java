package io.dods.api.model;

import io.dods.model.attribute.Fertigkeit;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Richard Gottschalk
 */
public class CreateFertigkeit {
    @ApiModelProperty(required = true, example = "1")
    private int probeId;

    @ApiModelProperty(required = true, example = "A")
    private String steigerungsfaktor;

    @ApiModelProperty(required = true)
    private Fertigkeit.Gruppe gruppe;

    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    private String name;

    public int getProbeId() {
        return probeId;
    }

    public void setProbeId(int probeId) {
        this.probeId = probeId;
    }

    public String getSteigerungsfaktor() {
        return steigerungsfaktor;
    }

    public void setSteigerungsfaktor(String steigerungsfaktor) {
        this.steigerungsfaktor = steigerungsfaktor;
    }

    public Fertigkeit.Gruppe getGruppe() {
        return gruppe;
    }

    public void setGruppe(Fertigkeit.Gruppe gruppe) {
        this.gruppe = gruppe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
