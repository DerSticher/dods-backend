package io.dods.attributeService.nutzkosten;

import io.dods.model.attribute.misc.Nutzkosten;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Richard Gottschalk
 */
interface NutzkostenRepository extends CrudRepository<Nutzkosten, Long> {
    Nutzkosten findById(long id);

    Nutzkosten findByName(String name);
}
