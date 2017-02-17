package io.dods.services.attribute;

import io.dods.api.exceptions.ConflictException;
import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.model.attribute.Attribut;
import io.dods.services.misc.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
public abstract class AbstractAttributService<T extends Attribut> {

    @Autowired
    private LoggerService loggerService;

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

        loggerService.info(getClass(),
                String.format("Persisting new %s: %s", getClass().getSimpleName(), attribut.getName()));
        return getRepository().save(attribut);
    }

    public T findByName(String name) {
        return getRepository().findByNameAndSubcategoryOfIdIsNull(name);
    }

    public T findByNameAndSubcategoryOf(String name, T attribut) {
        return getRepository().findFirstByNameAndSubcategoryOf(name, attribut);
    }
}
