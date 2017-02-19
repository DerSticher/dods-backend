package io.dods.interfaces.services;

import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.interfaces.HasId;
import io.dods.interfaces.repositories.DodsRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
public interface DodsDatabaseService<
        ID extends Serializable,
        DATA extends HasId<ID>,
        REPO extends DodsRepository<DATA, ID>> {

    REPO getRepository();

    default DATA findById(ID id) throws ResourceNotFoundException {
        DATA byId = getRepository().findById(id);
        if (byId == null) {
            throw new ResourceNotFoundException(String.format("Unable to find id %s", String.valueOf(id)));
        }
        return byId;
    }

    default List<DATA> findAll() {
        return getRepository().findAll();
    }

    default DATA save(DATA value) {
        return getRepository().save(value);
    }

    default void deleteById(ID id) throws ResourceNotFoundException {
        boolean exists = getRepository().exists(id);
        if (exists) {
            getRepository().delete(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    default void update(ID id, DATA data) {
        boolean exists = getRepository().exists(id);
        if (!exists) throw new ResourceNotFoundException();

        data.setId(id);
        getRepository().save(data);
    }

}
