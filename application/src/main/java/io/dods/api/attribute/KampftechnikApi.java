package io.dods.api.attribute;

import io.dods.api.model.CreateKampftechnik;
import io.dods.model.attribute.Kampftechnik;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface KampftechnikApi extends AbstractAttributeApi<Kampftechnik, CreateKampftechnik> {

    @Override
    @ApiOperation(value = "a single Kampftechnik or HTTP 404", response = Kampftechnik.class)
    @RequestMapping(path = "kampftechnik/{id}", method = RequestMethod.GET)
    Kampftechnik get(long id);

    @Override
    @ApiOperation(value = "adds a single Kampftechnik to the database", response = Kampftechnik.class)
    @RequestMapping(path = "kampftechnik", method = RequestMethod.POST)
    Kampftechnik create(CreateKampftechnik create);

}
