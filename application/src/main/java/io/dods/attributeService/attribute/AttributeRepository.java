package io.dods.attributeService.attribute;

import io.dods.interfaces.repositories.NamedDodsRepository;
import io.dods.model.attribute.Attribut;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
interface AttributeRepository extends NamedDodsRepository<Attribut, Long> {
    List<Attribut> findByTypAndName(String type, String name);
    List<Attribut> findByTyp(String type);

    List<Attribut> findByTypAndNameAndSubcategoryOfIdIsNull(String typ, String name);
    List<Attribut> findByTypAndSubcategoryOfIdIsNull(String typ);
    List<Attribut> findByNameAndSubcategoryOfIdIsNull(String name);

    List<Attribut> findBySubcategoryOfId(long id);
}
