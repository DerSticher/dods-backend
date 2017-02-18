package io.dods.services.held;

import io.dods.interfaces.repositories.NamedDodsRepository;
import io.dods.model.heroes.Hero;

/**
 * @author Richard Gottschalk
 */
interface HeldRepository extends NamedDodsRepository<Hero, String> {
    Hero findById(String id);
}
