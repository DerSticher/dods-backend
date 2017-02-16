package io.dods.api.abhangigkeit;

import io.dods.services.abhaengigkeit.AbhangigkeitService;
import io.dods.api.abhangigkeit.model.CreateAbhangigkeit;
import io.dods.model.regeln.Abhangigkeit;
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
public class AbhangigkeitApi {

    @Autowired
    private AbhangigkeitService abhangigkeitService;

    @ApiOperation(value = "a List containing every Abhangigkeit", response = Abhangigkeit.class, responseContainer = "List")
    @RequestMapping(path = "abhangigkeiten", method = RequestMethod.GET)
    public List<Abhangigkeit> find(@RequestParam(value = "effektAttributId", required = false) Long effektAttributId) {
        if (effektAttributId != null) {
            List<Abhangigkeit> values = new ArrayList<>();
            values.add(abhangigkeitService.findByEffektAttribut(effektAttributId));
            return values;
        }
        return abhangigkeitService.findAll();
    }

    @ApiOperation(value = "a single Abhangigkeit", response = Abhangigkeit.class)
    @RequestMapping(path = "abhangigkeit/{id}", method = RequestMethod.GET)
    public Abhangigkeit get(@PathVariable long id) {
        return abhangigkeitService.findById(id);
    }

    @ApiOperation(value = "update a single Abhangigkeit")
    @RequestMapping(path = "abhangigkeit/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(@PathVariable long id, @RequestBody Abhangigkeit abhangigkeit) {
        abhangigkeitService.update(id, abhangigkeit);
    }

    @ApiOperation(value = "deletes a single Abhangigkeit")
    @RequestMapping(path = "abhangigkeit/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        abhangigkeitService.deleteById(id);
    }

    @ApiOperation(value = "create a single Abhangigkeit", response = Abhangigkeit.class)
    @RequestMapping(path = "abhangigkeiten", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Abhangigkeit post(@RequestBody CreateAbhangigkeit createAbhangigkeit) {
        return abhangigkeitService.persist(createAbhangigkeit);
    }
}
