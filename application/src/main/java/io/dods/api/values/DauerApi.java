package io.dods.api.values;

import io.dods.attributeService.dauer.DauerService;
import io.dods.model.attribute.misc.Dauer;
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
public class DauerApi {

    @Autowired
    private DauerService dauerService;

    @ApiOperation(value = "a list of all available values", responseContainer = "List", response = Dauer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the list of values")
    })
    @RequestMapping(path = "dauer", method = RequestMethod.GET)
    public Iterable<Dauer> get() {
        return dauerService.findAll();
    }

    @ApiOperation(value = "a single value")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the Dauer", response = Dauer.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "dauer/{id}", method = RequestMethod.GET)
    public Dauer get(@PathVariable("id") String id) {
        return dauerService.findById(Long.parseLong(id));
    }

    @ApiOperation(value = "adds a single Aspekt to the database", response = Dauer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "referenced value not found")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "dauer", method = RequestMethod.POST)
    public Dauer create(@RequestBody Dauer dauer) {
        return dauerService.persist(dauer);
    }
}
