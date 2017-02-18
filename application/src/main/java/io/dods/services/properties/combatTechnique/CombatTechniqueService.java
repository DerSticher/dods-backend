package io.dods.services.properties.combatTechnique;

import io.dods.model.properties.CombatTechnique;
import io.dods.services.properties.AbstractPropertyRepository;
import io.dods.services.properties.AbstractPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class CombatTechniqueService extends AbstractPropertyService<CombatTechnique> {

    @Autowired
    private CombatTechniqueRepository combatTechniqueRepository;

    @Override
    protected AbstractPropertyRepository<CombatTechnique> getRepository() {
        return combatTechniqueRepository;
    }

}
