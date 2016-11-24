package io.dods.api.attribute;

import io.dods.api.model.CreateEigenschaft;
import io.dods.model.attribute.Eigenschaft;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface EigenschaftApi extends AbstractAttributeApi<Eigenschaft, CreateEigenschaft> {

    @Override
    @ApiOperation(value = "a single Eigenschaft or HTTP 404", response = Eigenschaft.class)
    @RequestMapping(path = "eigenschaft/{id}", method = RequestMethod.GET)
    Eigenschaft get(long id);

    @Override
    @ApiOperation(value = "adds a single Eigenschaft to the database", response = Eigenschaft.class)
    @RequestMapping(path = "eigenschaft", method = RequestMethod.POST)
    Eigenschaft create(CreateEigenschaft create);

}
