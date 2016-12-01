package io.dods.api.attribute;

import io.dods.model.attribute.Sonderfertigkeit;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface SonderfertigkeitApi extends AbstractAttributeApi<Sonderfertigkeit> {

    @Override
    @ApiOperation(value = "a single Sonderfertigkeit or HTTP 404", response = Sonderfertigkeit.class)
    @RequestMapping(path = "sonderfertigkeit/{id}", method = RequestMethod.GET)
    Sonderfertigkeit get(long id);

}
