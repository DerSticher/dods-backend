package io.dods.attributeService.attribute;

import io.dods.model.attribute.Attribut;
import io.dods.model.attribute.misc.Wirkungsdauer;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Richard Gottschalk
 */
interface AttributeRepository extends PagingAndSortingRepository<Attribut, Long> {
    Iterable<Attribut> findByTypAndName(String type, String name);
    Iterable<Attribut> findByTyp(String type);
    Iterable<Attribut> findByName(String name);

    Attribut findById(long id);
}
