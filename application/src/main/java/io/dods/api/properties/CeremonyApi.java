package io.dods.api.properties;

import io.dods.model.properties.Ceremony;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface CeremonyApi extends AbstractPropertyApi<Ceremony> {

    @Override
    @ApiOperation(value = "a single Ceremony or HTTP 404", response = Ceremony.class)
    @RequestMapping(path = "zeremonie/{id}", method = RequestMethod.GET)
    Ceremony get(long id);

}
