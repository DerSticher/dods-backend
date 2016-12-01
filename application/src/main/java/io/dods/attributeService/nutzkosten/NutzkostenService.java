package io.dods.attributeService.nutzkosten;

import io.dods.model.attribute.misc.Nutzkosten;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class NutzkostenService {

    @Autowired
    private NutzkostenRepository nutzkostenRepository;

    public Nutzkosten findById(long id) {
        return nutzkostenRepository.findById(id);
    }

    public Nutzkosten findByNameOrCreate(String name) {
        Nutzkosten aspekt = nutzkostenRepository.findByName(name);

        if (aspekt == null) {
            aspekt = new Nutzkosten(name);
            aspekt = nutzkostenRepository.save(aspekt);
        }

        return aspekt;
    }

}
