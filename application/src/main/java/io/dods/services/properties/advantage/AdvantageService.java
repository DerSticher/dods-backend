package io.dods.services.properties.advantage;

import io.dods.model.properties.Advantage;
import io.dods.services.properties.AbstractPropertyRepository;
import io.dods.services.properties.AbstractPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class AdvantageService extends AbstractPropertyService<Advantage> {

    @Autowired
    private AdvantageRepository advantageRepository;

    @Override
    protected AbstractPropertyRepository<Advantage> getRepository() {
        return advantageRepository;
    }

}
