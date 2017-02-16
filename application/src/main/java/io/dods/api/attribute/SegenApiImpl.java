package io.dods.api.attribute;

import io.dods.services.attribute.segen.SegenService;
import io.dods.model.attribute.Segen;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class SegenApiImpl
        extends AbstractAttributeApiImpl<Segen, SegenService>
        implements SegenApi {}
