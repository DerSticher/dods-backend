package io.dods.api.attribute;

import io.dods.services.attribute.AbstractAttributService;
import io.dods.model.attribute.Attribut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Richard Gottschalk
 */
abstract class AbstractAttributeApiImpl<ATTRIBUT extends Attribut, SERVICE extends AbstractAttributService<ATTRIBUT>>
        implements AbstractAttributeApi<ATTRIBUT> {

    @Autowired
    private SERVICE service;

    @Override
    public ATTRIBUT get(@PathVariable long id) {
        return service.findById(id);
    }

}
