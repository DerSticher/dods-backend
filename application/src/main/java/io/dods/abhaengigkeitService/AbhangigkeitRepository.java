package io.dods.abhaengigkeitService;

import io.dods.model.regeln.Abhangigkeit;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Richard Gottschalk
 */
interface AbhangigkeitRepository extends CrudRepository<Abhangigkeit, Long> {
    Abhangigkeit findByEffektAttributId(long id);

    Abhangigkeit findById(long id);
}
