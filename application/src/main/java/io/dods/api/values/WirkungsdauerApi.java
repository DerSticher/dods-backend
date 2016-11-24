package io.dods.api.values;

import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.model.attribute.misc.Wirkungsdauer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Richard Gottschalk
 */
@RestController
@RequestMapping("values")
public class WirkungsdauerApi {

    @Autowired
    private WirkungsdauerService wirkungsdauerService;

    @ApiOperation(value = "a list of all available values", responseContainer = "List", response = Wirkungsdauer.class)
    @RequestMapping(path = "wirkungsdauer", method = RequestMethod.GET)
    public Iterable<Wirkungsdauer> get() {
        return wirkungsdauerService.findAll();
    }

    @ApiOperation("a single value")
    @RequestMapping(path = "wirkungsdauer/{id}", method = RequestMethod.GET)
    public Wirkungsdauer get(@PathVariable("id") String id) {
        return wirkungsdauerService.findById(Long.parseLong(id));
    }

    @ApiOperation("adds a single value to the database")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "wirkungsdauer", method = RequestMethod.POST)
    public Wirkungsdauer create(@RequestBody Wirkungsdauer wirkungsdauer) {
        return wirkungsdauerService.persist(wirkungsdauer);
    }
}
