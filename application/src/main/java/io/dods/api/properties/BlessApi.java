package io.dods.api.properties;

import io.dods.model.properties.Bless;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface BlessApi extends AbstractPropertyApi<Bless> {

    @Override
    @ApiOperation(value = "a single Bless or HTTP 404", response = Bless.class)
    @RequestMapping(path = "segen/{id}", method = RequestMethod.GET)
    Bless get(long id);

}
