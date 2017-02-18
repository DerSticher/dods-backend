package io.dods.api.properties;

import io.dods.model.properties.Spell;
import io.dods.services.properties.spell.SpellService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class SpellApiImpl
        extends AbstractPropertyApiImpl<Spell, SpellService>
        implements SpellApi {}
