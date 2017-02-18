package io.dods.services.properties.cost;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.properties.misc.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class CostService implements NamedDodsDatabaseService<Long, Cost, CostServiceRepository> {

    @Autowired
    private CostServiceRepository costServiceRepository;

    @Override
    public CostServiceRepository getRepository() {
        return costServiceRepository;
    }

    public Cost findById(long id) {
        return costServiceRepository.findById(id);
    }

    @Override
    public Cost findByNameOrCreate(String name) {
        Cost aspekt = findFirstByName(name);

        if (aspekt == null) {
            aspekt = new Cost(name);
            aspekt = costServiceRepository.save(aspekt);
        }

        return aspekt;
    }
}
