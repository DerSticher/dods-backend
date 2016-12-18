package io.dods.regelwerkService;

import io.dods.model.regeln.Regelwerk;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Richard Gottschalk
 */
interface RegelwerkRepository extends CrudRepository<Regelwerk, Long> {

    Regelwerk findById(long regelwerkId);
}
