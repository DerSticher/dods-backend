package io.dods.services.properties.spell;

import io.dods.model.properties.Spell;
import io.dods.services.properties.AbstractPropertyRepository;
import io.dods.services.properties.AbstractPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class SpellService extends AbstractPropertyService<Spell> {

    @Autowired
    private SpellRepository spellRepository;

    @Override
    protected AbstractPropertyRepository<Spell> getRepository() {
        return spellRepository;
    }

}
