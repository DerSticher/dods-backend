package io.dods.services.properties.ritual;

import io.dods.services.properties.AbstractPropertyRepository;
import io.dods.services.properties.AbstractPropertyService;
import io.dods.model.properties.Ritual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class RitualService extends AbstractPropertyService<Ritual> {

    @Autowired
    private RitualRepository ritualRepository;

    @Override
    protected AbstractPropertyRepository<Ritual> getRepository() {
        return ritualRepository;
    }

}
