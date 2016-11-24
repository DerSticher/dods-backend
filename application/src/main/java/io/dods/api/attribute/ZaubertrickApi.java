package io.dods.api.attribute;

import io.dods.api.model.CreateZaubertrick;
import io.dods.model.attribute.Zaubertrick;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface ZaubertrickApi extends AbstractAttributeApi<Zaubertrick, CreateZaubertrick> {

    @Override
    @ApiOperation(value = "a single Zaubertrick or HTTP 404", response = Zaubertrick.class)
    @RequestMapping(path = "zaubertrick/{id}", method = RequestMethod.GET)
    Zaubertrick get(long id);

    @Override
    @ApiOperation(value = "adds a single Zaubertrick to the database", response = Zaubertrick.class)
    @RequestMapping(path = "zaubertrick", method = RequestMethod.POST)
    Zaubertrick create(CreateZaubertrick create);

}
