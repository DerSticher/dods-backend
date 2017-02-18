package io.dods.api.properties;

import io.dods.model.properties.CombatTechnique;
import io.dods.services.properties.combatTechnique.CombatTechniqueService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class CombatApiImpl
        extends AbstractPropertyApiImpl<CombatTechnique, CombatTechniqueService>
        implements CombatApi {}
