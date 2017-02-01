package io.dods.interfaces.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@NoRepositoryBean
public interface DodsRepository<
        DATA,
        ID extends Serializable>
        extends CrudRepository<DATA, ID> {

    DATA findById(ID id);
    List<DATA> findAll();

}
