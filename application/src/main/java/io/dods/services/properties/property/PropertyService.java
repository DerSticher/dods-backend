package io.dods.services.properties.property;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.properties.Property;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
public class PropertyService implements NamedDodsDatabaseService<Long, Property, PropertyRepository> {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public PropertyRepository getRepository() {
        return propertyRepository;
    }

    public List<Property> find(@Nullable String typ, @Nullable String name, boolean includeSubcategories) {
        if (includeSubcategories) {
            if (typ != null && name != null) {
                return propertyRepository.findByTypeAndName(typ, name);
            } else if (typ != null) {
                return propertyRepository.findByType(typ);
            } else if (name != null) {
                return propertyRepository.findByName(name);
            }
        } else {
            if (typ != null && name != null) {
                return propertyRepository.findByTypeAndNameAndSubcategoryOfIdIsNull(typ, name);
            } else if (typ != null) {
                return propertyRepository.findByTypeAndSubcategoryOfIdIsNull(typ);
            } else if (name != null) {
                return propertyRepository.findByNameAndSubcategoryOfIdIsNull(name);
            }
        }
        return propertyRepository.findAll();
    }

    public List<Property> findSubcategoriesById(long id) {
        return propertyRepository.findBySubcategoryOfId(id);
    }

    @Override
    public Property findByNameOrCreate(String name) {
        throw new IllegalStateException("Please add new Attributes with the corresponding Services");
    }
}
