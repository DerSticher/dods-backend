package io.dods.services.attribute.sonderfertigkeit;

import io.dods.services.attribute.AbstractAttributRepository;
import io.dods.services.attribute.AbstractAttributService;
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
