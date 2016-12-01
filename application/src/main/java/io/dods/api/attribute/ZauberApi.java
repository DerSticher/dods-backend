package io.dods.api.attribute;

import io.dods.model.attribute.Zauber;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface ZauberApi extends AbstractAttributeApi<Zauber> {

    @Override
    @ApiOperation(value = "a single Zauber or HTTP 404", response = Zauber.class)
    @RequestMapping(path = "zauber/{id}", method = RequestMethod.GET)
    Zauber get(long id);

}
