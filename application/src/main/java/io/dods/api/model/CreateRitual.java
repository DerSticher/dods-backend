package io.dods.api.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
public class CreateRitual {

    @ApiModelProperty(required = true, example = "1")
    private int wirkungsdauerId;

    @ApiModelProperty(required = true, example = "1")
    private int probeId;

    @ApiModelProperty(required = true, example = "1")
    private int ritualDauerId;

    @ApiModelProperty(required = true, example = "-2")
    private int reichweiteInSchritt;

    @ApiModelProperty(required = true, example = "2")
    private int nutzkosten;

    @ApiModelProperty(required = true, example = "B")
    private String steigerungsfaktor;

    @ApiModelProperty(required = true, example = "Fancy stuff is happening")
    private String wirkung;

    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    private String name;

    @ApiModelProperty(required = true)
    private List<Long> zielkategorienIds;

    public int getWirkungsdauerId() {
        return wirkungsdauerId;
    }

    public void setWirkungsdauerId(int wirkungsdauerId) {
        this.wirkungsdauerId = wirkungsdauerId;
    }

    public int getProbeId() {
        return probeId;
    }

    public void setProbeId(int probeId) {
        this.probeId = probeId;
    }

    public int getRitualDauerId() {
        return ritualDauerId;
    }

    public void setRitualDauerId(int ritualDauerId) {
        this.ritualDauerId = ritualDauerId;
    }

    public int getReichweiteInSchritt() {
        return reichweiteInSchritt;
    }

    public void setReichweiteInSchritt(int reichweiteInSchritt) {
        this.reichweiteInSchritt = reichweiteInSchritt;
    }

    public int getNutzkosten() {
        return nutzkosten;
    }

    public void setNutzkosten(int nutzkosten) {
        this.nutzkosten = nutzkosten;
    }

    public String getSteigerungsfaktor() {
        return steigerungsfaktor;
    }

    public void setSteigerungsfaktor(String steigerungsfaktor) {
        this.steigerungsfaktor = steigerungsfaktor;
    }

    public String getWirkung() {
        return wirkung;
    }

    public void setWirkung(String wirkung) {
        this.wirkung = wirkung;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getZielkategorienIds() {
        return zielkategorienIds;
    }

    public void setZielkategorienIds(List<Long> zielkategorienIds) {
        this.zielkategorienIds = zielkategorienIds;
    }
}
