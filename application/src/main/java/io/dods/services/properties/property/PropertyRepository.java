package io.dods.services.properties.property;

import io.dods.interfaces.repositories.NamedDodsRepository;
import io.dods.model.properties.Property;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
interface PropertyRepository extends NamedDodsRepository<Property, Long> {
    List<Property> findByTypeAndName(String type, String name);
    List<Property> findByType(String type);

    List<Property> findByTypeAndNameAndSubcategoryOfIdIsNull(String typ, String name);
    List<Property> findByTypeAndSubcategoryOfIdIsNull(String typ);
    List<Property> findByNameAndSubcategoryOfIdIsNull(String name);

    List<Property> findBySubcategoryOfId(long id);
}
