package io.dods.api.attribute;

import io.dods.model.attribute.Ritual;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface RitualApi extends AbstractAttributeApi<Ritual> {

    @Override
    @ApiOperation(value = "a single Ritual or HTTP 404", response = Ritual.class)
    @RequestMapping(path = "ritual/{id}", method = RequestMethod.GET)
    Ritual get(long id);

}
