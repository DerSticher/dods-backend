package io.dods.api.properties;

import io.dods.model.properties.Attribute;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface AttributeApi extends AbstractPropertyApi<Attribute> {

    @Override
    @ApiOperation(value = "a single Attribute or HTTP 404", response = Attribute.class)
    @RequestMapping(path = "eigenschaft/{id}", method = RequestMethod.GET)
    Attribute get(long id);

}
