package io.dods.services.properties.liturgicalChant;

import io.dods.model.properties.LiturgicalChant;
import io.dods.services.properties.AbstractPropertyRepository;
import io.dods.services.properties.AbstractPropertyService;
import io.dods.services.properties.castTime.CastTimeService;
import io.dods.services.properties.check.CheckService;
import io.dods.services.properties.range.RangeService;
import io.dods.services.properties.duration.DurationService;
import io.dods.services.properties.target.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class LiturgicalChantService extends AbstractPropertyService<LiturgicalChant> {

    @Autowired
    private LiturgicalChantRepository liturgicalChantRepository;

    @Override
    protected AbstractPropertyRepository<LiturgicalChant> getRepository() {
        return liturgicalChantRepository;
    }

}
