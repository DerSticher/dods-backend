package io.dods.api.properties;

import io.dods.model.properties.Property;
import io.dods.services.properties.AbstractPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Richard Gottschalk
 */
abstract class AbstractPropertyApiImpl<ATTRIBUT extends Property, SERVICE extends AbstractPropertyService<ATTRIBUT>>
        implements AbstractPropertyApi<ATTRIBUT> {

    @Autowired
    private SERVICE service;

    @Override
    public ATTRIBUT get(@PathVariable long id) {
        return service.findById(id);
    }

}
