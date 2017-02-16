package io.dods.services.attribute.dauer;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.attribute.misc.Dauer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class DauerService implements NamedDodsDatabaseService<Long, Dauer, DauerRepository> {

    @Autowired
    private DauerRepository dauerRepository;

    @Override
    public DauerRepository getRepository() {
        return dauerRepository;
    }

    @Override
    public Dauer findByNameOrCreate(String name) {
        Dauer dauer = findFirstByName(name);
        if (dauer == null) {
            dauer = new Dauer(name);
            dauer = dauerRepository.save(dauer);
        }
        return dauer;
    }
}
