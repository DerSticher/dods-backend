package io.dods.attributeService.probe;

import io.dods.model.attribute.misc.Probe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Richard Gottschalk
 */
interface ProbeRepository extends CrudRepository<Probe, Long> {
    Probe findById(long id);
}
