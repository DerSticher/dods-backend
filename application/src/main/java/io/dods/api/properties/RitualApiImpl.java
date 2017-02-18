package io.dods.api.properties;

import io.dods.services.properties.ritual.RitualService;
import io.dods.model.properties.Ritual;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class RitualApiImpl
        extends AbstractPropertyApiImpl<Ritual, RitualService>
        implements RitualApi {}
