package io.dods.services.properties.bless;

import io.dods.model.properties.Bless;
import io.dods.services.properties.AbstractPropertyRepository;
import io.dods.services.properties.AbstractPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class BlessService extends AbstractPropertyService<Bless> {

    @Autowired
    private BlessRepository blessRepository;

    @Override
    protected AbstractPropertyRepository<Bless> getRepository() {
        return blessRepository;
    }

}
