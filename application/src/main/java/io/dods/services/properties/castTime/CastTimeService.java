package io.dods.services.properties.castTime;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.properties.misc.CastTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class CastTimeService implements NamedDodsDatabaseService<Long, CastTime, CastTimeRepository> {

    @Autowired
    private CastTimeRepository castTimeRepository;

    @Override
    public CastTimeRepository getRepository() {
        return castTimeRepository;
    }

    @Override
    public CastTime findByNameOrCreate(String name) {
        CastTime castTime = findFirstByName(name);
        if (castTime == null) {
            castTime = new CastTime(name);
            castTime = castTimeRepository.save(castTime);
        }
        return castTime;
    }
}
