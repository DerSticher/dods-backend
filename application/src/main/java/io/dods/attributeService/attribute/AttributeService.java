package io.dods.attributeService.attribute;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.attribute.Attribut;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
public class AttributeService implements NamedDodsDatabaseService<Long, Attribut, AttributeRepository> {

    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public AttributeRepository getRepository() {
        return attributeRepository;
    }

    public List<Attribut> find(@Nullable String typ, @Nullable String name, boolean includeSubcategories) {
        if (includeSubcategories) {
            if (typ != null && name != null) {
                return attributeRepository.findByTypAndName(typ, name);
            } else if (typ != null) {
                return attributeRepository.findByTyp(typ);
            } else if (name != null) {
                return attributeRepository.findByName(name);
            }
        } else {
            if (typ != null && name != null) {
                return attributeRepository.findByTypAndNameAndSubcategoryOfIdIsNull(typ, name);
            } else if (typ != null) {
                return attributeRepository.findByTypAndSubcategoryOfIdIsNull(typ);
            } else if (name != null) {
                return attributeRepository.findByNameAndSubcategoryOfIdIsNull(name);
            }
        }
        return attributeRepository.findAll();
    }

    public List<Attribut> findSubcategoriesById(long id) {
        return attributeRepository.findBySubcategoryOfId(id);
    }

}
