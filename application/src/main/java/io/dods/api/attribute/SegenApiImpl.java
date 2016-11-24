package io.dods.api.attribute;

import io.dods.api.model.CreateSegen;
import io.dods.attributeService.segen.SegenService;
import io.dods.model.attribute.Segen;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class SegenApiImpl
        extends AbstractAttributeApiImpl<Segen, CreateSegen, SegenService>
        implements SegenApi {}
