package io.dods.api.model;

import io.dods.attributeService.probe.HatProbeApi;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
public class CreateZauber implements HatProbeApi {

    @ApiModelProperty(required = true, example = "8")
    private int nutzkosten;

    @ApiModelProperty(required = true, example = "C")
    private String steigerungsfaktor;

    @ApiModelProperty(required = true, example = "10")
    private int reichweiteId;

    @ApiModelProperty(required = true, example = "Fancy magic stuff is happening")
    private String wirkung;

    @ApiModelProperty(required = true, example = "1")
    private int wirkungsdauerId;

    @ApiModelProperty(required = true, example = "1")
    private int zauberDauerId;

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

    public int getNutzkosten() {
        return nutzkosten;
    }

    public void setNutzkosten(int nutzkosten) {
        this.nutzkosten = nutzkosten;
    }

    public int getReichweiteId() {
        return reichweiteId;
    }

    public void setReichweiteId(int reichweiteId) {
        this.reichweiteId = reichweiteId;
    }

    public String getWirkung() {
        return wirkung;
    }

    public void setWirkung(String wirkung) {
        this.wirkung = wirkung;
    }

    public int getWirkungsdauerId() {
        return wirkungsdauerId;
    }

    public void setWirkungsdauerId(int wirkungsdauerId) {
        this.wirkungsdauerId = wirkungsdauerId;
    }

    public List<Integer> getZielkategorienId() {
        return zielkategorienId;
    }

    public void setZielkategorienId(List<Integer> zielkategorienId) {
        this.zielkategorienId = zielkategorienId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSteigerungsfaktor() {
        return steigerungsfaktor;
    }

    public void setSteigerungsfaktor(String steigerungsfaktor) {
        this.steigerungsfaktor = steigerungsfaktor;
    }

    public int getZauberDauerId() {
        return zauberDauerId;
    }

    public void setZauberDauerId(int zauberDauerId) {
        this.zauberDauerId = zauberDauerId;
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
