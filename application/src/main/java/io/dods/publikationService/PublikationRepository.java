package io.dods.publikationService;

import io.dods.interfaces.repositories.NamedDodsRepository;
import io.dods.model.publikation.Werk;

/**
 * @author Richard Gottschalk
 */
interface PublikationRepository extends NamedDodsRepository<Werk, Long> {
}
