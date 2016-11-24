package io.dods.api.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
public class CreateLiturgie {
    @ApiModelProperty(required = true, example = "1")
    private int wirkungsdauerId;

    @ApiModelProperty(required = true, example = "1")
    private int probeId;

    @ApiModelProperty(required = true, example = "1")
    private int liturgieDauerId;

    @ApiModelProperty(required = true, example = "-1")
    private int reichweiteInSchritt;

    @ApiModelProperty(required = true, example = "2")
    private int nutzkosten;

    @ApiModelProperty(required = true, example = "A")
    private String steigerungsfaktor;

    @ApiModelProperty(required = true, example = "Fancy karma stuff is happening")
    private String wirkung;

    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    private String name;

    @ApiModelProperty(required = true, example = "[1,2,3]")
    private List<Long> zielkategorienIds;

    @ApiModelProperty(required = true, example = "[1,2,3]")
    private List<Long> verbreitungId;

    @ApiModelProperty(required = true, example = "[1,2]")
    private List<Long> aspektIds;

    public int getReichweiteInSchritt() {
        return reichweiteInSchritt;
    }

    public void setReichweiteInSchritt(int reichweiteInSchritt) {
        this.reichweiteInSchritt = reichweiteInSchritt;
    }

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

    public int getLiturgieDauerId() {
        return liturgieDauerId;
    }

    public void setLiturgieDauerId(int liturgieDauerId) {
        this.liturgieDauerId = liturgieDauerId;
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

    public List<Long> getZielkategorienIds() {
        return zielkategorienIds;
    }

    public void setZielkategorienIds(List<Long> zielkategorienIds) {
        this.zielkategorienIds = zielkategorienIds;
    }

    public List<Long> getVerbreitungId() {
        return verbreitungId;
    }

    public void setVerbreitungId(List<Long> verbreitungId) {
        this.verbreitungId = verbreitungId;
    }

    public List<Long> getAspektIds() {
        return aspektIds;
    }

    public void setAspektIds(List<Long> aspektIds) {
        this.aspektIds = aspektIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
