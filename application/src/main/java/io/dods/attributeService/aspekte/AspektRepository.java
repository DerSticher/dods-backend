package io.dods.attributeService.aspekte;

import io.dods.model.attribute.misc.Aspekt;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Richard Gottschalk
 */
interface AspektRepository extends PagingAndSortingRepository<Aspekt, Long> {
    Aspekt findById(long id);
}
