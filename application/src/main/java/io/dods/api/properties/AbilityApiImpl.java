package io.dods.api.properties;

import io.dods.model.properties.Ability;
import io.dods.services.properties.ability.AbilityService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class AbilityApiImpl
        extends AbstractPropertyApiImpl<Ability, AbilityService>
        implements AbilityApi {}
