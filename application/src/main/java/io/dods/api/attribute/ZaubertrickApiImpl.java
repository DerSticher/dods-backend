package io.dods.api.attribute;

import io.dods.api.model.CreateZaubertrick;
import io.dods.attributeService.zaubertrick.ZaubertrickService;
import io.dods.model.attribute.Zaubertrick;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class ZaubertrickApiImpl
        extends AbstractAttributeApiImpl<Zaubertrick, CreateZaubertrick, ZaubertrickService>
        implements ZaubertrickApi {}
