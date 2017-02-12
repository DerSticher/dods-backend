package io.dods.publikationService;

import io.dods.interfaces.repositories.DodsRepository;
import io.dods.model.publikation.Publikation;
import io.dods.model.publikation.Werk;

/**
 * @author Richard Gottschalk
 */
interface PublikationRepository extends DodsRepository<Publikation, Long> {

    Publikation findByWerkAndPage(Werk werk, int page);

}
