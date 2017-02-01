package io.dods.attributeService.nutzkosten;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.attribute.misc.Nutzkosten;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class NutzkostenService implements NamedDodsDatabaseService<Long, Nutzkosten, NutzkostenRepository> {

    @Autowired
    private NutzkostenRepository nutzkostenRepository;

    @Override
    public NutzkostenRepository getRepository() {
        return nutzkostenRepository;
    }

    public Nutzkosten findById(long id) {
        return nutzkostenRepository.findById(id);
    }

    public Nutzkosten findByNameOrCreate(String name) {
        Nutzkosten aspekt = findFirstByName(name);

        if (aspekt == null) {
            aspekt = new Nutzkosten(name);
            aspekt = nutzkostenRepository.save(aspekt);
        }

        return aspekt;
    }
}
