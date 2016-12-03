package io.dods.api.attribute;

import io.dods.attributeService.attribute.AttributeService;
import io.dods.model.attribute.Attribut;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author Richard Gottschalk
 */
@RestController
public class AttributeApi {

    @Autowired
    private AttributeService attributeService;

    @ApiOperation("a list of all available values")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the list of values")
    })
    @RequestMapping(path = "attribute", method = RequestMethod.GET)
    public Iterable<Attribut> get(@RequestParam(value = "typ", required = false) String typ,
                                  @RequestParam(value = "name", required = false) String name) {
        if (Objects.equals(typ, ValueConstants.DEFAULT_NONE)) typ = null;
        if (Objects.equals(name, ValueConstants.DEFAULT_NONE)) name = null;
        return attributeService.find(typ, name);
    }

    @ApiOperation("a single value")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the Attribut", response = Attribut.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "attribut/{id}", method = RequestMethod.GET)
    public Attribut get(@PathVariable("id") String id) {
        return attributeService.findById(Long.parseLong(id));
    }

}