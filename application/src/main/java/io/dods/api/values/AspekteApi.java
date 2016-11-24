package io.dods.api.values;

import io.dods.attributeService.aspekte.AspektService;
import io.dods.model.attribute.misc.Aspekt;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Richard Gottschalk
 */
@RestController
@RequestMapping("values")
public class AspekteApi {

    @Autowired
    private AspektService aspektService;

    @ApiOperation(value = "a list of all available values", responseContainer = "List", response = Aspekt.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the list of values")
    })
    @RequestMapping(path = "aspekte", method = RequestMethod.GET)
    public Iterable<Aspekt> get() {
        return aspektService.findAll();
    }

    @ApiOperation("a single value")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the Aspekt", response = Aspekt.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "aspekt/{id}", method = RequestMethod.GET)
    public Aspekt get(@PathVariable("id") String id) {
        return aspektService.findById(Long.parseLong(id));
    }

    @ApiOperation("adds a single Aspekt to the database")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "referenced value not found")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "aspekte", method = RequestMethod.POST)
    public Aspekt create(@RequestBody Aspekt aspekt) {
        return aspektService.persist(aspekt);
    }
}
