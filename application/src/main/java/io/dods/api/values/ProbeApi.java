package io.dods.api.values;

import io.dods.api.model.CreateProbe;
import io.dods.attributeService.probe.ProbeService;
import io.dods.model.attribute.misc.Probe;
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
public class ProbeApi {

    @Autowired
    private ProbeService probeService;

    @ApiOperation(value = "a list of all available values", responseContainer = "List", response = Probe.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the list of values")
    })
    @RequestMapping(path = "proben", method = RequestMethod.GET)
    public Iterable<Probe> get() {
        return probeService.findAll();
    }

    @ApiOperation("a single value")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the Probe", response = Probe.class)
    })
    @RequestMapping(path = "probe/{id}", method = RequestMethod.GET)
    public Probe get(@PathVariable("id") String id) {
        return probeService.findById(Long.parseLong(id));
    }

    @ApiOperation("adds a single value to the database")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "invalid Attribut for a Probe")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "proben", method = RequestMethod.POST)
    public Probe create(@RequestBody CreateProbe createProbe) {
        return probeService.create(createProbe);
    }
}
