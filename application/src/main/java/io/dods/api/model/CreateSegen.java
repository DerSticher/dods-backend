package io.dods.api.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
public class CreateSegen {
    @ApiModelProperty(required = true, example = "-3")
    private int reichweiteId;

    @ApiModelProperty(required = true, example = "1")
    private int wirkungsdauerId;

    @ApiModelProperty(required = true, example = "1")
    private int nutzkosten;

    @ApiModelProperty(required = true, example = "Fancy karma stuff is happening")
    private String wirkung;

    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    private String name;

    @ApiModelProperty(required = true)
    private List<Integer> zielkategorienIds;

    @ApiModelProperty(required = true)
    private List<Integer> aspektIds;

    public int getReichweiteId() {
        return reichweiteId;
    }

    public void setReichweiteId(int reichweiteId) {
        this.reichweiteId = reichweiteId;
    }

    public int getWirkungsdauerId() {
        return wirkungsdauerId;
    }

    public void setWirkungsdauerId(int wirkungsdauerId) {
        this.wirkungsdauerId = wirkungsdauerId;
    }

    public String getWirkung() {
        return wirkung;
    }

    public void setWirkung(String wirkung) {
        this.wirkung = wirkung;
    }

    public List<Integer> getZielkategorienIds() {
        return zielkategorienIds;
    }

    public void setZielkategorienIds(List<Integer> zielkategorienIds) {
        this.zielkategorienIds = zielkategorienIds;
    }

    public List<Integer> getAspektIds() {
        return aspektIds;
    }

    public void setAspektIds(List<Integer> aspektIds) {
        this.aspektIds = aspektIds;
    }

    public int getNutzkosten() {
        return nutzkosten;
    }

    public void setNutzkosten(int nutzkosten) {
        this.nutzkosten = nutzkosten;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
