package io.dods.abhaengigkeitService;

import io.dods.model.regeln.Abhangigkeit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
interface AbhangigkeitRepository extends CrudRepository<Abhangigkeit, Long> {
    List<Abhangigkeit> findByEffektAttributId(long id);

    Abhangigkeit findById(long id);
}
