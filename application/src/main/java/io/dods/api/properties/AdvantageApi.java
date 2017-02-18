package io.dods.api.properties;

import io.dods.model.properties.Advantage;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface AdvantageApi extends AbstractPropertyApi<Advantage> {

    @Override
    @ApiOperation(value = "a single Advantage or HTTP 404", response = Advantage.class)
    @RequestMapping(path = "vorteil/{id}", method = RequestMethod.GET)
    Advantage get(long id);

}
