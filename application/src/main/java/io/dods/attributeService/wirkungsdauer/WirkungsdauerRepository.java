package io.dods.attributeService.wirkungsdauer;

import io.dods.model.attribute.misc.Wirkungsdauer;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Richard Gottschalk
 */
interface WirkungsdauerRepository extends CrudRepository<Wirkungsdauer, Long> {
    Wirkungsdauer findById(long id);
}
