package io.dods.api.hero;

import io.dods.api.hero.model.HeroUpdate;
import io.dods.model.heroes.Hero;
import io.dods.services.held.HeroService;
import io.dods.model.properties.Property;
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
public class HeroApi {

    @Autowired
    private HeroService heroService;

    @ApiOperation("a single hero")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the hero")
    })
    @RequestMapping(path = "held/{id}", method = RequestMethod.GET)
    public Hero get(@PathVariable("id") String id) {
        return heroService.findById(id);
    }

    @ApiOperation("a list of heroes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "a List of heroes")
    })
    @RequestMapping(path = "held", method = RequestMethod.GET)
    public Iterable<Hero> getAll() {
        return heroService.findAll();
    }

    @ApiOperation("creates a new hero")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the Hero", response = Property.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "held", method = RequestMethod.POST)
    public Hero post() {
        return heroService.createNew();
    }

    @ApiOperation("deletes a heroes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the Hero", response = Property.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "held/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        heroService.delete(id);
    }

    @ApiOperation("updates a hero")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the Hero", response = Property.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "held/{id}", method = RequestMethod.PUT)
    public void put(@PathVariable("id") String id, @RequestBody HeroUpdate update) {
        heroService.update(id, update);
    }
}
