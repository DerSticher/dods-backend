package io.dods.services.properties.species;

import io.dods.model.properties.Species;
import io.dods.services.properties.AbstractPropertyRepository;
import io.dods.services.properties.AbstractPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class SpeciesService extends AbstractPropertyService<Species> {

    @Autowired
    private SpeciesRepository speciesRepository;

    @Override
    protected AbstractPropertyRepository<Species> getRepository() {
        return speciesRepository;
    }

}
