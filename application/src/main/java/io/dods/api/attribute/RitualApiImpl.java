package io.dods.api.attribute;

import io.dods.services.attribute.ritual.RitualService;
import io.dods.model.attribute.Ritual;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class RitualApiImpl
        extends AbstractAttributeApiImpl<Ritual, RitualService>
        implements RitualApi {}
