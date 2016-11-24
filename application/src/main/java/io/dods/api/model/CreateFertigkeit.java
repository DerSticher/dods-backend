package io.dods.api.model;

import io.dods.attributeService.probe.HatProbeApi;
import io.dods.model.attribute.Fertigkeit;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Richard Gottschalk
 */
public class CreateFertigkeit implements HatProbeApi {
    @ApiModelProperty(required = true, example = "1")
    private int probeId;

    @ApiModelProperty(required = true, example = "A")
    private String steigerungsfaktor;

    @ApiModelProperty(required = true)
    private Fertigkeit.Gruppe gruppe;

    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    private String name;

    @ApiModelProperty(required = true, example = "1")
    private int teilprobe1Id;

    @ApiModelProperty(required = true, example = "1")
    private int teilprobe2Id;

    @ApiModelProperty(required = true, example = "8")
    private int teilprobe3Id;

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

    @Override
    public int getTeilprobe1Id() {
        return teilprobe1Id;
    }

    public void setTeilprobe1Id(int teilprobe1Id) {
        this.teilprobe1Id = teilprobe1Id;
    }

    @Override
    public int getTeilprobe2Id() {
        return teilprobe2Id;
    }

    public void setTeilprobe2Id(int teilprobe2Id) {
        this.teilprobe2Id = teilprobe2Id;
    }

    @Override
    public int getTeilprobe3Id() {
        return teilprobe3Id;
    }

    public void setTeilprobe3Id(int teilprobe3Id) {
        this.teilprobe3Id = teilprobe3Id;
    }
}
