package io.dods.services.properties.range;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.properties.misc.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class RangeService implements NamedDodsDatabaseService<Long, Range, RangeRepository> {

    @Autowired
    private RangeRepository rangeRepository;

    @Override
    public RangeRepository getRepository() {
        return rangeRepository;
    }

    @Override
    public Range findByNameOrCreate(String name) {
        Range range = findFirstByName(name);
        if (range == null) {
            range = new Range(name);
            range = rangeRepository.save(range);
        }
        return range;
    }
}
