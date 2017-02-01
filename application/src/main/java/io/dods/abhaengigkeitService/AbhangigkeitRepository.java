package io.dods.abhaengigkeitService;

import io.dods.interfaces.repositories.DodsRepository;
import io.dods.model.regeln.Abhangigkeit;

/**
 * @author Richard Gottschalk
 */
interface AbhangigkeitRepository extends DodsRepository<Abhangigkeit, Long> {
    Abhangigkeit findByEffektAttributId(long id);
}
