package io.dods.services.properties.species;

import io.dods.model.properties.Culture;
import io.dods.services.properties.AbstractPropertyRepository;
import io.dods.services.properties.AbstractPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class CultureService extends AbstractPropertyService<Culture> {

    @Autowired
    private CultureRepository cultureRepository;

    @Override
    protected AbstractPropertyRepository<Culture> getRepository() {
        return cultureRepository;
    }

}
