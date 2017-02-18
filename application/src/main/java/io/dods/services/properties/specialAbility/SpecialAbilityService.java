package io.dods.services.properties.specialAbility;

import io.dods.model.properties.SpecialAbility;
import io.dods.services.properties.AbstractPropertyRepository;
import io.dods.services.properties.AbstractPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class SpecialAbilityService extends AbstractPropertyService<SpecialAbility> {

    @Autowired
    private SpecialAbilityRepository specialAbilityRepository;

    @Override
    protected AbstractPropertyRepository<SpecialAbility> getRepository() {
        return specialAbilityRepository;
    }

}
