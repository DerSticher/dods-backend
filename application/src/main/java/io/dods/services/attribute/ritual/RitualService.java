package io.dods.services.attribute.ritual;

import io.dods.services.attribute.AbstractAttributRepository;
import io.dods.services.attribute.AbstractAttributService;
import io.dods.model.attribute.Ritual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class RitualService extends AbstractAttributService<Ritual> {

    @Autowired
    private RitualRepository ritualRepository;

    @Override
    protected AbstractAttributRepository<Ritual> getRepository() {
        return ritualRepository;
    }

}
