package io.dods.api.attribute;

import io.dods.api.model.CreateSegen;
import io.dods.model.attribute.Segen;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface SegenApi extends AbstractAttributeApi<Segen, CreateSegen> {

    @Override
    @ApiOperation(value = "a single Segen or HTTP 404", response = Segen.class)
    @RequestMapping(path = "segen/{id}", method = RequestMethod.GET)
    Segen get(long id);

    @Override
    @ApiOperation(value = "adds a single Segen to the database", response = Segen.class)
    @RequestMapping(path = "segen", method = RequestMethod.POST)
    Segen create(CreateSegen create);

}
