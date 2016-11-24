package io.dods.api.model;

import io.dods.model.attribute.misc.Kostentabelle;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.NotNull;

/**
 * @author Richard Gottschalk
 */
public class CreateEigenschaft {

    @ApiModelProperty(required = true, example = "Fancy Attribute's Name")
    private String name;

    @ApiModelProperty(required = true, example = "E")
    private String steigerungsfaktor = Kostentabelle.E.name();

    public void setName(String name) {
        this.name = name;
    }

    public String getSteigerungsfaktor() {
        return steigerungsfaktor;
    }

    public void setSteigerungsfaktor(String steigerungsfaktor) {
        this.steigerungsfaktor = steigerungsfaktor;
    }

    public @NotNull Kostentabelle getKostentabelle() {
        return Kostentabelle.findOrThrow(steigerungsfaktor);
    }

    public String getName() {
        return name;
    }
}
