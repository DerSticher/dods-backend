package io.dods.api.attribute;

import io.dods.model.attribute.Zeremonie;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface ZeremonieApi extends AbstractAttributeApi<Zeremonie> {

    @Override
    @ApiOperation(value = "a single Zeremonie or HTTP 404", response = Zeremonie.class)
    @RequestMapping(path = "zeremonie/{id}", method = RequestMethod.GET)
    Zeremonie get(long id);

}
