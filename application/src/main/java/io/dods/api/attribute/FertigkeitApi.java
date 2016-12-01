package io.dods.api.attribute;

import io.dods.model.attribute.Fertigkeit;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface FertigkeitApi extends AbstractAttributeApi<Fertigkeit> {

    @Override
    @ApiOperation(value = "a single Fertigkeit or HTTP 404", response = Fertigkeit.class)
    @RequestMapping(path = "fertigkeit/{id}", method = RequestMethod.GET)
    Fertigkeit get(long id);

}
