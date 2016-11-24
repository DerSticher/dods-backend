package io.dods.attributeService;

import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.model.attribute.Attribut;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public abstract class AbstractAttributService<T extends Attribut, C> {

    protected abstract AbstractAttributRepository<T> getRepository();

    protected abstract T parse(C create);

    public Iterable<T> findAll() {
        return getRepository().findAll();
    }

    public T findById(long id) {
        T attribut = getRepository().findById(id);
        if (attribut != null) return attribut;
        throw new ResourceNotFoundException();
    }

    public T save(C create) {
        T parsedValue = parse(create);
        return getRepository().save(parsedValue);
    }
}
