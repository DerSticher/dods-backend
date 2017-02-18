package io.dods.services.dependency;

import io.dods.interfaces.repositories.DodsRepository;
import io.dods.model.rules.Dependency;

/**
 * @author Richard Gottschalk
 */
interface DependencyRepository extends DodsRepository<Dependency, Long> {
    Dependency findByEffectPropertyId(long id);
}
