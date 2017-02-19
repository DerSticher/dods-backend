package io.dods.api.properties;

import io.dods.model.properties.Attribute;
import io.dods.services.properties.ability.AttributeService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class AttributeApiImpl
        extends AbstractPropertyApiImpl<Attribute, AttributeService>
        implements AttributeApi {}
