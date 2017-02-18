package io.dods.api.properties;

import io.dods.services.properties.specialAbility.SpecialAbilityService;
import io.dods.model.properties.SpecialAbility;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class SpecialAbilityApiImpl
        extends AbstractPropertyApiImpl<SpecialAbility, SpecialAbilityService>
        implements SpecialAbilityApi {}
