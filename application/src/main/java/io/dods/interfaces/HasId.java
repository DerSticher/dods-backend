package io.dods.interfaces;

import java.io.Serializable;

/**
 * @author Richard Gottschalk
 */
public interface HasId<ID extends Serializable> {

    ID getId();
    void setId(ID id);

}
