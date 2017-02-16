package io.dods.api.attribute;

import io.dods.services.abhaengigkeit.AbhangigkeitService;
import io.dods.services.attribute.attribute.AttributeService;
import io.dods.model.attribute.Attribut;
import io.dods.model.regeln.Abhangigkeit;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Richard Gottschalk
 */
@RestController
public class AttributeApi {

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private AbhangigkeitService abhangigkeitService;

    @ApiOperation("a list of all available values")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the list of values")
    })
    @RequestMapping(path = "attribute", method = RequestMethod.GET)
    public List<Attribut> get(@RequestParam(value = "typ", required = false) String typ,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "includeSubcategories", required = false, defaultValue = "false")
                                              boolean includeSubcategories) {
        if (Objects.equals(typ, ValueConstants.DEFAULT_NONE)) typ = null;
        if (Objects.equals(name, ValueConstants.DEFAULT_NONE)) name = null;
        return attributeService.find(typ, name, includeSubcategories);
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

    @ApiOperation("a list of subcategories if available. Or an empty list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the List", response = Attribut.class, responseContainer = "List")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "attribut/{id}/subcategories", method = RequestMethod.GET)
    public List<Attribut> getSubcategories(@PathVariable("id") String id) {
        return attributeService.findSubcategoriesById(Long.parseLong(id));
    }

    @ApiOperation("a list of subcategories if available. Or an empty list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the List", response = Attribut.class, responseContainer = "List")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "attribut/{id}/abhangigkeit", method = RequestMethod.GET)
    public Abhangigkeit getAbhangigkeit(@PathVariable("id") long id) {
        return abhangigkeitService.findByEffektAttribut(id);
    }

}
