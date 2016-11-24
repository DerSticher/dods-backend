package io.dods.api.attribute;

import io.dods.api.exceptions.ApiNotImplementedException;
import io.dods.model.attribute.Attribut;
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
interface AbstractAttributeApi<T extends Attribut, C> {

    @ApiOperation(value = "a single value")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Attribut with given ID is another type")
    })
    @ResponseStatus(HttpStatus.OK)
    default T get(long id) {
        throw new ApiNotImplementedException();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "referenced value not found")
    })
    @ResponseStatus(HttpStatus.CREATED)
    default T create(C create) {
        throw new ApiNotImplementedException();
    }

}
