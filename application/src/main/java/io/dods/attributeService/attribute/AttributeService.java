package io.dods.attributeService.attribute;

import io.dods.attributeService.dauer.DauerService;
import io.dods.model.attribute.Attribut;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private DauerService dauerService;

    public Iterable<Attribut> find(@Nullable String typ, @Nullable String name, boolean includeSubcategories) {
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

    public Attribut findById(long id) {
        return attributeRepository.findById(id);
    }

    public <T extends Attribut> T create(T attribut) {
        return attributeRepository.save(attribut);
    }

    public List<Attribut> findSubcategoriesById(long id) {
        return attributeRepository.findBySubcategoryOfId(id);
    }
}
