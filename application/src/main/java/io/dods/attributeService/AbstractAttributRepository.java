package io.dods.attributeService;

import io.dods.model.attribute.Attribut;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Richard Gottschalk
 */
public interface AbstractAttributRepository<T extends Attribut> extends CrudRepository<T, Long> {
    T findById(long id);
    T findByName(String name);
    T findByTyp(String typ);
}
