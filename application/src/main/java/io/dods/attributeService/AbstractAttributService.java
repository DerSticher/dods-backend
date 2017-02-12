package io.dods.attributeService;

import io.dods.api.exceptions.ConflictException;
import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.model.attribute.Attribut;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
public abstract class AbstractAttributService<T extends Attribut> {

    protected abstract AbstractAttributRepository<T> getRepository();

    public List<T> findAll() {
        return getRepository().findAll();
    }

    public T findById(long id) {
        T attribut = getRepository().findById(id);
        if (attribut != null) return attribut;
        throw new ResourceNotFoundException();
    }

    public T save(T attribut) {
        T byName = findByName(attribut.getName());
        if (byName != null) throw new ConflictException(String.format("Value %s is not unique", attribut.getName()));

        return getRepository().save(attribut);
    }

    public T findByName(String name) {
        return getRepository().findByNameAndSubcategoryOfIdIsNull(name);
    }

    public T findByNameAndSubcategoryOf(String name, T attribut) {
        return getRepository().findFirstByNameAndSubcategoryOf(name, attribut);
    }
}
