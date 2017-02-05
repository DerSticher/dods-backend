package io.dods.api.held;

import io.dods.api.held.model.HeldUpdate;
import io.dods.heldService.HeldService;
import io.dods.model.attribute.Attribut;
import io.dods.model.helden.Held;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Richard Gottschalk
 */
@RequestMapping
@RestController
public class HeldApi {

    @Autowired
    private HeldService heldService;

    @ApiOperation("a signle hero")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the hero")
    })
    @RequestMapping(path = "hero/{id}", method = RequestMethod.GET)
    public Held get(@PathVariable("id") String id) {
        return heldService.findById(id);
    }

    @ApiOperation("a list of hero")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "a List of heroes")
    })
    @RequestMapping(path = "hero", method = RequestMethod.GET)
    public Iterable<Held> getAll() {
        return heldService.findAll();
    }

    @ApiOperation("creates a new hero")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the Hero", response = Attribut.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "hero", method = RequestMethod.POST)
    public Held post() {
        return heldService.createNew();
    }

    @ApiOperation("deletes a hero")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the Hero", response = Attribut.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "hero/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        heldService.delete(id);
    }

    @ApiOperation("update a hero")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the Hero", response = Attribut.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "hero/{id}", method = RequestMethod.PUT)
    public void put(@PathVariable("id") String id, @RequestBody HeldUpdate update) {
        heldService.update(id, update);
    }
}
