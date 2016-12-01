package io.dods.attributeService.zielkategorie;

import io.dods.model.attribute.misc.Zielkategorie;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Richard Gottschalk
 */
interface ZielkategorieRepository extends CrudRepository<Zielkategorie, Long> {
    Zielkategorie findById(long id);

    Zielkategorie findByName(String name);
}
