package io.dods.api.dependency;

import io.dods.model.rules.Dependency;
import io.dods.services.dependency.DependencyService;
import io.dods.api.dependency.model.CreateDependency;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@RestController
public class DependencyApi {

    @Autowired
    private DependencyService dependencyService;

    @ApiOperation(value = "a List containing every Dependency", response = Dependency.class, responseContainer = "List")
    @RequestMapping(path = "abhangigkeiten", method = RequestMethod.GET)
    public List<Dependency> find(@RequestParam(value = "effektAttributId", required = false) Long effektAttributId) {
        if (effektAttributId != null) {
            List<Dependency> values = new ArrayList<>();
            values.add(dependencyService.findByEffectProperty(effektAttributId));
            return values;
        }
        return dependencyService.findAll();
    }

    @ApiOperation(value = "a single Dependency", response = Dependency.class)
    @RequestMapping(path = "abhangigkeit/{id}", method = RequestMethod.GET)
    public Dependency get(@PathVariable long id) {
        return dependencyService.findById(id);
    }

    @ApiOperation(value = "update a single Dependency")
    @RequestMapping(path = "abhangigkeit/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(@PathVariable long id, @RequestBody Dependency dependency) {
        dependencyService.update(id, dependency);
    }

    @ApiOperation(value = "deletes a single Dependency")
    @RequestMapping(path = "abhangigkeit/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        dependencyService.deleteById(id);
    }

    @ApiOperation(value = "create a single Dependency", response = Dependency.class)
    @RequestMapping(path = "abhangigkeiten", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Dependency post(@RequestBody CreateDependency createDependency) {
        return dependencyService.persist(createDependency);
    }
}
