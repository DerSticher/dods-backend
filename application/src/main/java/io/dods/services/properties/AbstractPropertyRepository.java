package io.dods.services.properties;

import io.dods.interfaces.repositories.DodsRepository;
import io.dods.model.properties.Property;

/**
 * @author Richard Gottschalk
 */
public interface AbstractPropertyRepository<T extends Property> extends DodsRepository<T, Long> {
    T findFirstByNameAndSubcategoryOf(String name, Property property);
    T findByNameAndSubcategoryOfIdIsNull(String name);
    T findByType(String typ);
}
