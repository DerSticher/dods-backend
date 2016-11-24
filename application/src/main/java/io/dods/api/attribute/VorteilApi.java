package io.dods.api.attribute;

import io.dods.api.model.CreateVorteil;
import io.dods.model.attribute.Vorteil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface VorteilApi extends AbstractAttributeApi<Vorteil, CreateVorteil> {

    @Override
    @ApiOperation(value = "a single Vorteil or HTTP 404", response = Vorteil.class)
    @RequestMapping(path = "vorteil/{id}", method = RequestMethod.GET)
    Vorteil get(long id);

    @Override
    @ApiOperation(value = "adds a single Vorteil to the database", response = Vorteil.class)
    @RequestMapping(path = "vorteil", method = RequestMethod.POST)
    Vorteil create(CreateVorteil create);

}
