package io.dods.attributeService.reichweite;

import io.dods.model.attribute.misc.Reichweite;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Richard Gottschalk
 */
interface ReichweiteRepository extends CrudRepository<Reichweite, Long> {

    Reichweite findByName(String name);

    Reichweite findById(int id);
}
