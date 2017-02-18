package io.dods.services.properties.aspect;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.properties.misc.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class AspectService implements NamedDodsDatabaseService<Long, Aspect, AspectRepository> {

    @Autowired
    private AspectRepository aspectRepository;

    @Override
    public AspectRepository getRepository() {
        return aspectRepository;
    }

    @Override
    public Aspect findByNameOrCreate(String name) {
        Aspect aspect = findFirstByName(name);

        if (aspect == null) {
            aspect = new Aspect(name);
            aspect = aspectRepository.save(aspect);
        }

        return aspect;
    }
}
