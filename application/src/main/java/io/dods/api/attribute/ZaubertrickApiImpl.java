package io.dods.api.attribute;

import io.dods.services.attribute.zaubertrick.ZaubertrickService;
import io.dods.model.attribute.Zaubertrick;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class ZaubertrickApiImpl
        extends AbstractAttributeApiImpl<Zaubertrick, ZaubertrickService>
        implements ZaubertrickApi {}
