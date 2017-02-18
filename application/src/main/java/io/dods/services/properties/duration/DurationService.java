package io.dods.services.properties.duration;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.properties.misc.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class DurationService implements NamedDodsDatabaseService<Long, Duration, DurationRepository> {

    @Autowired
    private DurationRepository durationRepository;

    @Override
    public DurationRepository getRepository() {
        return durationRepository;
    }

    @Override
    public Duration findByNameOrCreate(String name) {
        Duration duration = findFirstByName(name);

        if (duration == null) {
            duration = new Duration(name);
            duration = durationRepository.save(duration);
        }

        return duration;
    }
}
