package io.dods.api.properties;

import io.dods.model.properties.LiturgicalChant;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface LiturgicalChantApi extends AbstractPropertyApi<LiturgicalChant> {

    @Override
    @ApiOperation(value = "a single LiturgicalChant or HTTP 404", response = LiturgicalChant.class)
    @RequestMapping(path = "liturgie/{id}", method = RequestMethod.GET)
    LiturgicalChant get(long id);

}
