package io.dods.api.properties;

import io.dods.model.properties.Spell;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface SpellApi extends AbstractPropertyApi<Spell> {

    @Override
    @ApiOperation(value = "a single Spell or HTTP 404", response = Spell.class)
    @RequestMapping(path = "zauber/{id}", method = RequestMethod.GET)
    Spell get(long id);

}
