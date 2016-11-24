package io.dods.api.attribute;

import io.dods.api.model.CreateZauber;
import io.dods.model.attribute.Zauber;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface ZauberApi extends AbstractAttributeApi<Zauber, CreateZauber> {

    @Override
    @ApiOperation(value = "a single Zauber or HTTP 404", response = Zauber.class)
    @RequestMapping(path = "zauber/{id}", method = RequestMethod.GET)
    Zauber get(long id);

    @Override
    @ApiOperation(value = "adds a single Zauber to the database", response = Zauber.class)
    @RequestMapping(path = "zauber", method = RequestMethod.POST)
    Zauber create(CreateZauber create);

}
