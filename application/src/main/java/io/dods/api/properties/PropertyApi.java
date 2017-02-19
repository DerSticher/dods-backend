package io.dods.api.properties;

import io.dods.model.properties.Property;
import io.dods.model.rules.Dependency;
import io.dods.services.dependency.DependencyService;
import io.dods.services.properties.property.PropertyService;
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
public class PropertyApi {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private DependencyService dependencyService;

    @ApiOperation("a list of all available values")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the list of values")
    })
    @RequestMapping(path = "attribute", method = RequestMethod.GET)
    public List<Property> get(@RequestParam(value = "typ", required = false) String typ,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "includeSubcategories", required = false, defaultValue = "false")
                                              boolean includeSubcategories) {
        if (Objects.equals(typ, ValueConstants.DEFAULT_NONE)) typ = null;
        if (Objects.equals(name, ValueConstants.DEFAULT_NONE)) name = null;
        return propertyService.find(typ, name, includeSubcategories);
    }

    @ApiOperation("a single value")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the Property", response = Property.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "attribut/{id}", method = RequestMethod.GET)
    public Property get(@PathVariable("id") String id) {
        return propertyService.findById(Long.parseLong(id));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the List", response = Property.class, responseContainer = "List")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "attribut/{id}/subcategories", method = RequestMethod.GET)
    public List<Property> getSubcategories(@PathVariable("id") String id) {
        return propertyService.findSubcategoriesById(Long.parseLong(id));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the List", response = Dependency.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "attribut/{id}/abhangigkeit", method = RequestMethod.GET)
    public Dependency getDependencyById(@PathVariable("id") long id) {
        return dependencyService.findByEffectProperty(id);
    }

}
