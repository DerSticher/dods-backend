package io.dods.interfaces.repositories;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@NoRepositoryBean
public interface NamedDodsRepository<
        DATA,
        ID extends Serializable>
        extends DodsRepository<DATA, ID> {

    List<DATA> findByName(String name);
    DATA findFirstByName(String name);
    List<DATA> findAllByOrderByNameAsc();

}
