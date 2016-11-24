package io.dods.api.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
public class CreateZaubertrick {

    @ApiModelProperty(required = true, example = "8")
    private int reichweiteInSchritt;

    @ApiModelProperty(required = true)
    private List<Integer> zielkategorienId;

    @ApiModelProperty(required = true, example = "1")
    private int wirkungsdauerId;

    @ApiModelProperty(required = true, example = "Fancy magic stuff is happening")
    private String wirkung;

    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    private String name;

    public int getReichweiteInSchritt() {
        return reichweiteInSchritt;
    }

    public void setReichweiteInSchritt(int reichweiteInSchritt) {
        this.reichweiteInSchritt = reichweiteInSchritt;
    }

    public List<Integer> getZielkategorienId() {
        return zielkategorienId;
    }

    public void getZielkategorienId(List<Integer> zielkategorienListe) {
        this.zielkategorienId = zielkategorienListe;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
