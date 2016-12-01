package io.dods.attributeService.dauer;

import io.dods.model.attribute.misc.Dauer;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Richard Gottschalk
 */
interface DauerRepository extends CrudRepository<Dauer, Long> {
    Dauer findById(long id);

    Dauer findByName(String name);
}
