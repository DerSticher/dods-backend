package io.dods.api.attribute;

import io.dods.model.attribute.Kampftechnik;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface KampftechnikApi extends AbstractAttributeApi<Kampftechnik> {

    @Override
    @ApiOperation(value = "a single Kampftechnik or HTTP 404", response = Kampftechnik.class)
    @RequestMapping(path = "kampftechnik/{id}", method = RequestMethod.GET)
    Kampftechnik get(long id);

}
