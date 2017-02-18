package io.dods.services.properties.cantrip;

import io.dods.model.properties.Cantrip;
import io.dods.services.properties.AbstractPropertyRepository;
import io.dods.services.properties.AbstractPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class CantripService extends AbstractPropertyService<Cantrip> {

    @Autowired
    private CantripRepository zauberRepository;

    @Override
    protected AbstractPropertyRepository<Cantrip> getRepository() {
        return zauberRepository;
    }

}
