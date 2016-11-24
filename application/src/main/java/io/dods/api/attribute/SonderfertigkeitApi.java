package io.dods.api.attribute;

import io.dods.api.model.CreateSonderfertigkeit;
import io.dods.model.attribute.Sonderfertigkeit;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface SonderfertigkeitApi extends AbstractAttributeApi<Sonderfertigkeit, CreateSonderfertigkeit> {

    @Override
    @ApiOperation(value = "a single Sonderfertigkeit or HTTP 404", response = Sonderfertigkeit.class)
    @RequestMapping(path = "sonderfertigkeit/{id}", method = RequestMethod.GET)
    Sonderfertigkeit get(long id);

    @Override
    @ApiOperation(value = "adds a single Sonderfertigkeit to the database", response = Sonderfertigkeit.class)
    @RequestMapping(path = "sonderfertigkeit", method = RequestMethod.POST)
    Sonderfertigkeit create(CreateSonderfertigkeit create);

}
