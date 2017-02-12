package io.dods.publikationService;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.publikation.Werk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class WerkService implements NamedDodsDatabaseService<Long, Werk, WerkRepository> {

    @Autowired
    private WerkRepository werkRepository;

    @Override
    public WerkRepository getRepository() {
        return werkRepository;
    }

    @Override
    public Werk findByNameOrCreate(String name) {
        Werk werk = getRepository().findFirstByName(name);
        if (werk == null) {
            werk = new Werk(name);
            werk = werkRepository.save(werk);
        }
        return werk;
    }
}
