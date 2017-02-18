package io.dods.services.properties.ceremony;

import io.dods.services.properties.AbstractPropertyRepository;
import io.dods.services.properties.AbstractPropertyService;
import io.dods.services.properties.castTime.CastTimeService;
import io.dods.services.properties.check.CheckService;
import io.dods.services.properties.range.RangeService;
import io.dods.services.properties.duration.DurationService;
import io.dods.services.properties.target.TargetService;
import io.dods.model.properties.Ceremony;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class CeremonyService extends AbstractPropertyService<Ceremony> {

    @Autowired
    private CeremonyRepository ceremonyRepository;

    @Override
    protected AbstractPropertyRepository<Ceremony> getRepository() {
        return ceremonyRepository;
    }

}
