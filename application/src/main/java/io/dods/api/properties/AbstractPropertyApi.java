package io.dods.api.properties;

import io.dods.model.properties.Property;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Richard Gottschalk
 */
@RequestMapping("attribute")
interface AbstractPropertyApi<T extends Property> {

    @ApiOperation(value = "a single value")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Property with given ID is another type")
    })
    @ResponseStatus(HttpStatus.OK)
    T get(long id);

}
