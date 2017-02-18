package io.dods.api.properties;

import io.dods.model.properties.SpecialAbility;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface SpecialAbilityApi extends AbstractPropertyApi<SpecialAbility> {

    @Override
    @ApiOperation(value = "a single SpecialAbility or HTTP 404", response = SpecialAbility.class)
    @RequestMapping(path = "sonderfertigkeit/{id}", method = RequestMethod.GET)
    SpecialAbility get(long id);

}
