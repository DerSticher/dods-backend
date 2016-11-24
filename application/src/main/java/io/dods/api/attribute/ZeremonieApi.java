package io.dods.api.attribute;

import io.dods.api.model.CreateZeremonie;
import io.dods.model.attribute.Zeremonie;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface ZeremonieApi extends AbstractAttributeApi<Zeremonie, CreateZeremonie> {

    @Override
    @ApiOperation(value = "a single Zeremonie or HTTP 404", response = Zeremonie.class)
    @RequestMapping(path = "zeremonie/{id}", method = RequestMethod.GET)
    Zeremonie get(long id);

    @Override
    @ApiOperation(value = "adds a single Zeremonie to the database", response = Zeremonie.class)
    @RequestMapping(path = "zeremonie", method = RequestMethod.POST)
    Zeremonie create(CreateZeremonie create);

}
