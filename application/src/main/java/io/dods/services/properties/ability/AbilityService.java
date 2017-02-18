package io.dods.services.properties.ability;

import io.dods.model.properties.Ability;
import io.dods.services.properties.AbstractPropertyRepository;
import io.dods.services.properties.AbstractPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class AbilityService extends AbstractPropertyService<Ability> {

    @Autowired
    private AbilityRepository abilityRepository;

    @Override
    protected AbstractPropertyRepository<Ability> getRepository() {
        return abilityRepository;
    }

}
