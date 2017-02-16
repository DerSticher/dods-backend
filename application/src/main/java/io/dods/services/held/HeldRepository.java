package io.dods.services.held;

import io.dods.interfaces.repositories.NamedDodsRepository;
import io.dods.model.helden.Held;

/**
 * @author Richard Gottschalk
 */
interface HeldRepository extends NamedDodsRepository<Held, String> {
    Held findById(String id);
}
