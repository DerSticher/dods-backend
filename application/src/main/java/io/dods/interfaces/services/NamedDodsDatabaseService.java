package io.dods.interfaces.services;

import io.dods.interfaces.HasId;
import io.dods.interfaces.repositories.NamedDodsRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
public interface NamedDodsDatabaseService<
        ID extends Serializable,
        DATA extends HasId<ID>,
        REPO extends NamedDodsRepository<DATA, ID>>
        extends DodsDatabaseService<ID, DATA, REPO> {

    default List<DATA> findByName(String name) {
        return getRepository().findByName(name);
    }

    default DATA findFirstByName(String name) {
        return getRepository().findFirstByName(name);
    }

}
