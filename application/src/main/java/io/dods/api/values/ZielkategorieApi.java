package io.dods.api.values;

import io.dods.attributeService.zielkategorie.ZielkategorieService;
import io.dods.model.attribute.misc.Zielkategorie;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Richard Gottschalk
 */
@RestController
@RequestMapping("values")
public class ZielkategorieApi {

    @Autowired
    private ZielkategorieService zielkategorieService;

    @ApiOperation(value = "a list of all available values", responseContainer = "List", response = Zielkategorie.class)
    @RequestMapping(path = "zielkategorien", method = RequestMethod.GET)
    public Iterable<Zielkategorie> get() {
        return zielkategorieService.findAll();
    }

    @ApiOperation("a single value")
    @RequestMapping(path = "zielkategorie/{id}", method = RequestMethod.GET)
    public Zielkategorie get(@PathVariable("id") String id) {
        return zielkategorieService.findById(Long.parseLong(id));
    }

    @ApiOperation("adds a single value to the database")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "zielkategorien", method = RequestMethod.POST)
    public Zielkategorie create(@RequestBody Zielkategorie zielkategorie) {
        return zielkategorieService.persist(zielkategorie);
    }
}
