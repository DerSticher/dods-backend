package io.dods.services.dependency;

import io.dods.interfaces.repositories.DodsRepository;
import io.dods.model.rules.Dependency;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
interface DependencyRepository extends DodsRepository<Dependency, Long> {
    Dependency findFirstByEffectPropertyId(long id);

    List<Dependency> findByEffectPropertyIsNull();
}
