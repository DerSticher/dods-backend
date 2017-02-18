package io.dods.api.properties;

import io.dods.model.properties.Cantrip;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface CantripApi extends AbstractPropertyApi<Cantrip> {

    @Override
    @ApiOperation(value = "a single Cantrip or HTTP 404", response = Cantrip.class)
    @RequestMapping(path = "zaubertrick/{id}", method = RequestMethod.GET)
    Cantrip get(long id);

}
