package io.dods.publikationService;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.publikation.Werk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class PublikationService implements NamedDodsDatabaseService<Long, Werk, PublikationRepository> {

    @Autowired
    private PublikationRepository publikationRepository;

    @Override
    public PublikationRepository getRepository() {
        return publikationRepository;
    }

    public Werk findByNameOrCreate(String name) {
        Werk werk = findFirstByName(name);
        if (werk == null) {
            werk = new Werk(name);
            werk = publikationRepository.save(werk);
        }
        return werk;
    }
}
