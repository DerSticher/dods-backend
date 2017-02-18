package io.dods.api.properties;

import io.dods.model.properties.CombatTechnique;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface CombatApi extends AbstractPropertyApi<CombatTechnique> {

    @Override
    @ApiOperation(value = "a single CombatTechnique or HTTP 404", response = CombatTechnique.class)
    @RequestMapping(path = "kampftechnik/{id}", method = RequestMethod.GET)
    CombatTechnique get(long id);

}
