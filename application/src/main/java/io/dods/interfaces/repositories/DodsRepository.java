package io.dods.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
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
        extends JpaRepository<DATA, ID> {

    DATA findById(ID id);
    List<DATA> findAll();

}
