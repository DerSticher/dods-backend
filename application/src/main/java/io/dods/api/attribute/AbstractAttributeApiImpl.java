package io.dods.api.attribute;

import io.dods.attributeService.AbstractAttributService;
import io.dods.model.attribute.Attribut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Richard Gottschalk
 */
abstract class AbstractAttributeApiImpl<ATTRIBUT extends Attribut, CREATE, SERVICE extends AbstractAttributService<ATTRIBUT, CREATE>>
        implements AbstractAttributeApi<ATTRIBUT, CREATE> {

    @Autowired
    private SERVICE service;

    @Override
    public ATTRIBUT get(@PathVariable long id) {
        return service.findById(id);
    }

    @Override
    public ATTRIBUT create(@RequestBody CREATE create) {
        return service.save(create);
    }

}
