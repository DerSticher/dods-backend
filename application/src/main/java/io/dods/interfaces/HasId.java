package io.dods.interfaces;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Richard Gottschalk
 */
public interface HasId<ID extends Serializable> {

    @ApiModelProperty(notes = "will be set by server", readOnly = true)
    ID getId();
    void setId(ID id);

}
