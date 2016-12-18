package io.dods.api.abhangigkeit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Richard Gottschalk
 */
public class CreateAbhangigkeit {

    @JsonProperty("bedingung")
    private CreateBedingung createBedingung;

    @JsonProperty("effekt")
    private CreateEffekt createEffekt;

    @JsonProperty("regelwerkId")
    private int regelwerkId;

    public CreateBedingung getCreateBedingung() {
        return createBedingung;
    }

    public void setCreateBedingung(CreateBedingung createBedingung) {
        this.createBedingung = createBedingung;
    }

    public CreateEffekt getCreateEffekt() {
        return createEffekt;
    }

    public void setCreateEffekt(CreateEffekt createEffekt) {
        this.createEffekt = createEffekt;
    }

    public int getRegelwerkId() {
        return regelwerkId;
    }

    public void setRegelwerkId(int regelwerkId) {
        this.regelwerkId = regelwerkId;
    }
}
