package io.dods.api.attribute;

import io.dods.api.model.CreateLiturgie;
import io.dods.model.attribute.Liturgie;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface LiturgieApi extends AbstractAttributeApi<Liturgie, CreateLiturgie> {

    @Override
    @ApiOperation(value = "a single Liturgie or HTTP 404", response = Liturgie.class)
    @RequestMapping(path = "liturgie/{id}", method = RequestMethod.GET)
    Liturgie get(long id);

    @Override
    @ApiOperation(value = "adds a single Liturgie to the database", response = Liturgie.class)
    @RequestMapping(path = "liturgie", method = RequestMethod.POST)
    Liturgie create(CreateLiturgie create);

}
