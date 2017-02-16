package io.dods.services.attribute.wirkungsdauer;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.attribute.misc.Wirkungsdauer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class WirkungsdauerService implements NamedDodsDatabaseService<Long, Wirkungsdauer, WirkungsdauerRepository> {

    @Autowired
    private WirkungsdauerRepository wirkungsdauerRepository;

    @Override
    public WirkungsdauerRepository getRepository() {
        return wirkungsdauerRepository;
    }

    @Override
    public Wirkungsdauer findByNameOrCreate(String name) {
        Wirkungsdauer wirkungsdauer = findFirstByName(name);

        if (wirkungsdauer == null) {
            wirkungsdauer = new Wirkungsdauer(name);
            wirkungsdauer = wirkungsdauerRepository.save(wirkungsdauer);
        }

        return wirkungsdauer;
    }
}
