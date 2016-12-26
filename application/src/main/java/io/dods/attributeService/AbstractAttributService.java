package io.dods.attributeService;

import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.model.attribute.Attribut;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public abstract class AbstractAttributService<T extends Attribut> {

    protected abstract AbstractAttributRepository<T> getRepository();

    public Iterable<T> findAll() {
        return getRepository().findAll();
    }

    public T findById(long id) {
        T attribut = getRepository().findById(id);
        if (attribut != null) return attribut;
        throw new ResourceNotFoundException();
    }

    public T save(T attribut) {
        return getRepository().save(attribut);
    }

    public T findByName(String name) {
        return getRepository().findByName(name);
    }
}
