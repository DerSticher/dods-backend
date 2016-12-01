package io.dods.api.attribute;

import io.dods.model.attribute.Vorteil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface VorteilApi extends AbstractAttributeApi<Vorteil> {

    @Override
    @ApiOperation(value = "a single Vorteil or HTTP 404", response = Vorteil.class)
    @RequestMapping(path = "vorteil/{id}", method = RequestMethod.GET)
    Vorteil get(long id);

}
