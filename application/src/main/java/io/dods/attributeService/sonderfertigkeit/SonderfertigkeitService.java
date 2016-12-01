package io.dods.attributeService.sonderfertigkeit;

import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.model.attribute.Sonderfertigkeit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class SonderfertigkeitService extends AbstractAttributService<Sonderfertigkeit> {

    @Autowired
    private SonderfertigkeitRepository sonderfertigkeitRepository;

    @Override
    protected AbstractAttributRepository<Sonderfertigkeit> getRepository() {
        return sonderfertigkeitRepository;
    }

}
