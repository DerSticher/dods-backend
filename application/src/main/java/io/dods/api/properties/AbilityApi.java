package io.dods.api.properties;

import io.dods.model.properties.Ability;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface AbilityApi extends AbstractPropertyApi<Ability> {

    @Override
    @ApiOperation(value = "a single Ability or HTTP 404", response = Ability.class)
    @RequestMapping(path = "eigenschaft/{id}", method = RequestMethod.GET)
    Ability get(long id);

}
