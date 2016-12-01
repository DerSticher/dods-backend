package io.dods.api.model;

import io.dods.attributeService.probe.HatProbeApi;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
public class CreateZeremonie implements HatProbeApi {

    @ApiModelProperty(required = true, example = "-1")
    private int reichweiteId;

    @ApiModelProperty(required = true, example = "1")
    private int dauerId;

    @ApiModelProperty(required = true, example = "Fancy stuff is happening")
    private String wirkung;

    @ApiModelProperty(required = true, example = "1")
    private int wirkungsdauerId;

    @ApiModelProperty(required = true, example = "D")
    private String steigerungsfaktor;

    @ApiModelProperty(required = true)
    private List<Integer> zielkategorienId;

    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    private String name;

    @ApiModelProperty(required = true, example = "1")
    private int teilprobe1Id;

    @ApiModelProperty(required = true, example = "1")
    private int teilprobe2Id;

    @ApiModelProperty(required = true, example = "8")
    private int teilprobe3Id;

    public int getReichweiteId() {
        return reichweiteId;
    }

    public void setReichweiteId(int reichweiteId) {
        this.reichweiteId = reichweiteId;
    }

    public int getDauerId() {
        return dauerId;
    }

    public void setDauerId(int dauerId) {
        this.dauerId = dauerId;
    }

    public String getWirkung() {
        return wirkung;
    }

    public void setWirkung(String wirkung) {
        this.wirkung = wirkung;
    }

    public String getSteigerungsfaktor() {
        return steigerungsfaktor;
    }

    public void setSteigerungsfaktor(String steigerungsfaktor) {
        this.steigerungsfaktor = steigerungsfaktor;
    }

    public List<Integer> getZielkategorienId() {
        return zielkategorienId;
    }

    public void setZielkategorienId(List<Integer> zielkategorienId) {
        this.zielkategorienId = zielkategorienId;
    }

    public int getWirkungsdauerId() {
        return wirkungsdauerId;
    }

    public void setWirkungsdauerId(int wirkungsdauerId) {
        this.wirkungsdauerId = wirkungsdauerId;
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

    @Override
    public int getTeilprobe2Id() {
        return teilprobe2Id;
    }

    @Override
    public int getTeilprobe3Id() {
        return teilprobe3Id;
    }
}
