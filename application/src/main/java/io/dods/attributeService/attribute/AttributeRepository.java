package io.dods.attributeService.attribute;

import io.dods.model.attribute.Attribut;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
interface AttributeRepository extends PagingAndSortingRepository<Attribut, Long> {
    Iterable<Attribut> findByTypAndName(String type, String name);
    Iterable<Attribut> findByTyp(String type);
    Iterable<Attribut> findByName(String name);

    Iterable<Attribut> findByTypAndNameAndSubcategoryOfIdIsNull(String typ, String name);
    Iterable<Attribut> findByTypAndSubcategoryOfIdIsNull(String typ);
    Iterable<Attribut> findByNameAndSubcategoryOfIdIsNull(String name);

    Attribut findById(long id);

    List<Attribut> findBySubcategoryOfId(long id);
}
