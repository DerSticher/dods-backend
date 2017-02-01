package io.dods.attributeService.aspekte;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.attribute.misc.Aspekt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class AspektService implements NamedDodsDatabaseService<Long, Aspekt, AspektRepository> {

    @Autowired
    private AspektRepository aspektRepository;

    @Override
    public AspektRepository getRepository() {
        return aspektRepository;
    }

    public Aspekt findByNameOrCreate(String name) {
        Aspekt aspekt = findFirstByName(name);

        if (aspekt == null) {
            aspekt = new Aspekt(name);
            aspekt = aspektRepository.save(aspekt);
        }

        return aspekt;
    }
}
