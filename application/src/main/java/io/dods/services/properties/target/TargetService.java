package io.dods.services.properties.target;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.properties.misc.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class TargetService implements NamedDodsDatabaseService<Long, Target, TargetRepository> {

    @Autowired
    private TargetRepository targetRepository;

    @Override
    public TargetRepository getRepository() {
        return targetRepository;
    }

    @Override
    public Target findByNameOrCreate(String name) {
        Target target = findFirstByName(name);

        if (target == null) {
            target = new Target(name);
            target = targetRepository.save(target);
        }

        return target;
    }
}
