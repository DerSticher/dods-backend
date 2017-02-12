package io.dods.attributeService;

import io.dods.interfaces.repositories.DodsRepository;
import io.dods.model.attribute.Attribut;

/**
 * @author Richard Gottschalk
 */
public interface AbstractAttributRepository<T extends Attribut> extends DodsRepository<T, Long> {
    T findFirstByNameAndSubcategoryOf(String name, Attribut attribut);
    T findByNameAndSubcategoryOfIdIsNull(String name);
    T findByTyp(String typ);
}
