package io.dods.api.attribute;

import io.dods.model.attribute.Eigenschaft;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface EigenschaftApi extends AbstractAttributeApi<Eigenschaft> {

    @Override
    @ApiOperation(value = "a single Eigenschaft or HTTP 404", response = Eigenschaft.class)
    @RequestMapping(path = "eigenschaft/{id}", method = RequestMethod.GET)
    Eigenschaft get(long id);

}
